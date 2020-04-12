package thread.concurrence;

public class Lock01 {

    static long value = 0L;

    public static void main(String[] args) {
        new Thread(()->{

            for (int i = 0; i < 100; i++) {

                System.out.println(get());
                Lock01.addOne();
            }
        }).start();
    }

    synchronized static long  get(){
        return value;
    }

    synchronized static void addOne(){
        value += 1;
    }


}
