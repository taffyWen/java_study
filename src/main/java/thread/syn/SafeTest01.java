package thread.syn;

/**
 * 线程锁，synchronized关键字。主要是锁的范围
 * @author wen
 *
 */
public class SafeTest01 {

	public static void main(String[] args) {
		Usafe12306 uasafe = new Usafe12306();
		new Thread(uasafe, "张三").start();
		new Thread(uasafe, "李四").start();
		new Thread(uasafe, "王五").start();
	}

}

class Usafe12306 implements Runnable {

	public static int ticketNums = 10;
	private boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test5();
		}
	}

	// 线程安全
	public void test5() {
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		//
		synchronized (this) {//虽然一样，但是上面的if判断除去了<=0的情况，所以粒度反而更小
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("异常" + e);
			}
			System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
		}
	}

	public synchronized void test() {
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		// 模拟延时
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("异常" + e);
		}
		System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
	}

	//
	public void test2() {
		// 需要看ticketNums/flag，两个刚好在当前类。所以就用this
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("异常" + e);
			}
			System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
		}
	}

	// 线程安全
	public void test3() {
		// 需要看ticketNums/flag，两个刚好在当前类。所以就用this
		synchronized ((Integer) ticketNums) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("异常" + e);
			}
			System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
		}
	}

	// 线程不安全
	public void test4() {
		// 范围太小，锁不住
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
		}
		// 模拟延时
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("异常" + e);
		}
		System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
	}
}
