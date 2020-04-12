package designpatterns.a_strategy_pattern.duck;


import designpatterns.a_strategy_pattern.base.Duck;
import designpatterns.a_strategy_pattern.flybehavior.GoodFlybehavior;
import designpatterns.a_strategy_pattern.quarkbehavior.GaGaQuarkBehaver;

public class GreenDuck extends Duck {

	@Override
	public void display() {

		System.out.println("绿头鸭");
	}
	public GreenDuck() {
		myQuarkBehaver = new GaGaQuarkBehaver();
		myFlyBehavior = new GoodFlybehavior();
	}
	
	
}
