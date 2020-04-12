package proxy.jdk;

public class MainJavaProxy {

    public static void main(String[] args) {
        JavaProxyInvocationHandler javaProxyInvocationHandler = new JavaProxyInvocationHandler(new MyHelloService());
        IHelloService iHelloService = (IHelloService) javaProxyInvocationHandler.newProxyInstance();
        iHelloService.sayHello("阿温");
        iHelloService.sayByeBye("阿温");
    }
}
