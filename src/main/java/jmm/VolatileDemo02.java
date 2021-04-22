package jmm;

import java.util.concurrent.TimeUnit;

public class VolatileDemo02 {


    public static void main(String[] args) {

        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    myData.addPlusPlus();
                }
            },"线程：" + String.valueOf(i)).start();
        }

        // main本身是一个单独线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t  number 的值：" + myData.number) ;
    }
}


