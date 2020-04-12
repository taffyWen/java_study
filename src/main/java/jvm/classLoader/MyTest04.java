package jvm.classLoader;


/**
 * 5.当一个接口初始化时，并不需要起父接口都完成初始化。只有在真正使用到父接口时，（如引用父接口定义的常量时）才会初始化
 *
 */
public class MyTest04
{

    public static void main(String[] args) {

        System.out.println(MyChild4.ss);
    }
}

interface MyParent4{
    public static  int num = 8;
}
interface MyChild4 extends MyParent4{

    public static  String  ss = "ss";
}