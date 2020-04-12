package arithmetic;

/**
 * 快排------------>以此为好的实现方式
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] array = new int[]{7,10,3,5,4,6,2,8,1,12};
        new QuickSort2().quick_sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }
    public void quick_sort(int[] a){
        quick_sort_c(a, 0, a.length - 1);
    }//

    public void quick_sort_c(int[] a, int p, int r){

        if (p >= r) return; //边界值，包含等于

        int q =partition(a, p, r);
        quick_sort_c(a, p, q - 1);
        quick_sort_c(a, q + 1, r);
    }

    public int partition(int [] a, int p, int r){
        int x = a[r];
        int i = p;
        int temp;
        for(int j=p ;j<=r;j++){ //遍历数组，如果小于temp，就把小于的数，放到数组的前面（方式是替换位置）
            if(a[j]<=x){
                // 交换(a[j-1],a[i-1]);
                temp=a[j];
                a[j]=a[i];
                a[i]=temp;
                i++; //最后一次循环会+1，因此最后返回数据应该 -1

            }
        }
        /*//交换(a[r-1,a[i+1-1]);
        temp=a[r];
        a[r]=a[i+1];
        a[i+1]=temp;*/
        return i-1;
    }
}
