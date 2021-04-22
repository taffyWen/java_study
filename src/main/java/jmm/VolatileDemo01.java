package jmm;

import java.util.concurrent.TimeUnit;

public class VolatileDemo01 {


    public static void main(String[] args) {

        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ==========");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t 更改number" + myData.number);
        },"测试线程").start();

        while (myData.number == 0){
            // main线程一直等待循环，直到number的值不等于0
        }

        System.out.println(Thread.currentThread().getName() + "\t  is over");
    }

}



