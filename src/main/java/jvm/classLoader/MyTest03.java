package jvm.classLoader;


/**
 * 4.对于数组来说，其类型是由JVM在运行期动态生成的
 *
 */
public class MyTest03 {

    public static void main(String[] args) {

        MyChild3[] myChild3 = new MyChild3[3];

        System.out.println(myChild3.getClass());
        System.out.println(myChild3.getClass().getClassLoader());
    }
}

class MyChild3{

    static {
        System.out.println("MyChild static block");
    }
}