package jvm.classLoader;

/**
 * 类的初始化顺序
 * 
 * 首先加载InitClass2类，因此会执行InitClass2类中的static块。接着执行new
 * MyClass()，而MyClass类还没有被加载，因此需要加载MyClass类。
 * 在加载MyClass类的时候，发现MyClass类继承自InitClass2类，但是由于InitClass2类已经被加载了，所以只需要加载MyClass类，
 * 那么就会执行MyClass类的中的static块。在加载完之后，就通过构造器来生成对象。
 * 而在生成对象的时候，必须先初始化父类的成员变量，因此会执行InitClass2中的Person person = new Person()，
 * 而Person类还没有被加载过，因此会先加载Person类并执行Person类中的static块，接着执行父类的构造器，完成了父类的初始化，
 * 然后就来初始化自身了，因此会接着执行MyClass中的Person person = new Person()，最后执行MyClass的构造器。
 *test static
 * myclass static
 * person static
 * person Test
 * test constructor
 * person MyClass
 * myclass constructor
 *
 * 父类静态代码块(包括静态初始化块，静态属性，但不包括静态方法)
 * 子类静态代码块(包括静态初始化块，静态属性，但不包括静态方法 )
 * 父类非静态代码块( 包括非静态初始化块，非静态属性 )
 * 父类构造方法
 * 子类非静态代码块 ( 包括非静态初始化块，非静态属性 )
 * 子类构造方法
 *
 */
public class InitClass2 {
	Person person = new Person("Test");
	static {
		System.out.println("test static");
	}
	public InitClass2() {
		System.out.println("test constructor");
	}
	public static void main(String[] args) {
		new MyClass();
	}
}

class Person {
	static {
		System.out.println("person static");
	}
	public Person(String str) {
		System.out.println("person " + str);
	}
}

class MyClass extends InitClass2 {
	Person person = new Person("MyClass");
	static {
		System.out.println("myclass static");
	}
	public MyClass() {
		System.out.println("myclass constructor");
	}
}
