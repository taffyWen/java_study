package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 测试 ArrayList 是线程不安全的
 */
public class ContainerNoSafeDemo {


    public static void main(String[] args) {

        /**
         * 可能出现问题：
         * java.util.ConcurrentModificationException
         */
        List<String> list = new ArrayList<>();

        // 转为线程安全
        List<String> strings = Collections.synchronizedList(new ArrayList<>());


        List<String> synchronizedList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();

        }
        /**
         * 解决方法：
         * 1. 使用vector，add方法加了 synchronized 关键字
         * 2. Collections.synchronizedList
         * 3. new CopyOnWriteArrayList
         */
    }
}
