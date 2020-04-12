package io;

import java.io.*;
import java.net.URL;

/**
 * 转换流：InputStreamReader  字节流到字符流的桥：它读取字节，并使用指定的charset将其解码为字符
 * OutputStreamReader  是字符流到字节流的桥梁：向其写入的字符编码成使用指定的字节charset
 * 1. 以字符流的形式操作字节流（纯文本）
 * 2. 指定字符集
 */
public class IO_Study06_Convert2 {

    public static void main(String[] args) throws IOException {
        read03();
    }
    //有中文乱码问题
    public static void read01() throws IOException {
        InputStream is = new URL("https://cn.bing.com/").openStream();
        byte[] flush = new byte[4];//缓存容器
        int length ;
        while ((length = is.read(flush)) != -1){

            System.out.print(new String(flush, 0, length)); //length 的原因是最后一次读取的长度。如果写成 flush.length最后会多读几个字节
        }
        is.close();
    }

    //中文不再乱码 -->指定正确的字符集
    public static void read02() throws IOException {
        InputStreamReader isr = new InputStreamReader(new URL("https://cn.bing.com/").openStream(),"utf-8");
        int temp ;
        while ((temp = isr.read()) != -1){

            System.out.print((char) temp);
        }
        isr.close();
    }

    public static void read03() throws IOException {
        BufferedReader isr = new BufferedReader(new InputStreamReader(new URL("https://cn.bing.com/").openStream(),"utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bing.html")));
        String msg ;
        while ((msg = isr.readLine()) != null){
            bw.write(msg);
            bw.newLine();
            //System.out.println(msg);
        }
        isr.close();
        bw.close();
    }
}
