package collection;

import java.util.*;

/**
 * List和Set继承自Collection接口。 Map也属于集合系统，但和Collection接口不同。
 * @author wen
 *
 */
public class ListDemo {

	public static void main(String[] args) {
		//Collection extends Iterable ,所以list跟set都可以用迭代器
		List<String> list = new ArrayList<String>(); //直接指向了空对象{}
		/**
		 * （1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）
		 * (2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新
		 * (3）不支持add和remove方法 --->asList返回的List是Array中的实现的
		 *  * 内部类,而该类并没有定义add和remove方法.另外,为什么修改其中一个,另一个也自动
		 *  * 获得更新了,因为asList获得List实际引用的就是数组
		 */
		List<String> list2 = Arrays.asList();
		//list2.add("sadasd"); //不能使用add以及remove
		list.contains("haha");
		//list.add(null);
		list.add("abc");
		list.add("def");

		Object[] obj = {};
		Object[] obj2 = {};
		System.out.println("{}-------->" + obj); //不同的对象
		System.out.println("{}-------->" + obj2);
		Collections.sort(list);
		System.out.println(list);
		//线程安全原因，get/add等方法都添加了synchronized
		List vector = new Vector<String>();


		List linkedList = new LinkedList();
		Iterator ite = list.iterator();
		while(ite.hasNext()) {
			Object next = ite.next();
			System.out.println(next);
		}
	}
}
