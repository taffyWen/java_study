package arithmetic.queue.demo;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 1, 2, 3};
        insertionSort(arr);
    }

    public static void insertionSort(int[] a) {

        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = i -1;

            for (; j >= 0; --j) {
                    if (a[j] > value) {
                        a[j + 1] = a[j];//数据移动
                    } else {
                        break;
                    }
            }
            a[j + 1] = value;//插入数据
        }
        for (int num : a ) {
            System.out.println(num);
        }
    }
}
