package jmm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 有返回值的线程
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask,"AA").start();

        //同一个future，不会再次打印  【come in callable=======等待处理】
        new Thread(futureTask,"BB").start();

        Object o = futureTask.get();
        System.out.println(o);

        //主线程等待 future 执行完，因为先  futureTask.get();
        System.out.println(Thread.currentThread().getName() + "\t =======");


    }
}


class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tcome in callable=======等待处理");
        TimeUnit.SECONDS.sleep(1);
        return 1024;
    }
}