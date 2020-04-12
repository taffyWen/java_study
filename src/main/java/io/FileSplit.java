package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 文件切割
 */
public class FileSplit {



    public void split() throws IOException {
        //总长度
        long len = src.length();
        //每块大小
        int blockSize = this.blockSize;
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
            splitDetail(i,beginPos,actualSize);
        }
    }

    private void splitDetail(int i, int beginIndex, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(this.src, "r");
        RandomAccessFile raf2 = new RandomAccessFile(new File(this.destPaths.get(i)), "rw");

        raf.seek(beginIndex);

        byte[] flush = new byte[1024];
        int length = -1;
        while ((length = raf.read(flush)) != -1) {
            if (actualSize > length) {
                actualSize -= length;
                raf2.write(flush, 0, length);
            } else {
                raf2.write(flush, 0, length);
                break;
            }
        }
        raf.close();
        raf2.close();
    }

    //多个文件的合并
    public void merge(String destPath) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath,true));
        Vector<InputStream> vector = new Vector<>();
        for (int i = 0; i < destPaths.size(); i++) {
            InputStream is = new BufferedInputStream(new FileInputStream(destPaths.get(i)));
            vector.add(is);
        }
        SequenceInputStream stream = new SequenceInputStream(vector.elements());
        byte[] flush = new byte[1024];
        int len ;
        while ((len = stream.read(flush)) != -1){
            os.write(flush,0,len);
        }
        os.flush();
        os.close();
        stream.close();

    }

    //多个文件的合并
    public void merge2(String destPath) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath,true));
        for (int i = 0; i < destPaths.size(); i++) {
            InputStream is = new BufferedInputStream(new FileInputStream(destPaths.get(i)));
            byte[] flush = new byte[1024];
            int len ;
            while ((len = is.read(flush)) != -1){
                os.write(flush,0,len);
                os.flush();
            }
        }
        os.close();
    }

    public static void main(String[] args) {

    }


    //源文件
    private File src;
    //目标路径
    private String destDir;
    //所有分割后的文件存储路径
    private List<String> destPaths;
    //每块大小
    private int blockSize;
    //块数
    private int size;

    public FileSplit(String src, String destDir, int blockSize) {
        this.src = new File(src);
        this.destDir = destDir;
        this.blockSize = blockSize;
        this.destPaths = new ArrayList<>();
        init();
    }

    //初始化
    private void init() {
        long len = this.src.length();
        this.size = (int) Math.ceil(len * 1.0 / this.blockSize);
        for (int i = 0; i < destPaths.size(); i++) {
            this.destPaths.add(this.destDir + "/"+ i + "_" + this.src.getName());
        }
    }
}
