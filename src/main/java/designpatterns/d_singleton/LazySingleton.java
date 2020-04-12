package designpatterns.d_singleton;

/**
 * 懒汉模式
 * @author wen
 *
 */
public class LazySingleton {

	//初始化时，不加载该对象，延迟加载，使用到时才创建
	private static LazySingleton lazySingleton;
	
	private LazySingleton() {
	}
	
	//方法要同步（避免同时调用时，因为线程被挂起，产生多个对象 ），效率低
	public static synchronized LazySingleton getInstance() {
		if(lazySingleton == null) {
			lazySingleton = new LazySingleton();
		}
		return lazySingleton;
	}
}
