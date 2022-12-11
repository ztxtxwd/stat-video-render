package xyz.tapeless.entity;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
public class 头像 extends 元素{
    private String path;

    public 头像(坐标 基准坐标, String 头像, Color color) {
        坐标儿 = 基准坐标;
        path = 头像;
        this.color = color;
    }


    @Override
    public void draw(Graphics2D graphics) throws IOException {
        // 读取图片文件
        File file = new File(path);
        BufferedImage 读取的图片 = ImageIO.read(file);

        // 绘制图片
        graphics.drawImage(读取的图片, 坐标儿.getX(), 坐标儿.getY(), null);

    }
}
