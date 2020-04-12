package designpatterns.a_strategy_pattern.quarkbehavior;


import designpatterns.a_strategy_pattern.base.QuarkBehaver;

public class GaGaQuarkBehaver implements QuarkBehaver {

	@Override
	public void quark() {

		System.out.println("gaga---------------叫");
	}

}
