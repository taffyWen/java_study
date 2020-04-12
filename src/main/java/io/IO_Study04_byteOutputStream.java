package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 字节数组输出流
 * 1. 源头，内部维护
 * 2. 选择流：不再用父类引用，不关联源
 * 3. 操作
 * 4. 释放资源：可不关闭
 *
 * 获取数据，数据是在内存中，用  toByteArray()
 */
public class IO_Study04_byteOutputStream {

    public static void main(String[] args) throws IOException {
        byte[] dest = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //写出操作
        String str = "show me the code";
        byte[] data = str.getBytes();
        byteArrayOutputStream.write(data);
        byteArrayOutputStream.flush();
        dest = byteArrayOutputStream.toByteArray();//创建一个新分配的字节数组
        System.out.println(new String(dest));

        byteArrayOutputStream.close();
    }
}
