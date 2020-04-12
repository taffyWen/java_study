package io;

import java.io.*;

/**
 * 文件字节输入流
 * IO流标准步骤
 * 1. 创建源
 * 2. 选择流
 * 3. 操作
 * 4. 释放资源
 */
public class IO_Study05_BufferedInput {

    public static void main(String[] args) throws IOException {
        //1.创建源
        String uri = "D:/workspace/workspace2018-9/restudy/src/main/java/io/hello2.txt";
        File file = new File(uri);
        //2. 选择流
        InputStream fileInputStream = null;
        try {
            fileInputStream = new BufferedInputStream(new FileInputStream(file));
            //3. 操作
            byte[] flush = new byte[1024];//缓存容器
            int length ;
            int count = 0;
            while ((length = fileInputStream.read(flush)) != -1){
                count ++;
                System.out.print(new String(flush, 0, length)); //length 的原因是最后一次读取的长度。如果写成car.length最后会多读几个字节
            }
            System.out.println();
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //4. 关闭
            fileInputStream.close();
        }

    }
}
