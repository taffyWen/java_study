package jmm;

public class MyData {


    /**
     * number 没有使用volatile关键字，对其他线程不具备可见性，因此01示例中main线程会一直执行
     */
    int number = 0;

    public void addTo60() {
        number += 60;
    }

    /**
     * 不实用 synchronized 时，方法没有原子性，计算总金额小于预期
     */
    public synchronized void addPlusPlus(){
        number ++;
    }

}
