package designpatterns.a_strategy_pattern.flybehavior;


import designpatterns.a_strategy_pattern.base.Flybehavior;

public class GoodFlybehavior implements Flybehavior {

	@Override
	public void fly() {
		System.out.println("good-------------------fly---------------------");

	}

}
