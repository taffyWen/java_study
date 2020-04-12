package io;

import java.io.*;

/**
 * 文件字节输出流
 * IO流标准步骤
 * 1. 创建源
 * 2. 选择流
 * 3. 操作
 * 4. 释放资源
 */
public class IO_Study05_BufferedOutput {
    public static void main(String[] args) {
        //创建源
        File file = new File("hello3.txt");

        OutputStream outputStream = null;
        //选择流
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));//true表示追加
            String msg = "IO is so easy";
            byte[] data = msg.getBytes();//字符串--》字节数组（编码）
            outputStream.write(data, 0, data.length);
            outputStream.flush();
            //操作
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
