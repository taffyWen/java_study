package proxy.jdk.demo2;

import proxy.jdk.IHelloService;
import proxy.jdk.MyHelloService;

import java.lang.reflect.Proxy;

public class Demo2Test {

    public static void main(String[] args) {
        MyHelloService helloService = new MyHelloService();
        IHelloService iHelloService = (IHelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(),
                new Class[]{IHelloService.class},new Demo2InvocationHandler(helloService));
        iHelloService.sayHello("你猜我是谁");

    }
}
