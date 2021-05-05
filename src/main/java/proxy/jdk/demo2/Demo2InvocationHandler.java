package proxy.jdk.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Demo2InvocationHandler implements InvocationHandler {

    private Object object;

    public Demo2InvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法代理前。。。。。。。。方法入参"  + args[0]);
        Object invoke = method.invoke(object, args);
        System.out.println("方法代理后=============");
        return invoke;
    }
}
