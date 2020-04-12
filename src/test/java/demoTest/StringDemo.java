package demoTest;

public class StringDemo {

	public void change(String str) {
		str = "改变";
	}
	public static void main(String[] args) {
		String str1 = "哈哈";
		StringDemo ss = new StringDemo();
		ss.change(str1);
		System.out.println(str1);
		StringBuilder sb = new StringBuilder("abcde");
		System.out.println(sb.hashCode());
		sb.setCharAt(2, 'M');
		System.out.println(sb.hashCode());
		System.out.println(Integer.toHexString(sb.hashCode()));
		System.out.println(sb);
		
	}
}
