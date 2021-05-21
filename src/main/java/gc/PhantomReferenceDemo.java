package gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();

        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(obj,referenceQueue);

        System.out.println(obj);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        obj = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("============垃圾回收");

        System.out.println(obj);
        System.out.println(phantomReference.get());
        // 对象被回收后，对象添加到队列中，可以在对象被销毁后做一些操作
        System.out.println(referenceQueue.poll());
    }
}
