package jvm.classLoader;


/**
 * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会出发定义常量的类的初始化
 * 注意：将常量放到A_ClassLoaderDemo02的常量池中后，A_ClassLoaderDemo02与MyChild就没有关系了
 * 删除MyChild的class文件也可以正常打印
 *
 */
public class MyTest02 {

    public static void main(String[] args) {
        System.out.println(MyChild.str);
    }
}

class MyChild{
    public static final String str = "hello world";
    public static final int num = 6;
    public static final char cc = 'c';

    static {
        System.out.println("MyChild static block");
    }
}