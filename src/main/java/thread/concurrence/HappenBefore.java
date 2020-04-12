package thread.concurrence;

public class HappenBefore {

    public static void main(String[] args) {
        // 以下代码来源于【参考 1】
        VolatileExample volatileExample = new  VolatileExample();

        volatileExample.writer();
        volatileExample.reader();



    }
}

class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            System.out.println("x------>" + x);
        }
    }
}
