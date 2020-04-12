package thread;


/**
 * lambda推导过程
 * @author wen
 */
public class LambdaTest01 {

	//静态内部类
	static class Like2 implements ILike{

		@Override
		public void lambda() {
			System.out.println("i like lambda2");
		}
		
	}
	public static void main(String[] args) {
		ILike like = new Like();
		ILike like2 = new Like2();
		like.lambda();
		like2.lambda();
		//匿名内部类
		like = new ILike() {
			@Override
			public void lambda() {
				System.out.println("i like lambda3");
			}
		};
		like.lambda();
		
		class Like3 implements ILike{

			@Override
			public void lambda() {
				System.out.println("i like lambda4");
			}
		}
		ILike like3 = new Like3();
		like3.lambda();
		//接口推导，只能有一个方法
		like = ()-> {
			System.out.println("i like lambda5");
		};
		like.lambda();
	}
	
}
interface ILike{
	void lambda();
}

class Like implements ILike{

	@Override
	public void lambda() {
		System.out.println("i like lambda");
	}
	
}