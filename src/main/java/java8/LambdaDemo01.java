package java8;

/**
 * @Author wen
 * @Date 2021/5/30
 *
 *  Lambda表达式总结
 *      -> 左边： lambda形参列表参数类型可以省略；如果形参列表只有一个参数，() 可以省略
 *      -> 右边： lambda体应该是有那个{}包起来，如果只有一条语句，return与大括号若有，可以省略
 **/
public class LambdaDemo01 {
    public static void main(String[] args) {

        MyInteface inteface = () -> System.out.println("你好啊");


        inteface.say();
        inteface.hello();

        MyInterface2 interface2 = () -> "wen"; //只有一条可以省略 {}

        String name = interface2.getName();
        System.out.println("返回值的输出结果:" + name);
    }
}
