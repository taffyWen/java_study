package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * countDown 每次减1
 *  await 计数为0时被唤醒
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        // 初始化计数器
        CountDownLatch count = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t被灭国");
                // 计数器减1
                count.countDown();
            },String.valueOf(i)).start();

        }
        try {
            // 调用的线程阻塞，当计数器为0时，调用await方法从阻塞将会被唤醒
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************秦国统一");
    }
}
