package designpatterns.b_observer_pattern;


import designpatterns.b_observer_pattern.impl.User;
import designpatterns.b_observer_pattern.impl.WechatServer;
import designpatterns.b_observer_pattern.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Observer user1 = new User("张三");
		Observer  user2 = new User("李四");
		Observer  user3 = new User("王五");
		
		WechatServer server = new WechatServer();
		List<Observer> list = new ArrayList<Observer>();
		server.setList(list);
		
		server.registerObserver(user1);
		server.registerObserver(user2);
		server.registerObserver(user3);
		
		server.setInformation("Python 是最好的开发语言");
		
		server.removeObserver(user2);
		
		server.setInformation("Java 是最好的开发语言");
		
	}
}
