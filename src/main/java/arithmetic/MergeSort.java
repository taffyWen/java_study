package arithmetic;

/**
 * 归并排序
 */
public class MergeSort {

    public void mergeSort(int[] a){

        mergeSortHalf(a, 0, a.length - 1);
    }

    /**
     * 递归调用函数
     * @param a
     * @param left
     * @param right
     */
    public void mergeSortHalf(int[] a, int left, int right){

        //递归终止条件
        if (left >= right) return;

        int mid = (left + right)/2;
        //分治递归
        mergeSortHalf(a, left, mid);
        mergeSortHalf(a, mid + 1, right);

    }

    public void merge(int[] a, int[] b, int[] c){

    }
}
