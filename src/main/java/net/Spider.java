package net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * @Author wen
 * @create 2019/10/20 13:03
 *
 * 网络爬虫
 *
 * 在基于OpenURL时，采用网络抓取的形式获取结果页，其中遇到这样的错误：
 * Server returned HTTP response code: 403 for URL: http://www……………………而可以使用浏览器正确访问
 *  因为服务器的安全设置不接受Java程序作为客户端访问
 * 解决方案：设置User Agent   -----》模拟浏览器访问
 * 即在url.openConnection()后添加：
 * connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExtMozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36)");
 *
 * 上述随便打开浏览器，Headers 下的 User-Agent
 */
public class Spider {
    public static void main(String[] args) throws IOException {
        // 获取url
        URL url = new URL("https://www.bilibili.com");

        HttpURLConnection connection = (HttpURLConnection) url.
                openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36)");
        // 下载资源
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String msg = null;
        while ((msg = bufferedReader.readLine()) != null){
            System.out.println(msg);
        }
        bufferedReader.close();

    }

}
