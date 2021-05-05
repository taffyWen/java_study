package jmm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo01 {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);
        System.out.println(integer.compareAndSet(5,100));
        System.out.println(integer.compareAndSet(5,201));
        System.out.println(integer.get());


        //增加版本号
        AtomicStampedReference stampedReference = new AtomicStampedReference(1,1);

        //可以把一般类转为atomic类型
        AtomicReference<Person> atomicReference = new AtomicReference<>();

    }
}
