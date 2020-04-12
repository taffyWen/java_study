package io.commonsIo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.Collection;

/**
 * 操作文件夹
 */
public class Demo01 {

    public static void main(String[] args) {
        //文件大小
        long size = FileUtils.sizeOf(new File("meng.png"));
        System.out.println(size);

        //获取路径下的所有文件
        Collection<File> files = FileUtils.listFiles(new File("D:/workspace/workspace2018-9/restudy"),
                EmptyFileFilter.NOT_EMPTY, null);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("打印出文件下的子孙级文件");
        files = FileUtils.listFiles(new File("D:/workspace/workspace2018-9/restudy"),
                new SuffixFileFilter("java"), //java结尾的文件
                DirectoryFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }


    }
}
