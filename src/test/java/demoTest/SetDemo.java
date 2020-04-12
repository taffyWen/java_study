package demoTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {

	// 验证结果 1. 唯一，无序 
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("12");
		set.add("12");
		set.add("23");
		set.add("45");
		set.add("16");
		set.add("78");
		for (String string : set) {
			System.out.println(string);
		}
		
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
