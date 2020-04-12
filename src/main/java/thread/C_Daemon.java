package thread;

public class C_Daemon {

	public static void main(String[] args) {
		Person p = new Person();
		Daemon d = new Daemon();
		
		Thread t = new Thread(d);
		t.setDaemon(true);//守护线程
		t.start();
		new Thread(p).start();
	}
	
}

class Person implements Runnable{

	@Override
	public void run() {

		for (int i = 0; i < 30; i++) {
			System.out.println("happy life ....");
		}
		System.out.println("end.............");
	}
	
}
class Daemon implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("守护者。。。。。。。。。。。");
		}
	}
	
}