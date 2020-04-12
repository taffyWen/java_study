package arithmetic.queue.demo;

import java.util.Scanner;

public class CircularQueueDemo{
    private int n = 0;
    private String[] array;

    private int head = 0;
    private int tail = 0;

    public static void main(String[] args) {
        CircularQueueDemo queueDemo = new CircularQueueDemo(8);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("请输入一个数");
            String temp = scanner.next();
            queueDemo.list();
        }
    }

    public CircularQueueDemo(int capacity){
        array = new String[capacity];
        this.n = capacity;

    }

    public void list(){
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + "/t");
        }
    }
    public boolean isEmpty(){
        if(head == tail){
            return true;
        }
        return false;
    }

    /**
     * 入队
     * @return
     */
    public boolean enqueue(String str){

        //满了,条件是tail+1取余
        if((tail + 1) % n == head){
            return false;
        }
        array[tail] = str;
        tail = (tail + 1) % n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if(isEmpty()){
            return null;
        }
        String temp = array[head];
        head = (head + 1) % n;
        return temp;
    }
}
