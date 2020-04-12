package io;

import java.io.*;

/**
 * 使用文件的输入输出流，实现文件的拷贝
 */
public class IO_Copy {

    public static void main(String[] args) {
        copy("meng.png", "./src/main/java/io/meng2.png");
    }
    public static void copy(String srcPath, String destPath){
        File src = new File(srcPath);
        File destination = new File(destPath);//

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(src);
            byte[] flush = new byte[1024];
            outputStream = new FileOutputStream(destination);
            int length;
            while ((length = inputStream.read(flush)) != -1){
                outputStream.write(flush, 0, length);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
