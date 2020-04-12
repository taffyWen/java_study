package net.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author wen
 * @create 2019/10/20 11:23
 *
 * 客户端
 *  * 1 建立连接，使用Socket 创建客户端 + 服务器的地址+IP
 *  * 2. 操作，输入输出流
 *  * 3. 释放资源
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------client---------------");
        //建立连接，使用Socket 创建客户端 + 服务器的地址+IP
        Socket client = new Socket("localhost",9008);
        //操作，输入输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String data = "hello wen";
        // 使用机器无关的方式使用modified UTF-8编码将字符串写入基础输出流。
        dos.writeUTF(data);
        dos.flush();

        dos.close();
        client.close();
    }
}
