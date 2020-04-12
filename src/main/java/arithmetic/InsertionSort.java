package arithmetic;

/**
 * 插入排序
 * @author wen
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,2,9,0,7,4,5,8};
        insertionSort(array);

    }

    public static void insertionSort(int[] a){
        int n = a.length;
        if (n <= 1) return;

        int count = 0;
        for (int i = 1; i < n; i++) {
            int value = a[i]; //从第二个数开始
            int j = i - 1; //比较是从当前值的前一个数开始
            for ( ; j >= 0 ; j--) { //之所以循环，是前面数也只是排序了，但是当前值，可能比第一个数还小
                count ++;
                if (a[j] > value){
                    a[j + 1] = a[j]; //大的值，放到当前值的下一位
                }else {
                    break;
                }
            }
            //把本次循环的值，给到正确的位置
            a[j + 1] = value; //上述不满足条件的最后一个值，-- 了，因此要 + 1 （上述的最早的位置已经后移了）
        }

        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("比较次数" + count);
    }
}
