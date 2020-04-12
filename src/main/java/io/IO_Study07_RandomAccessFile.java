package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机访问  文件分割
 */
public class IO_Study07_RandomAccessFile {

    public static void main(String[] args) throws IOException {

        File src = new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/IO_Study06_Convert2.java");
        //总长度
        long len = src.length();
        //每块大小
        int blockSize = 1024;
        //分多少块
        int size = (int) Math.ceil(len * 1.0 /blockSize);//向上取整

        int beginPos = 0;
        int actualSize = blockSize > len ? (int) len : blockSize;
        for (int i = 0; i < size; i++) {
            beginPos = i * blockSize; //最后一块
            if (i == size - 1){
                actualSize = (int) len;
            }else {
                actualSize = blockSize;
                len -= actualSize;//剩余量
            }
            System.out.println(i + "--->" + beginPos + "--->" + actualSize);
            split2(i,beginPos,actualSize);
        }
    }

    /**
     * 拆分成几个文件
     * @param i 拆分的文件个数
     * @param beginIndex
     * @param actualSize
     * @throws IOException
     */
    public static void split2(int i, int beginIndex, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/IO_Study06_Convert2.java"),"r");
        RandomAccessFile raf2 = new RandomAccessFile(new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/" + i + "Copy.txt"),"rw");

        raf.seek(beginIndex);

        byte[] flush = new byte[1024];
        int length =-1;
        while ((length = raf.read(flush))!= -1){
            if (actualSize > length){
                actualSize -= length;
                raf2.write(flush,0,length);
            }else {
                raf2.write(flush,0,actualSize);
                break;
            }
        }
        raf.close();
        raf2.close();
    }

    //从某个位置开始
    public static void read01() throws IOException {

        RandomAccessFile raf = new RandomAccessFile(new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/IO_Copy.java"), "r");
        raf.seek(2);

        byte[] flush = new byte[1024];
        int length =-1;
        while ((length = raf.read(flush))!= -1){
            System.out.println(new String(flush,0,length));
        }
        raf.close();
    }
    //分段，起始，实际大小
    public static void read02() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/IO_Copy.java"), "r");
        //起始位置
        int beginIndex = 3;
        //实际大小
        int actualSize = 1024;

        raf.seek(beginIndex);

        byte[] flush = new byte[1024];
        int length =-1;
        while ((length = raf.read(flush))!= -1){
            if (actualSize > length){
                System.out.println(new String(flush,0,length));
                actualSize -= length;
            }else {
                System.out.println(new String(flush,0,actualSize));
                break;
            }
        }
        raf.close();
    }

    //指定起始位置
    public static void split(int i, int beginIndex, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File("D:/workspace/workspace2018-9/restudy/src/main/java/io/IO_Copy.java"), "r");

        raf.seek(beginIndex);

        byte[] flush = new byte[1024];
        int length =-1;
        while ((length = raf.read(flush))!= -1){
            if (actualSize > length){
                System.out.println(new String(flush,0,length));
                actualSize -= length;
            }else {
                System.out.println(new String(flush,0,actualSize));
                break;
            }
        }
        raf.close();
    }
}
