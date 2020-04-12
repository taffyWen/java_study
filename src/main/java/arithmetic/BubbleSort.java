package arithmetic;

/**
 * 冒泡排序
 * @author wen
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] values = new int[] {3,1,6,2,9,0,7,4,5,8};
		sort1(values);
		sort2(values);
	}
	//最优方法
	public static void sort1(int[] values) {
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			int temp = 0;
			boolean flag = true;//避免已经排好序了，还要循环
			for (int j = 0; j < values.length - 1 - i; j++) {
				count ++;
				if(values[j] > values[j+1]) {
					temp = values[j];
					values[j] = values[j+1];
					values[j+1] = temp;
					flag = false;
				}
				//System.out.println(Arrays.toString(values));
			}
			if(flag) {
				break;
			}
		}
		System.out.println(count);
	}
	
	
	public static void sort2(int[] values) {
		int count = 0;
		for (int i = values.length - 1; i > 0; i--){
			int s = 0;
            for (int j = 0; j < i; ++j){ // i= values.length – 1，j+1<i+1=values.length
            	count ++ ;
                if (values[j + 1] < values[j]){
                    s = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = s;
                }
                //System.out.println(Arrays.toString(values));  
            }
        }
		System.out.println(count);
	}
	
}
