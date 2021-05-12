package jmm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * 1. 用于多个共享资源互斥使用  ==》抢车位
 * 2. 并发线程的控制
 */

public class SemaphoreDemo {


    public static void main(String[] args) {
        //默认非公平锁
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");

                    TimeUnit.SECONDS.sleep(2);

                    System.out.println(Thread.currentThread().getName() + "\t离开车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }

    }
}
