package xyz.tapeless.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {
    public static void download(String 文件名, String u){
        try {
            // 图片的URL地址
            URL url = new URL(u);

            // 打开图片的URL地址
            URLConnection conn = url.openConnection();

            // 获取图片的数据
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());

            // 创建文件输出流
            FileOutputStream out = new FileOutputStream(文件名);

            // 定义缓冲区
            byte[] buffer = new byte[1024];

            // 将图片数据写入文件
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            // 关闭文件输出流
            out.close();

            // 关闭图片输入流
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        download("云涛-.jpeg","https://p26.douyinpic.com/aweme/100x100/aweme-avatar/tos-cn-avt-0015_b35ee89b9db6d657b70f897179be8187.jpeg");
    }
}
