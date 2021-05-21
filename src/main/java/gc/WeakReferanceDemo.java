package gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferanceDemo {


    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();

        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(o1,referenceQueue);

        System.out.println(o1);

        o1 = null;
        System.gc();
        System.gc();
        System.gc();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
