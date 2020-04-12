package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * 交流软件：接送端
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
public class Udp_Talk_Server {

    public static void main(String[] args) throws IOException {
        //1. 使用 DatagramSocket 指定端口 创建接收端
        DatagramSocket socket = new DatagramSocket(9999);
        while (true) {
            //2. 准备容器，封装成 DatagramPacket 包裹
            DatagramPacket packet = null;
            byte[] catalina = new byte[1024*60];
            packet = new DatagramPacket(catalina,0,catalina.length);

            //3. 阻塞式接受包裹 receive(DatagramPacket p)
            socket.receive(packet);

            //4. 分析数据
            byte[] data = packet.getData();
            String str = new String(data,0,packet.getLength());//若此处用data.length 就会多出很多乱码
            System.out.println(str);
            if (str.equalsIgnoreCase("bye")){
                break;
            }
        }

        socket.close();
    }
}
