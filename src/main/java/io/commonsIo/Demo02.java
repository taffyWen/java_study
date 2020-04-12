package io.commonsIo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件 -->都指定字符集
 */
public class Demo02 {

    public static void main(String[] args) throws IOException {
        String string = FileUtils.readFileToString(new File("hello3.txt"), "utf-8");
        System.out.println(string);

        byte[] bytes = FileUtils.readFileToByteArray(new File("hello4.txt"));
        System.out.println(bytes.length);

        List<String> strings = FileUtils.readLines(new File("hello4.txt"), "utf-8");
        for (String s : strings) {
            System.out.println(s);
        }

        //写
        FileUtils.write(new File("happy.txt"),"哈好学习","utf-8");
        FileUtils.writeStringToFile(new File("happy.txt"),"我爱我的祖国","utf-8",true);
        FileUtils.writeByteArrayToFile(new File("happy.txt"),"哈好学习,天天向上".getBytes(),true);

        List<String> list = new ArrayList<>();
        list.add("码农真辛苦");
        list.add("一敲一上午");
        FileUtils.writeLines(new File("happy.txt"),list,true);

    }
}
