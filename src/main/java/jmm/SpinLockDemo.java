package jmm;


import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁 demo
 */
public class SpinLockDemo {


    // 加锁与释放锁必须是针对同一个 对象？
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName() + "等待释放锁");
        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "释放锁");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "开始");
            spinLockDemo.myLock();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();

        },"AA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "开始");
            spinLockDemo.myLock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();

        },"BB").start();

    }
}
