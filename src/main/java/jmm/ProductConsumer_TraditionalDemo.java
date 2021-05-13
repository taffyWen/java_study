package jmm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统的 线程通讯  生产者消费者模式
 *
 * 多线程的判断必须使用 while ==》不然会出现
 */
public class ProductConsumer_TraditionalDemo {


    /**
     * 要求：两个线程，一个加一，一个减一，执行5轮
     * @param args
     */
    public static void main(String[] args) {

        MyShareData shareData = new MyShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"AA").start();

        new Thread(()->{

            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"CC").start();

        new Thread(()->{

            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }

}


class MyShareData{

    private int num;
    private Lock lock = new ReentrantLock();
    // 锁的condition
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {

        lock.lock();
        try {
            while (num != 0){
                // 当前操作===》等待别人通知我，才从等待中结束
                condition.await();
            }
            num ++ ;
            System.out.println(Thread.currentThread().getName() + "\t" + num + "\t" + lock.hashCode());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {

        lock.lock();
        try {
            while (num == 0){
                // 等待，
                condition.await();
            }
            num -- ;
            System.out.println(Thread.currentThread().getName() + "\t" + num + "\t" + lock.hashCode());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}