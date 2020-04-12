package string;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {

	public static void main(String[] args) {
		StringDemo strr = new StringDemo();
		String name = "meng";
		StringBuffer nnn = new StringBuffer("hahah");
		strr.getNewStr(name, nnn);
		
		Map map = new HashMap();
		map.put("mm", name);
		
		String string3="second";
		String string4=new String("second");
		System.out.println(string3==string4);
		System.out.println(string3.equals(string4));

	}
}
