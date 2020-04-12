package collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 底层是数组+链表
 */
public class MapDemo {
	
	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();
		
		//线程安全原因，方法上面都有synchronized
		Map<String, String> hashTable = new Hashtable<String, String>(); 
		
		//key/value 都可为null
		hashMap.put(null, null); 
		hashMap.put("c", "ccccc"); 
		hashMap.put("b", "dddddd"); 
		hashMap.put("a", "aaaaa");

		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


		/**
		 * 循环方式
		 */
		for (String string : hashMap.values()) {
			System.out.println(string);
		}
		
		for (String string : hashMap.keySet()) {
			System.out.println(string);
		}
		
		for (Entry entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		Map treeMap = new TreeMap<String, String>();
		String str = "haha";
		
		Map map = new HashMap();
		Map map1 = new HashMap();
		map1.put("name","wen");
		map.put("map1",map1);
		map.put("age",24);

		Map map2 = map;
		map2.put("age",28);
		System.out.println(map);
		((Map)map2.get("map1")).put("name","mable");
		System.out.println(map);
		
	}
}
