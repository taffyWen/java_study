package redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";

	/**
	 * 可以看到，我们加锁就一行代码：jedis.set(String key, String value, String nxxx, String expx,
	 * int time)，这个set()方法一共有五个形参： 第一个为key，我们使用key来当锁，因为key是唯一的。
	 * 第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，
	 * 分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，
	 * 在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
	 * 第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT
	 * EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
	 * 第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
	 * 第五个为time，与第四个参数相呼应，代表key的过期时间。 总的来说，执行上面的set()方法就只会导致两种结果：1.
	 * 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。2. 已有锁存在，不做任何操作。
	 * 
	 * 心细的童鞋就会发现了，我们的加锁代码满足我们可靠性里描述的三个条件。
	 * 首先，set()加入了NX参数，可以保证如果已有key存在，则函数不会调用成功，也就是只有一个客户端能持有锁，满足互斥性。
	 * 其次，由于我们对锁设置了过期时间，即使锁的持有者后续发生崩溃而没有解锁，锁也会因为到了过期时间而自动解锁（即key被删除），不会发生死锁。
	 * 最后，因为我们将value赋值为requestId，代表加锁的客户端请求标识，那么在客户端在解锁的时候就可以进行校验是否是同一个客户端。
	 * 由于我们只考虑Redis单机部署的场景，所以容错性我们暂不考虑。
	 * 
	 * @param jedis      Redis客户端
	 * @param lockKey    锁
	 * @param requestId  客户端标识
	 * @param expireTime 超期时间
	 * @return 是否获取成功
	 */
	public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

		String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
		if (LOCK_SUCCESS.equals(result)) {
			return true;
		}
		return false;

	}

	/**
	 * 错误示例： 
	 * 比如客户端A加锁，一段时间之后客户端A解锁，在执行jedis.del()之前，锁突然过期了，此时客户端B尝试加锁成功，
	 * 然后客户端A再执行del()方法，则将客户端B的锁给解除了。
	 * 
	 * @param jedis
	 * @param lockKey
	 * @param requestId
	 */
	public static void wrongReleaseLock2(Jedis jedis, String lockKey, String requestId) {
		// 判断加锁与解锁是不是同一个客户端
		if (requestId.equals(jedis.get(lockKey))) { // 若在此时，这把锁突然不是这个客户端的，则会误解锁
			jedis.del(lockKey);
		}
	}
	
	
	
	private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 正确解锁：
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
