package xyz.tapeless.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 定义抽象基类 Shape，它包含一个抽象方法 draw
public abstract class 元素 {
    protected Color color;
    protected 坐标 坐标儿;
    public abstract void draw(Graphics2D graphics) throws IOException;
}
