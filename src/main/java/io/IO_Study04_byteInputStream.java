package io;



import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 字节数组输入流
 * 1. 创建源--》不能太大，会内存溢出
 * 2. 选择流
 * 3. 操作
 * 4. 关闭--》可不用，因为是内存
 */
public class IO_Study04_byteInputStream {

    public static void main(String[] args) throws IOException {
        //创建源
        byte[] bytes = "亲爱的你，怎么哈不回来".getBytes();
        //选择流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        byte[] flush = new byte[1024];
        int temp;
        while ((temp = byteArrayInputStream.read(flush)) != -1){
            System.out.println(new String(flush,0,temp));
        }
        //可不关闭流
    }
}
