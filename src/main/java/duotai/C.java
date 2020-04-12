package duotai;


/**
 * 接口可以多继承
 * @author wen
 *
 */
public interface C extends A,B{

	void laugh();
    
	//1.8中接口可以有default方法
	default void cry() {
		System.out.println("入世第一哭");
	}
	
	String str = "";
	
	public static String s = "";
}
