package io;

import java.io.*;

/**
 * 字节处理流
 */
public class IO_Util {

    public static void main(String[] args) throws IOException {
        byte[] bytes = fileToByteArray("meng.png");
        System.out.println(bytes.length);
        byteArrayToFile(bytes, "meng3.png");
    }

    public static byte[] fileToByteArray(String filePath) throws IOException {
        //1. 创建源+目的地
        File src = new File(filePath);
        byte[] dest = null;
        //2 选择流
        InputStream inputStream = new FileInputStream(src);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] flush = new byte[1024 * 3];
        int temp;
        while ((temp = inputStream.read(flush)) != -1){
            outputStream.write(flush, 0, temp);//写出到字节数组中
        }
        outputStream.flush();
        dest = outputStream.toByteArray();
        inputStream.close();//简写没有try、catch
        return dest;
    }

    public static void byteArrayToFile(byte[] src, String filePath) throws IOException {
        File dest = new File(filePath);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(src);
        OutputStream outputStream = new FileOutputStream(dest);
        byte[] flush = new byte[1024];
        int temp ;
        while ((temp = byteArrayInputStream.read(flush)) != -1){
            outputStream.write(flush, 0, temp);
        }
        outputStream.flush();
        outputStream.close();//简写close
    }
}
