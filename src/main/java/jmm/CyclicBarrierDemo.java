package jmm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 可循环使用的屏障，当线程打到屏障后，所有被拦截的线程才会继续干活
 *
 * 对比：CountDownLatch  一直减
 * 此处，一直加
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        // 达到最后的屏障后，才执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("----------召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(()->{

                System.out.println("集齐第" + finalI + "龙珠");
                try {

                    //在未达到屏障前，线程等待
                    cyclicBarrier.await();

                    //达到屏障后，才继续执行线程
                    System.out.println(finalI + "任务结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
