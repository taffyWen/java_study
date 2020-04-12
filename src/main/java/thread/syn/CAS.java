package thread.syn;

import java.util.concurrent.atomic.AtomicLong;

/**
 * atomic 原子性
 * 悲观锁：synchronized 是独占锁，即悲观锁。会导致其他所有需要锁的线程挂起
 * 乐观锁：每次不加锁，而是假设没有冲突而去完成某项操作，
 * 			如果因为冲突失败就重试，直到成功为止。
 * CAS comparec and swap  -->比较并交换
 * 		有3个值，一个当前内存值V，旧的预期值A，将更新的值B。
 * 		先获得V，与原值A比较，相等就修改为B并返回true；否则什么都不做，返回false
 * @author wen
 *
 */
public class CAS {

	//库存
	private static AtomicLong stock = new AtomicLong(5);
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(()-> {
				int left = (int) stock.decrementAndGet();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//模拟网络延时
				if(left < 1) {
					System.out.println("抢完了----------------");
					return;
				}
				System.out.println(Thread.currentThread().getName()+"抢了一件");
				System.out.println("还剩商品" + left);
			}).start();
		}
	}
}
