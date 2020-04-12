package io;

import java.io.*;

public class IO_File_Copy_Util {

    public static void main(String[] args) {
        //文件到文件
        try {
            InputStream in = new FileInputStream("meng.png");
            OutputStream out = new FileOutputStream("meng-copy.png");
            copy(in, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //文件到字节数组

        byte[] datas = null;
        try {
            InputStream in = new FileInputStream("meng.png");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copy(in, out);
            datas = out.toByteArray();
            System.out.println("字节大小-->" + datas.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //字节数组到文件
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(datas);
            OutputStream out = new FileOutputStream("meng-copy2.png");
            copy(in, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * inputStream流拷贝到outputStream流中
     * @param inputStream
     * @param outputStream
     */
    public static void copy(InputStream inputStream, OutputStream outputStream){

        byte[] flush = new byte[1024];//缓存区
        int temp;

        try {
            while ((temp = inputStream.read(flush)) != -1){
                outputStream.write(flush, 0, temp);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream, outputStream);
        }
    }

    /**
     * 关闭流
     * @param in
     * @param out
     */
    public static void close(InputStream in, OutputStream out){
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     * @param ios
     */
    public static void close(Closeable... ios){
        try {
            for (Closeable io : ios) {
                if (io != null){
                    io.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
