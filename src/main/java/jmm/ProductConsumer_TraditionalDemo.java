package jmm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统的生产者消费者模式
 */
public class ProductConsumer_TraditionalDemo {


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
            condition.signal();
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
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}