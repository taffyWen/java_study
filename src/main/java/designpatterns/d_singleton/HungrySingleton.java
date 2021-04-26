package designpatterns.d_singleton;
/**
 *  饿汉模式
 * @author wen
 *
 */
public class HungrySingleton {

	//类初始化的时候，就加载该对象，天然的线程安全
	private static HungrySingleton hungrySingleton = new HungrySingleton();
	
	//私有的构造函数
	private HungrySingleton() {
		System.out.println("HungrySingleton---构造器");
	}
	
	//方法不需要同步（synchronized），调用效率高
	public static HungrySingleton getInstance() {
		return hungrySingleton;
	}
}
