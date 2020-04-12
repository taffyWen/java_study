package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池实例
 */
public class MyThreadPool {
    //利⽤阻塞队列实现⽣产者-消费者模式
    BlockingQueue<Runnable> blockingQueue ;
    //保存内部⼯作线程
    List<WorkerThread> workerThreadList = new ArrayList<>();

    public MyThreadPool(BlockingQueue<Runnable> blockingQueue, int size) {
        this.blockingQueue = blockingQueue;
        for (int i = 0; i < size; i++) {
            WorkerThread workerThread = new WorkerThread();
            new Thread(workerThread,"线程" + i).start();
            workerThreadList.add(workerThread);
        }
    }

    void execute(Runnable runnable) throws InterruptedException {
        blockingQueue.put(runnable);
    }

    public static void main(String[] args) throws Exception {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(9);
        MyThreadPool myThreadPool = new MyThreadPool(blockingQueue, 2);
        myThreadPool.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行了1-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行了2-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            System.out.println("执行了3-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行了4-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            System.out.println("执行了5-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行了6-->" + Thread.currentThread().getName());
        });
        myThreadPool.execute(()->{
            System.out.println("执行了7-->" + Thread.currentThread().getName());
        });
    }
    class WorkerThread implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    Runnable runnable = blockingQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
