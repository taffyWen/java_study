package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t被灭国");
                count.countDown();
            },String.valueOf(i)).start();

        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************秦国统一");
    }
}
