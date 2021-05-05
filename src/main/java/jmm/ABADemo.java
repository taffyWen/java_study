package jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    //增加版本号
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference(121,1);


    public static void main(String[] args) {

        new Thread(()->{

            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);

            try {
                // 暂停1s 线程t3
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(121,99,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号：" + stampedReference.getStamp());
            System.out.println("当前值：" + stampedReference.getReference());
            stampedReference.compareAndSet(99,121,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 第三次版本号：" + stampedReference.getStamp());

        },"t3").start();

        new Thread(()->{

            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);

            try {
                // 暂停3s 线程t4
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean result = stampedReference.compareAndSet(121, 73, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "修改结果：" + result + "当前最新版本号：" + stampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号：" + stampedReference.getStamp());
        },"t4").start();

    }
}
