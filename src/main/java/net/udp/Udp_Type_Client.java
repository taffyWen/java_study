package net.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 基本类型：发送端
 * 1. 使用 DatagramSocket 指定端口 创建接收端
 * 2. 准备数据，一定得转成字节数组
 * 3. 封装成 DatagramPacket ，指定目的地
 * 4. 发送包裹 send(DatagramPacket p)
 * 5. 释放资源
 *
 * @Author wen
 * @create 2019/10/20 14:16
 */
public class Udp_Type_Client {
    public static void main(String[] args) throws IOException {
        //1. 使用 DatagramSocket 指定端口 创建接收端
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        //2. 准备数据，一定得转成字节数组

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(outputStream));
        // 操作数据类型 + 数据
        dos.writeUTF("编码辛苦累");
        dos.writeInt(23);
        dos.writeBoolean(true);
        dos.writeChar('a');
        dos.flush();
        byte[] bytes = outputStream.toByteArray();

        //3. 封装成 DatagramPacket ，指定目的地  ---->打包
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                new InetSocketAddress("localhost", 9999));
        //4. 发送包裹 send(DatagramPacket p)
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
