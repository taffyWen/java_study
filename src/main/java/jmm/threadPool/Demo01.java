package jmm.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3); //一池固定个数的线程池
        ExecutorService executor2 = Executors.newSingleThreadExecutor(); //一池1个处理线程的线程池
        ExecutorService executor3 = Executors.newCachedThreadPool(); //1池1个处理线程的线程池

        for (int i = 1; i <= 10; i++) {

            executor3.execute(()->{
                System.out.println(Thread.currentThread().getName() + "\t办理业务");
            });
        }

    }
}
