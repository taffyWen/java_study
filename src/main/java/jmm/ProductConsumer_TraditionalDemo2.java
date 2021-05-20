package jmm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个条件
 */
public class ProductConsumer_TraditionalDemo2 {

    /**
     * 需求：
     * AA打印5次，BB打印10次，CC打印15次
     * 来5轮
     */

    public static void main(String[] args) {

        ShareResource resource = new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.print5();
            }

        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.print10();
            }

        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.print15();
            }

        },"CC").start();

    }
}

class ShareResource{

    private int num = 1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 判断 条件
            while (num != 1){
                c1.await();
            }

            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" +i);
            }

            //通知
            num = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断 条件
            while (num != 2){
                c2.await();
            }

            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" +i);
            }

            //通知
            num = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断 条件
            while (num != 3){
                c3.await();
            }

            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" +i);
            }

            //通知
            num = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}