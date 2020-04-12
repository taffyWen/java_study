package net.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 1 指定端口，使用 ServerSocket 创建服务器
 * 2. 阻塞式等待连接 accept
 * 3. 操作，输入输出流
 * 4. 释放资源
 */
public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("-----------server-------------");
        //指定端口，使用 ServerSocket 创建服务器
        ServerSocket serverSocket = new ServerSocket(9008);
        //阻塞式   等待连接 accept
        Socket client = serverSocket.accept();
        System.out.println("一个客户端连接成功");

        DataInputStream dis = new DataInputStream(client.getInputStream());

        // 从流in读取以modified UTF-8格式编码的Unicode字符串的表示; 这个字符串然后作为String返回。
        String data = dis.readUTF();//需要跟客户端对应
        System.out.println(data);

        dis.close();
        client.close();
        serverSocket.close();
    }
}
