package jmm.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试github提交
 * @Author wen
 * @create 2021/5/25 23:27
 */
public class LockSupportDemo4 {

    public static void main(String[] args) {

        Lock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Object obj = new Object();
        Object obj2 = new Object();

        Thread a = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t ----come in");

            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
        },"AA");
        a.start();


        new Thread(()->{

            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t ----通知");

        },"BB").start();
    }
}
