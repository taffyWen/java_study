package arithmetic;

/**
 * 计数排序
 *
 * 将要排序的数据分到几个有序的桶里，每个桶里的数据再单独进行排序。
 * 桶内排完序之后，再把每个桶里的数据按照顺序依次取出，组成的序列就是有序的了。
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] array = new int[]{7,10,3,5,4,6,2,8,1,12};
        countingSort(array, array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }
    public static void countingSort(int[] a, int n) {
        if (n <= 1) return;
// 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        int[] c = new int[max + 1]; // 申请一个计数数组c，下标大小[0,max]
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }
// 计算每个元素的个数，放入c中
        for (int i = 0; i < n; ++i) {
            c[a[i]]++;
        }
// 依次累加
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i-1] + c[i];
        }
// 临时数组r，存储排序之后的结果
        int[] r = new int[n];
// 计算排序的关键步骤，有点难理解
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }
// 将结果拷贝给a数组
        for (int i = 0; i < n; ++i) {
            a[i] = r[i];
        }
    }
}
