package thread;

public class LambdaThread {

	// 静态内部类
	static class Test implements Runnable {
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println("一边听歌");
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new Test()).start();

		// 局部内部类
		class Test2 implements Runnable {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("一边学习");
				}
			}
		}
		new Thread(new Test2()).start();
		// 匿名内部类，必须借助接口或者父类
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("一边玩手机");
				}
			}
		}).start();
		// jdk8简化 lambda ,接口中只有一个方法
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("一边玩手机");
			}
		}).start();
	}
}
