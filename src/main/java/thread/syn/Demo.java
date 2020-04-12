package thread.syn;

public class Demo {

	public static void main(String[] args) {
		int[] array = new int[] {1,2,6,7,9,10};
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			boolean flag = true;
			for (int j = 0; j < array.length-i-1; j++) {
				count ++;
				if(array[j] > array[j+1]) {
					int m = array[j] ;
					array[j] = array[j+1];
					array[j+1] = m;
					flag = false;
				}
			}
			
			 if(flag) { break; } 
			 
		}
		for (int i : array) {
			System.out.print(i+"\t");
		}
		System.out.print("\n" + count);
	}
}
