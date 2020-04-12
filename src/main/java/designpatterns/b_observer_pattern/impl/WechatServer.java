package designpatterns.b_observer_pattern.impl;


import designpatterns.b_observer_pattern.interfaces.Observer;
import designpatterns.b_observer_pattern.interfaces.Observerable;

import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * @author wen
 *
 */
public class WechatServer implements Observerable {

	//注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
	private List<Observer> list;
	private String message;
	
	@Override
	public void registerObserver(Observer o) {
		
		list.add(o);
	}

	@Override
	public void removeObserver(Observer o) {

		if(!list.isEmpty()) {
			list.remove(o);
		}
	}

	@Override
	public void notifyObserver() {

		for (Observer observer : list) {
			observer.update(message);
		}
	}

	public void setInformation(String message) {
		this.message = message;
		notifyObserver();
	}
	
	public List<Observer> getList() {
		return list;
	}

	public void setList(List<Observer> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
