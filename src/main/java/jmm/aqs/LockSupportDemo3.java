package jmm.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition.await();  // 必须 lock，不然会出现 IllegalMonitorStateException
 * condition.signal();  // 必须 lock 代码块中，不然会出现 IllegalMonitorStateException
 * @Author wen
 * @create 2021/5/25 23:27
 */
public class LockSupportDemo3 {

    public static void main(String[] args) {

        Lock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Object obj = new Object();
        Object obj2 = new Object();

        new Thread(()->{
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");

                try {
                    condition.await();  // 必须 lock，不然会出现 IllegalMonitorStateException
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } finally {
                reentrantLock.unlock();
            }
        },"AA").start();

        new Thread(()->{

            reentrantLock.lock();
            try {
                condition.signal();  // 必须 lock 代码块中，不然会出现 IllegalMonitorStateException
                System.out.println(Thread.currentThread().getName() + "\t ----通知");
            } finally {
                reentrantLock.unlock();
            }

        },"BB").start();
    }
}
