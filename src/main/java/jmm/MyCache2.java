package jmm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 模拟缓存操作
 * 使用读写锁
 */
public class MyCache2 {

    private volatile Map map = new HashMap();

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(int key, Object obj){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入---" + key);
            TimeUnit.MILLISECONDS.sleep(100);
            map.put(key,obj);
            System.out.println(Thread.currentThread().getName() + "\t写入完成---" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(int key){

        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t正在读取===" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成===" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }


    public static void main(String[] args) {

        // 注意别写成 MyCache 了
        MyCache2 myCache2 = new MyCache2();


        for (int i = 0; i < 5; i++) {
            int count = i;
            new Thread(()->{
                myCache2.put(count,count);
            },String.valueOf(count)).start();
        }

        for (int i = 0; i < 5; i++) {
            int count = i;
            new Thread(()->{
                myCache2.get(count);
            },String.valueOf(count)).start();
        }
    }
}
