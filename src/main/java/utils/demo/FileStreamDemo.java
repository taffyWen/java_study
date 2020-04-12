package utils.demo;

import utils.FileStreamUtil;

public class FileStreamDemo {

	
	public static void main(String[] args) {
		
		String pathname = "D:\\ioFile\\test1.txt";
		//String str = FileStreamUtil.readStreamFile(pathname);
		FileStreamUtil.fileReader(pathname);
	}
}
