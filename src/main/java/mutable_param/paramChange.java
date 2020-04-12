package mutable_param;

/**
 * @Author wen
 * @create 2020/3/22 9:29
 * java 中除了基本数据类型 + String ，其他的都是不可变的。也就是传入方法参数中，其本身不改变
 */
public class paramChange {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 7, 9};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        change(arr);
        System.out.println("\n------------更改后--------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }

    }

    public static void change(int[] arr){
        arr[0] = 100;
    }
}
