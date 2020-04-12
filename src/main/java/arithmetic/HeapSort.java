package arithmetic;

import java.util.Arrays;

/**
 * 堆化
 * 堆是一个完全二叉树；
 * 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值。
 * 对于每个节点的值都大于等于子树中每个节点值的堆，我们叫作“大顶堆”。对于每个节点的值都小于等于子树中每个节点值的堆，我们叫作“小顶堆”。
 *
 * 排序sort有问题
 */
public class HeapSort {

    private int[] array; //数组，从下标1开始
    private int capacity; //堆可以存放的最大个数
    private int count; //堆中已经储存的数据个数

    public HeapSort(int capacity) {
        this.capacity = capacity;
        array = new int[capacity + 1];
        count = 0;
    }

    //动态堆化--》只能满足堆的条件。并不能
    boolean insert(int data){
        if (count >= capacity) return false;
        count ++;
        array[count] = data;
        int i = count;
        int temp = 0; //存储临时变量值
        while (i/2 > 0 && array[i/2] < array[i]){
            temp = array[i/2];
            array[i/2] = array[i];
            array[i] = temp;
            i = i/2; //递归的值的变更
        }
        return true;
    }

    //建堆
    void buildHeap(int[] array, int n){
        for (int i = n/2; i >= 1 ; i--) {
            heapify(array, n, i);
        }
    }

    //堆化
    void heapify(int[] array, int n, int i) {

        while (true) {
            int max = i;
            if (i * 2 <= n && array[2 * i] > array[i]){
                max = i * 2;
            }
            if (i*2 + 1 <= n && array[max] < array[i*2 +1]){
                max = i*2 + 1;
            }
            if ( i == max){
                break;
            }
            i = max;
        }
    }
    //排序
    void sort(int[] array, int n){
        buildHeap(array, n);

        int k = n;
        int temp = 0;
        while (k > 1){
            //交换最后一位，跟未排序的最后一位(每次循环排序一次)
            temp = array[k];
            array[k] = array[1];
            array[1] = temp;
            k --;
            heapify(array, k, 1);
        }

    }

    @Override
    public String toString() {
        return "HeapSort{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) {
        int num = 10;
        int[] array = {11,12,27,81,90,91,94,33,77,9};
        HeapSort heapSort = new HeapSort(array.length);
        for (int i = 0; i < array.length; i++) {
            int data = array[i];
            heapSort.insert(data);
        }

        System.out.println(heapSort.toString());
        heapSort.sort(array, array.length ); //排序这里，数组长度不好传值。排序方法从1开始，数组是从0开始的
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
