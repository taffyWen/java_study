package io;

import java.io.*;

/**
 * 文件字符输入流
 * 1. 创建源
 * 2. 选择流
 * 3. 操作
 * 4. 关闭流
 */
public class IO_Study04_FileReader {

    public static void main(String[] args) {
        File file = new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/hello3.txt");
        Reader reader = null;
        try {
            reader = new FileReader(file);
            char[] flush = new char[3];
            int temp;
            //reader.read(flush); //read() 读一个字符,不用担心一个字符究竟是几个字节
            while ((temp = reader.read(flush)) != -1){

                System.out.print(new String(flush, 0, temp));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
