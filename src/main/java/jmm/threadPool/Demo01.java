package jmm.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3); //一池固定个数的线程池
        ExecutorService executor2 = Executors.newSingleThreadExecutor(); //一池1个处理线程的线程池
        ExecutorService executor3 = Executors.newCachedThreadPool(); //1个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程

        for (int i = 1; i <= 10; i++) {

            executor3.execute(()->{
                System.out.println(Thread.currentThread().getName() + "\t办理业务");
            });
        }

    }
}
