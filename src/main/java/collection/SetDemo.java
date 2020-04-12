package collection;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {

	public static void main(String[] args) {
		
		Set<String>  hashSet = new HashSet();
		hashSet.add("hahah");
		hashSet.add("gga");
		hashSet.hashCode();
		
		Set<String>  hashSet2 = new HashSet();
		hashSet2.add("gga");
		hashSet2.add("hahah");
		hashSet2.add(null);
		
		System.out.println(hashSet.equals(hashSet2));
	}
}
