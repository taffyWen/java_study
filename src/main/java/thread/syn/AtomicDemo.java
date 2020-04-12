package thread.syn;

public class AtomicDemo{
//    AtomicLong count =  new AtomicLong(0);
    int count = 0;

    void add10K(){
        int idx = 0;
        while (idx ++ < 10000){
//            count.getAndIncrement();
            count += 1;
        }
        System.out.println("count--------->" + count);
    }

    public static void main(String[] args) {

        AtomicDemo atomicDemo = new AtomicDemo();
        new Thread(()->{
            atomicDemo.add10K();

        },"线程1").start();
       /* new Thread(()->{
            atomicDemo.add10K();

        },"线程2").start();*/

    }

}

class SimulatedCAS{
    volatile int count;
    volatile int newValue;
    // 实现 count+=1
    void addOne(){
        do {
            newValue = count+1; //①
        }while(count !=
                cas(count,newValue)); //②
    }
    // 模拟实现 CAS，仅⽤来帮助理解
    synchronized int cas(
            int expect, int newValue){
// 读⽬前 count 的值
        int curValue = count;
// ⽐较⽬前 count 值是否 == 期望值
        if(curValue == expect){
// 如果是，则更新 count 的值
            count= newValue;
        }
// 返回写⼊前的值
        return curValue;
    }
}