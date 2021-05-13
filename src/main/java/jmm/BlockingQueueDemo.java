package jmm;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));

        // 产生一个，得等消费才能再生产
        BlockingQueue syno = new SynchronousQueue();

        new Thread(()->{
            try {
                System.out.println("生产一个"+ 1);
                syno.put(1); //队列阻塞，等待消费

                System.out.println("生产一个"+ 2);
                syno.put(2);

                System.out.println("生产一个"+ 3);
                syno.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("获取："+syno.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("获取："+syno.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("获取："+syno.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
