package designpatterns.b_observer_pattern.impl;


import designpatterns.b_observer_pattern.interfaces.Observer;

/**
 * 观察者
 * 实现了update方法
 * @author wen
 *
 */
public class User implements Observer {

	private String name;
	private String message;
	
	@Override
	public void update(String message) {

		this.message = message;
		read(message);
	}

	public void read(String message) {
		System.out.println(this.name + "收到一条消息" + message);
	}
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
