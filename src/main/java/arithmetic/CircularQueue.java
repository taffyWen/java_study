package arithmetic;

/**
 * 数组模拟环形队列
 * 1.队列满的条件：(tail+1)%n = head
 * 2.队列为空条件：tail = head
 * 3.队列中有效数据个数：(tail - head + n ) % n
 * 4.最大容量为 n-1
 * 5.添加或者删除，移动的值都是 (X + 1) % n
 */
public class CircularQueue {

    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;

    // head表示队头下标，tail表示队尾下标(不含最后一个元素)
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
// 队列满了
        int temp = (tail + 1) % n;
        if (temp == head) return false;
        items[tail] = item;
        tail = temp;
        return true;
    }

    // 出队
    public String dequeue() {
    // 如果head == tail 表示队列为空
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}
