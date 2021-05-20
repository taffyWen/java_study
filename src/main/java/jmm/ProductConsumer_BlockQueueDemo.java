package jmm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者模式 实现 阻塞队列
 */
public class ProductConsumer_BlockQueueDemo {



    public static void main(String[] args) throws InterruptedException {


        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        MyResource myResource = new MyResource(arrayBlockingQueue);

        new Thread(()->{
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        TimeUnit.SECONDS.sleep(5);

        System.out.println();
        System.out.println();

        myResource.stop();
    }

}

class MyResource{

    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<Integer> blockingQueue = null;

    public MyResource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws InterruptedException {

        //声明在外面，避免每次都声明变量
        int num = 0;
        boolean offer;
        while (FLAG){

            num = atomicInteger.incrementAndGet();
            offer = blockingQueue.offer(num,2L,TimeUnit.SECONDS);
            if (offer){
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + num + "成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + num + "失败");
            }

            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("大老板叫停---------------");
    }


    public void myConsumer() throws InterruptedException {
        Integer poll = 0;


        // 叫停后，没有直接结束，还轮训了一次
        while (FLAG){
            poll = blockingQueue.poll(2L,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " \t 消费者队列消费" + poll + "成功");
        }
    }

    public void stop(){
        FLAG = false;
    }
}