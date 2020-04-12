package proxy.cglb;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor{

	/**
	 * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，
	 * Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
	 */
	Enhancer enhancer = new Enhancer();
	
	public Object createProxyInstance(Class targetObject) {
		// 设置产生的代理对象的父类,增强类型
		enhancer.setSuperclass(targetObject);//非final类
		// 定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
		// 参数Callback ，因为本类实现了 MethodInterceptor
		enhancer.setCallback(this);
		//使用默认无参数的构造函数创建目标对象,这是一个前提,被代理的类要提供无参构造方法
		return enhancer.create();
	}

	/**
	 *
	 * @param sub 被代理的对象
	 * @param method 代理的方法
	 * @param objects 方法的参数
	 * @param methodProxy CGLIB方法代理对象
	 * @return cglib生成用来代替Method对象的一个对象
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("======插入前置通知======");
		Object invokeSuper = methodProxy.invokeSuper(sub, objects);
        System.out.println("======插入前置通知======");
        return invokeSuper;
	}

}
