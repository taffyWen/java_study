package designpatterns.d_singleton;

/**
 * 静态内部类
 * @author wen
 *
 */
public class StaticInnerClassSingleton {

	private StaticInnerClassSingleton() {
	}
	
	//类加载时，天然的线程安全
	private static class SingletonInstance {
		private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
	}
	
	//懒加载，使用时才加载
	public static StaticInnerClassSingleton getInstance() {
		return SingletonInstance.staticInnerClassSingleton;
	}
}
