package jmm.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait() / notify()  必须 在synchronized 代码块中，不然会出现 IllegalMonitorStateException
 * 必须先wait 在notify才能被唤醒
 * @Author wen
 * @create 2021/5/25 23:27
 */
public class LockSupportDemo2 {

    public static void main(String[] args) {

        Lock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Object obj = new Object();
        Object obj2 = new Object();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){

                System.out.println(Thread.currentThread().getName() + "\t ----come in");

                try {
                    obj.wait();  // 必须 在synchronized 代码块中，不然会出现 IllegalMonitorStateException
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        },"AA").start();

        new Thread(()->{

            synchronized (obj){
                obj.notify();  // 必须 在synchronized 代码块中，不然会出现 IllegalMonitorStateException
                System.out.println(Thread.currentThread().getName() + "\t ----通知");
            }

        },"BB").start();
    }
}
