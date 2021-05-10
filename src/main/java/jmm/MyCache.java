package jmm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 模拟缓存操作
 * 使用读写锁 ===> 未使用
 */
public class MyCache {

    private volatile Map map = new HashMap();

    public void put(int key, Object obj){
        System.out.println(Thread.currentThread().getName() + "\t正在写入---" + key);

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,obj);
        System.out.println(Thread.currentThread().getName() + "\t写入完成---" + key);
    }

    public void get(int key){
        System.out.println(Thread.currentThread().getName() + "\t正在读取===" + key);

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t读取完成===" + key);
    }


    public static void main(String[] args) {

        MyCache myCache = new MyCache();


        for (int i = 0; i < 5; i++) {
            int count = i;
            new Thread(()->{
                myCache.put(count,count);
            },String.valueOf(count)).start();
        }

        for (int i = 0; i < 5; i++) {
            int count = i;
            new Thread(()->{
                myCache.get(count);
            },String.valueOf(count)).start();
        }
    }
}
