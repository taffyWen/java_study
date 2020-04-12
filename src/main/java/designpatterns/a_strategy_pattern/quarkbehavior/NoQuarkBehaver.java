package designpatterns.a_strategy_pattern.quarkbehavior;


import designpatterns.a_strategy_pattern.base.QuarkBehaver;

public class NoQuarkBehaver implements QuarkBehaver {

	@Override
	public void quark() {
		
		System.out.println("不会------------------------叫");

	}

}
