package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * IO流标准步骤
 * 1. 创建源
 * 2. 选择流
 * 3. 操作
 * 4. 释放资源
 *
 */
public class IO_Study01_FileInputStream {

    public static void main(String[] args) throws IOException {
        String uri = "D:/workspace/workspace2018-9/restudy/src/main/java/io/hello.txt";
        File file = new File(uri);

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int temp ;
            while ((temp = fileInputStream.read()) != -1){

                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileInputStream.close();
        }

    }
}
