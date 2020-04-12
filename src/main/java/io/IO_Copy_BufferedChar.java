package io;

import java.io.*;

/**
 * 纯字符文件拷贝
 */
public class IO_Copy_BufferedChar {

    public static void main(String[] args) throws IOException {
        File src = new File("study04.txt");
        File deter = new File("copyStudy.txt");

        BufferedReader reader = new BufferedReader(new FileReader(src));
        BufferedWriter writer = new BufferedWriter(new FileWriter(deter));
        String line;
        while ((line = reader.readLine()) != null){
            writer.write(line);
            writer.newLine(); //必须换行，不然写的文本都在同一行了。
        }
        writer.close();
        reader.close();
    }
}
