package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStreamUtil {

	
	/**
	 * 读取文本文件.到内存中。只使用FileInputStream
	 * @param pathname
	 * @return
	 */
	public static String readStreamFile(String pathname) {
		
		
		File file = new File(pathname);
		if(!file.exists()) {
			System.out.println("文件不存在");
		}
		StringBuilder  stringBuilder = new StringBuilder();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			int len = 0;
			byte[] buffer = new byte[512];
			while((len = fileInputStream.read(buffer)) != -1) {
				stringBuilder = stringBuilder.append(new String(buffer,0,len,"utf-8"));
			}
			fileInputStream.close();
			System.out.println(stringBuilder);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
	
	
	/**
	 * 文件输出
	 * @param pathname 输出路径
	 * @param outputString	输出内容
	 */
	public static void outputStream(String pathname, String outputString) {

		try {
			File file = new File(pathname);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(outputString);
			fileWriter.flush();
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * fileReader 读取文本
	 * @param pathname
	 * @return
	 */
	public static String fileReader(String pathname) {
		File file = new File(pathname);
		if(!file.exists()) {
			System.out.println("文件不存在");
		}
		try {
			FileReader fileReader = new FileReader(pathname);
			int len = 0;
			char[] input=new char[500];
			while((len = fileReader.read(input)) != -1) {
				
				System.out.println(new String(input,0,len));
			}
			
			fileReader.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pathname;
		
	}
}
