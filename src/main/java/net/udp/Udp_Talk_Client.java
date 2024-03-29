package net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 交流软件：发送端
 * 1. 使用 DatagramSocket 指定端口 创建接收端
 * 2. 准备数据，一定得转成字节数组
 * 3. 封装成 DatagramPacket ，指定目的地
 * 4. 发送包裹 send(DatagramPacket p)
 * 5. 释放资源
 *
 * @Author wen
 * @create 2019/10/20 14:16
 */
public class Udp_Talk_Client {
    public static void main(String[] args) throws IOException {
        //1. 使用 DatagramSocket 指定端口 创建接收端
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        //2. 准备数据，一定得转成字节数组
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String data = bufferedReader.readLine();
            byte[] bytes = data.getBytes();
            //3. 封装成 DatagramPacket ，指定目的地  ---->打包
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                    new InetSocketAddress("localhost", 9999));
            //4. 发送包裹 send(DatagramPacket p)
            datagramSocket.send(packet);
            if (data.equalsIgnoreCase("bye")){
                break;
            }
        }
        datagramSocket.close();
    }
}
