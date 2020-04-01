package arithmetic;

import java.util.Arrays;

/**
 * 二分法查找
 * @author wen
 *
 */
public class BinarySearch {

	
	public static void main(String[] args) {
		int[] arrays = {30,20,50,48,56,94,11,3,7,101,521}; 
		Arrays.sort(arrays);
		//System.out.println(Arrays.toString(arrays));
		System.out.println(myBinarySearch(arrays, 101));
	}
	
	public static int myBinarySearch(int[] arrays, int value) {
		int min = 0;
		int max = arrays.length-1;
		while(min <= max) {
			int middle = (min + max)/2;
			if(arrays[middle] == value) {
				return middle;
			}
			if(arrays[middle] < value) {
				min = middle + 1;
			}
			if(arrays[middle] > value) {
				max = middle - 1;
			}
		}
		return -1;
	}
}
