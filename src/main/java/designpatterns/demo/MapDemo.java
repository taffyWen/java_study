package designpatterns.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {

	public static void main(String[] args) {
		
		Map map = new HashMap<>();
		Map map1 = new ConcurrentHashMap();
		
		String str1 = new StringBuffer("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuffer("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		
	}
}
