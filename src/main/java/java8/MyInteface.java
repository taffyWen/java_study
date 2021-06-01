package java8;

public interface MyInteface {

    void say();


    default void hello(){
        System.out.println("我是接口的默认方法");
    }
}
