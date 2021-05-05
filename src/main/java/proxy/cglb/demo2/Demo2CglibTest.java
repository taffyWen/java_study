package proxy.cglb.demo2;

import org.springframework.cglib.proxy.Enhancer;
import proxy.cglb.HelloService;

public class Demo2CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new Demo2Interceptor());
        HelloService hello  = (HelloService) enhancer.create();
        hello.sayHello();
        //不报错，但是不能被代理
        hello.sayOthers("测试final");

    }
}
