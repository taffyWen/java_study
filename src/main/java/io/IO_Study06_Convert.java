package io;

import java.io.*;

/**
 * 转换流：InputStreamReader  字节流到字符流的桥：它读取字节，并使用指定的charset将其解码为字符
 * OutputStreamReader  是字符流到字节流的桥梁：向其写入的字符编码成使用指定的字节charset
 * 1. 以字符流的形式操作字节流（纯文本）
 * 2. 指定字符集
 */
public class IO_Study06_Convert {

    public static void main(String[] args) throws IOException {
        BufferedReader isr = new BufferedReader(new InputStreamReader(System.in));//处理字符
        BufferedWriter osr = new BufferedWriter(new OutputStreamWriter(System.out));//处理字符
        String line = null;
        while (!"E".equalsIgnoreCase(line)){
            line = isr.readLine();//循环读取
            osr.write(line);
            osr.newLine();
            osr.flush();
        }
        try {
            osr.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
