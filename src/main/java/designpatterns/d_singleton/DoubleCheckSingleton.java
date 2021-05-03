package designpatterns.d_singleton;

public class DoubleCheckSingleton {


    //初始化时，不加载该对象，延迟加载，使用到时才创建
    private static DoubleCheckSingleton doubleCheckSingleton;

    private DoubleCheckSingleton() {
        System.out.println("DoubleCheckSingleton---构造器");
    }

    // DCL 双端检查
    /**
      对比懒汉模式在整个方法上面加 synchronized ，这里使用代码块对的方式，但是可能出现指令重排带来的问题。
     doubleCheckSingleton = new DoubleCheckSingleton(); ===》分为3个步骤
        1. 分配内存空间
        2. 初始化对象
        3. 设置instance 指向分配嗯内存地址 ，此时，instance != null
     即：先对doubleCheckSingleton 进行了赋值，但是对象还没有真正的生成，后续使用的话，会出现空指针异常
     可以使用 volatile
     */
    public static  DoubleCheckSingleton getInstance() {
        if (doubleCheckSingleton == null){
            synchronized (DoubleCheckSingleton.class){
                if(doubleCheckSingleton == null) {
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }
}
