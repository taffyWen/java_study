package io;

import java.io.*;

/**
 * 纯字符文件拷贝
 */
public class IO_Copy_Char {

    public static void main(String[] args) throws IOException {
        File src = new File("study04.txt");
        File deter = new File("copyStudy.txt");

        Reader reader = new FileReader(src);
        Writer writer = new FileWriter(deter);
        char[] flush = new char[1024];
        int temp;
        while ((temp = reader.read(flush)) != -1){
            writer.write(flush, 0, temp);
        }
        writer.close();
        reader.close();
    }
}
