package duotai;

/**
 * 方法参数的引用
 * @author wen
 *
 */
public class MethodParam {
	public static void main(String[] args)  {
		MyOneClass myClass = new MyOneClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        String str = "hello";
        myClass.changeStringValue(str);
        System.out.println("buffer--->" + buffer.toString());
        System.out.println("str--->" + str);
    }
}

class MyOneClass {
    void changeValue(StringBuffer buffer) {
        buffer.append("world");
    }
    void changeStringValue(String string) {
    	string += " world";
    }
}
