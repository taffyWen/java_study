package thread.syn;

public class UnSafeTest {

	public static void main(String[] args) {
		Usafe12306 uasafe = new Usafe12306();
		new Thread(uasafe,"张三").start();
		new Thread(uasafe,"李四").start();
		new Thread(uasafe,"王五").start();
	}
	
	
}

class Safe12306 implements Runnable{

	public static int ticketNums = 10;
	private boolean flag = true;
	
	@Override
	public void run() {
		while(flag) {
			test();
		}
	}
	
	public void test() {
		if(ticketNums < 0) {
			flag = false;
			return;
		}
		//模拟延时
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("异常" + e);
		}
		System.out.println(Thread.currentThread().getName() +"---->"+ticketNums --);
	}
	
}
