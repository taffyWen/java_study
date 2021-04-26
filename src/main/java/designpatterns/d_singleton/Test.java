package designpatterns.d_singleton;

public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                DoubleCheckSingleton.getInstance();

            }).start();
        }
    }
}
