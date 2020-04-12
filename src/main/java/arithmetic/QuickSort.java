package arithmetic;

/**
 * 快速排序 -----有问题
 */
public class QuickSort {

    public static void quickSort(int[] array, int left, int right){

        if (left > right) return;
        int n = 3;//(int) (Math.random() * (right - left )) + left;
        int temp = array[n]; //存储基数temp
        int i = left;
        int j = right;
        while (i < n && j > n){

            //从右边向左边查找，找比基数小的
            while (j > n && array[j] >= temp){
                j --;
            }

            while (i < n && array[i] <= temp){
                i ++;
            }

            //交换两个数值
            int t = array[j];
            array[j] = array[i];
            array[i] = t;

        }
        //array[left] = array[i];
        array[i] = temp;

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static void main(String[] args) {
        int[] array = new int[]{8,10,2,3,6,1,5};
        /*for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            System.out.print(array[i] + " ");
        }*/
        System.out.println("-------------------------------------");
        quickSort(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
