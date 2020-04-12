package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk可视化工具：jconsole、jvisualvm
 */
public class JConsole {


    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024]; //内存占位对象，大约64kb
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
