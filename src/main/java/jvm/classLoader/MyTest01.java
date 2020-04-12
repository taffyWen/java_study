package jvm.classLoader;

/**
 * 对于静态字段变量来说，只有直接定义该字段的类才会被初始化
 * 对一个类的初始化，要求其父类全部都已经初始化完毕
 *
 * -XX:+TraceClassLoading 用于追踪类的加载信息并打印出来
 */
public class MyTest01 {


    public static void main(String[] args) {

        System.out.println(Child.str2);
    }
}

class Parent{

    public static String str1 = "hello ";
    static {
        System.out.println("Parent static block");
    }

}

class Child extends Parent{

    public static String str2 = "world!";
    static {
        System.out.println("Child static block");
    }
}