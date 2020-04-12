package duotai;

/**
 * 抽象类有构造方法，只是不能被实例化。
 * 接口没有构造方法
 * @author wen
 *抽象方法必须为public或者protected
 *（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public
 */
public abstract class AbstractDemo {

	public AbstractDemo() {
	}

	abstract void say();
}
