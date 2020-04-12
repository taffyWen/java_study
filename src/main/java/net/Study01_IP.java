package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress
 * 1. getLocalHost :本机
 * 2. getByName 根据域名DNS | IP --》IP
 *
 * 成员方法
 * getHostAddress 返回主机地址
 * getHostName 返回主机名
 *
 * 了解即可
 */
public class Study01_IP {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        address = InetAddress.getByName("www.baidu.com");
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
    }
}
