package gc;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {


        Integer integer = new Integer(2);
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put(integer,"我是WeakHashMap");

        // 测试 weakHashMap 是否被回收，不能有一下注释代码，即integer对象不能被其他对象使用
 /*       Map map = new HashMap();
        map.put(integer,"我是0");
        System.out.println(map);*/

        integer = null;
        //System.out.println(integer);
        //System.out.println(map);



        System.gc();


        TimeUnit.SECONDS.sleep(1);

        System.out.println("哈哈哈"+weakHashMap);
    }
}
