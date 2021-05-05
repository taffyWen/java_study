package proxy.cglb.demo2;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Demo2Interceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法执行之前。。。。。。。");
        /**
         * objects 是方法的参数
         */
        if ("sayHello".equals(method.getName())){
            System.out.println("我在执行sayHello方法啊。。。。。。参数:" + objects);
        }
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("方法执行之后============");

        return invokeSuper;
    }
}
