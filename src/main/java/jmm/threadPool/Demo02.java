package jmm.threadPool;

import java.util.concurrent.*;

public class Demo02 {

    public static void main(String[] args) {

        // 获取本机cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        /**
         * 最大线程数 配置
         * cpu密集型  CPU核数 * 2
         * IO密集型：大多线程阻塞，故可多配置线程数
         *      CPU核数/(1-阻塞系数)    阻塞系数 0.8-0.9
         *
         *      不使用 Executors 创建线程池的原因，是 几个线程池创建的阻塞队列不可控，阻塞队列  Integer.MAX_VALUE
         *
         */

        Executors.newFixedThreadPool(2);
        ExecutorService poolExecutor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors() * 2 +1,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), //阻塞队列大小
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());//丢弃策略

        try {
            for (int i = 1; i <= 60; i++) {
                final int num = i;
                poolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t办理业务\t" + num);
                });
            }
        } finally {
            poolExecutor.shutdown();
        }

    }
}
