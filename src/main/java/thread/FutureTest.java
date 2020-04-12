package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 支持获得任务执行结果。
 * 烧水泡茶
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask ft2 = new FutureTask(new T2Task());

        FutureTask ft1 = new FutureTask(new T1Task(ft2));

        new Thread(ft1).start();
        new Thread(ft2).start();

        System.out.println(ft1.get());

    }
}

// T1Task需要执⾏的任务：
// 洗⽔壶、烧开⽔、泡茶
class T1Task implements Callable<String>{

    FutureTask<String> ft2;

    // T1任务需要T2任务的FutureTask
    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1:洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1:烧开水");
        TimeUnit.SECONDS.sleep(10);

        //获取T2线程的茶叶
        String tf = ft2.get();
        System.out.println("T1:拿到茶叶：" + tf);

        System.out.println("T1:泡茶----------");
        return "上茶：" + tf;
    }
}

class T2Task implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("T2:洗茶壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2:洗茶杯");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2:拿茶叶");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}
