package designpatterns.a_strategy_pattern.base;

public abstract class Duck {

	protected QuarkBehaver myQuarkBehaver;
	protected Flybehavior myFlyBehavior;
	
	public void fly() {
		myFlyBehavior.fly();
	}

	public void quark() {
		myQuarkBehaver.quark();
	}
	
	public abstract void display();
	
	
	public QuarkBehaver getMyQuarkBehaver() {
		return myQuarkBehaver;
	}

	public void setMyQuarkBehaver(QuarkBehaver myQuarkBehaver) {
		this.myQuarkBehaver = myQuarkBehaver;
	}

	public Flybehavior getMyFlyBehavior() {
		return myFlyBehavior;
	}

	public void setMyFlyBehavior(Flybehavior myFlyBehavior) {
		this.myFlyBehavior = myFlyBehavior;
	}
	
}
