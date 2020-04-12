package designpatterns.a_strategy_pattern;


import designpatterns.a_strategy_pattern.base.Duck;
import designpatterns.a_strategy_pattern.duck.GreenDuck;

public class Test {

	public static void main(String[] args) {
		Duck greenDuck = new GreenDuck();
		greenDuck.display();
		greenDuck.fly();
		greenDuck.quark();
	}
}
