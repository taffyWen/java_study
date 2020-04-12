package io;

import java.io.*;

/**
 * 文件字符输出流
 */
public class IO_Study05_BufferedWriter {

    public static void main(String[] args) throws IOException {
        File file = new File("study04.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        String str = "好好学习，多挣钱啊,哈哈哈哈哈";

        //writer.write(str);//方法1
        writer.append(str);
        writer.newLine();
        writer.append("第二行");
        writer.flush();
        writer.close();

    }
}
