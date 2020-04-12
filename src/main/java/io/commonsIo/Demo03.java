package io.commonsIo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文件拷贝
 */
public class Demo03 {

    public static void main(String[] args) throws IOException {
        //复制文件
        //FileUtils.copyFile(new File("meng.png"),new File("mengmeng.png"));
        //复制文件到目录
        //FileUtils.copyFileToDirectory(new File("meng.png"),new File("lib"));

        //复制 目录到目录
        //FileUtils.copyDirectoryToDirectory(new File("lib"),new File("lib2"));

        //复制目录  把lib中的文件，拷贝到lib2中
        //FileUtils.copyDirectory(new File("lib"),new File("lib2"));

        //复制网络文件到---目录
        //FileUtils.copyURLToFile(new URL(""), new File("file"));

        String gbk = IOUtils.toString(new URL("https://www.163.com/"), "gbk");
        System.out.println(gbk);

    }
}
