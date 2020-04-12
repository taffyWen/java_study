package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilePutStream {

	
	public static void main(String[] args) {
		/*OutputStream fos;
		try {
			fos = new FileOutputStream("text.txt");
			String str="好好学习，天天向上";
			byte[] words=str.getBytes();
			fos.write(words, 0, words.length);
			fos.close();
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*try {
			FileInputStream fis =new FileInputStream("text.txt");
			int num = 24;
			byte data[] = new byte[num];
			while(fis.read(data, 0, num) != -1){
				System.out.println(new String(data,"utf-8"));
			}
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		try {
			FileWriter fw=new FileWriter("copy2.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write("你好啊，mable！");
			bw.write("我喜欢上你怎么办？");
			bw.newLine();//换行
			bw.write("那就一边玩去!!!。");
			bw.flush();//刷新缓存区
			bw.close();//没有fluse()或者close()，至少有一个。不然文件是空的
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
