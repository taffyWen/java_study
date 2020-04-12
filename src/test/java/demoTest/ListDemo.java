package demoTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

	//验证结果1. List 有序且，不唯一
	
	public static void main(String[] args) {
		List arrayList = new ArrayList();
		arrayList.add("哈哈");
		arrayList.add("哈哈1");
		arrayList.add("哈哈2");
		arrayList.add("哈哈");
		for (Object object : arrayList) {
			System.out.println(object);
		}
		
		List<String> linkedList = new LinkedList<String>();
		linkedList.add("嘻嘻1");
		linkedList.add("嘻嘻2");
		linkedList.add("嘻嘻3");
		linkedList.add("嘻嘻4");
		for (String string : linkedList) {
			System.out.println(string);
		}
		LinkedList list = new LinkedList<String>();
		list.addFirst("");
	}
	
}
