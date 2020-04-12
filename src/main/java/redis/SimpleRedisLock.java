package redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class SimpleRedisLock {

	public static long hold_time = 3000;
	public static ThreadLocal<String> expireHolder = new ThreadLocal<String>();

	public static Jedis jedis;
	public static void acquire(String lock) { // 1.先尝试用setnx命令获取锁,key为参数lock,值为当前时间+要持有锁的时间hold_time
		while (jedis.setnx(lock, String.valueOf(System.currentTimeMillis() + hold_time)) == 0) { // 2.如果获取失败,先watch lock
																									// key
			jedis.watch(lock); // 3.获取当前超时时间
			String expireTime = jedis.get(lock);
			if (expireTime != null && Long.parseLong(expireTime) < System.currentTimeMillis()) { // 4.如果超时时间小于当前时间,开事务准备更新lock值
				Transaction transaction = jedis.multi();
				Response<String> response = transaction.getSet(lock,
						String.valueOf(System.currentTimeMillis() + hold_time)); // 5.步骤2设置了watch,如果lock的值被其他线程修改,不是执行事务中的命令
				if (transaction.exec() != null) {
					String oldExpire = response.get();
					if (oldExpire != null && Long.parseLong(expireTime) < System.currentTimeMillis()) { // 6.如果setget命令返回的值依然是过期时间,认为获取锁成功(加了watch之后,这里返回的应该一直是超时时间)
						break;
					}
				}
			} else { // 如果key未超时,解除watch
				jedis.unwatch();
			}
		} // 设置客户端超时时间
		expireHolder.set(jedis.get(lock));
	}

	public static void release(String lock) { // 比较客户端超时时间与lock值,判断是否还由自己持有锁
		if (jedis.get(lock).equals(expireHolder.get())) {
			jedis.del(lock);
		}
		jedis.close();
	}


}
