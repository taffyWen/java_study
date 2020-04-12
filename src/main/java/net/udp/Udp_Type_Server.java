package net.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * 基本类型：接送端
 * 1. 使用 DatagramSocket 指定端口 创建接收端
 * 2. 准备容器，封装成 DatagramPacket 包裹
 * 3. 阻塞式接受包裹 receive(DatagramPacket p)
 * 4。分析数据
 *      byte[] getData()
 *      int getLength()
 * 5. 释放资源
 *
 * @Author wen
 * @create 2019/10/20 14:16
 *
 */
public class Udp_Type_Server {

    public static void main(String[] args) throws IOException {
        //1. 使用 DatagramSocket 指定端口 创建接收端
        DatagramSocket socket = new DatagramSocket(9999);
        //2. 准备容器，封装成 DatagramPacket 包裹
        byte[] catalina = new byte[1024*60];
        DatagramPacket packet = new DatagramPacket(catalina,0,catalina.length);

        //3. 阻塞式接受包裹 receive(DatagramPacket p)
        socket.receive(packet);

        //4. 分析数据
        byte[] data = packet.getData();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(inputStream));

        //顺序与写保持一致
        String s = dis.readUTF();
        int age = dis.readInt();
        boolean readBoolean = dis.readBoolean();
        char aChar = dis.readChar();
        System.out.println(s);
        socket.close();
    }
}
