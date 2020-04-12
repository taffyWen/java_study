package io;

import java.io.*;

/**
 * Data 类型 数据流
 * @Author wen
 * @create 2019/10/20 14:52
 */
public class IO_DataStream {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(outputStream));
        // 操作数据类型 + 数据
        dos.writeUTF("编码辛苦累");
        dos.writeInt(23);
        dos.writeBoolean(true);
        dos.writeChar('a');
        dos.flush();
        byte[] bytes = outputStream.toByteArray();
        System.out.println(bytes.length);

        //读取

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(inputStream));

        //顺序与写保持一致
        String s = dis.readUTF();
        int age = dis.readInt();
        boolean readBoolean = dis.readBoolean();
        char aChar = dis.readChar();
        System.out.println(aChar);
    }
}
