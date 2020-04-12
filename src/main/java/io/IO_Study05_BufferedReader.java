package io;

import java.io.*;

/**
 * 文件字符输入流
 * 1. 创建源
 * 2. 选择流
 * 3. 操作
 * 4. 关闭流
 */
public class IO_Study05_BufferedReader {

    public static void main(String[] args) {
        File file = new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/hello3.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            //reader.read(flush); //read() 读一个字符,不用担心一个字符究竟是几个字节
            while ((line = reader.readLine()) != null){

                System.out.print(line);
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
