package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件字符输出流
 */
public class IO_Study04_FileWriter {

    public static void main(String[] args) throws IOException {
        File file = new File("study04.txt");

        Writer writer = new FileWriter(file,true);
        String str = "好好学习，多挣钱啊,哈哈哈哈哈";

        //writer.write(str);//方法1
        writer.append(str);
        writer.flush();
        writer.close();

    }
}
