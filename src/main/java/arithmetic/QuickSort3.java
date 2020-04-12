package arithmetic;

public class QuickSort3 {

    public static void main(String[] args) {
        int[] array = new int[]{7,10,3,5,4,6,2,8,1,12};
        new QuickSort3().quickSort(array, 0 ,array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }
    public void quickSort(int[] array, int p, int r){
        if (p >= r) return ;
        int q = partition(array, p, r);
        quickSort(array, 0, q - 1);
        quickSort(array, q + 1, r);
    }

    public int partition(int[] array, int p, int r){
        int x = array[p];
        int j = r;
        int temp ;
        for (int i = r; i >= 0; i--) {
            if (array[i] >= x){
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j --;
            }
        }
        return j + 1;
    }
}
