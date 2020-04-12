package proxy.cglb;

public class CglbTest {
	
	public static void main(String[] args) {
		
		MyMethodInterceptor interceptor = new MyMethodInterceptor();
		HelloService proxyHelloService = (HelloService) interceptor.createProxyInstance(HelloService.class);
		proxyHelloService.sayHello();
	}

}
