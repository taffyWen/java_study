package thread;


/**
 * lambda推导过程
 * @author wen
 */
public class LambdaTest02 {
	public static void main(String[] args) {
		ILove love = (int a)-> {
			System.out.println("i like lambda2 --->"+a);
		};
		
		love.lambda(100);
	}
	
}
interface ILove{
	void lambda(int a);
}

class Love implements ILove{

	@Override
	public void lambda(int a) {
		System.out.println("i like lambda --->"+a);
	}
	
}