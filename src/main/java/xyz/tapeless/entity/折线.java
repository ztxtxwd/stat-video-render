package xyz.tapeless.entity;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

@Data
public class 折线 extends 元素{
    private int[] xPoints;
    private int[] yPoints;
    private 坐标 起始坐标;

    public 折线(坐标 基准坐标, 坐标 上一点的坐标, Color color) {
        起始坐标 = 上一点的坐标;
        坐标儿 = 基准坐标;
        this.color = color;
    }
    public 折线(int[] xPoints,int[] yPoints, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if(起始坐标==null){
            起始坐标 = 坐标儿;
        }
        // 设置线条的颜色和宽度
        graphics.setColor(color);
        BasicStroke bs = new BasicStroke(18.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f);

        graphics.setStroke(bs);

// 创建透明度合成对象，并设置图形的透明度为0.5
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
        graphics.setComposite(composite);

        graphics.drawPolyline(xPoints,yPoints,xPoints.length);
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        graphics.setComposite(composite);
        BasicStroke bs2 = new BasicStroke(6.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f);
        graphics.setStroke(bs2);
        graphics.setColor(color);
        graphics.drawPolyline(xPoints,yPoints,xPoints.length);

        // 绘制直线
//        graphics.drawLine(起始坐标.getX(),起始坐标.getY(),坐标儿.getX(), 坐标儿.getY());
    }

//    @Override
//    public void draw(Graphics2D graphics) {
//        if(起始坐标==null){
//            起始坐标 = 坐标儿;
//        }
//        graphics.drawPolyline();
//
//        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
//        g2d.setColor(Color.BLUE);
//        g2d.drawLine(0, 0, 100, 100);
//
//        bufferStrategy.show();
//        // 设置线条样式
//        graphics.setStroke(new BasicStroke(10.0f));
//        graphics.setColor(color);
//
//        // 绘制直线
//        graphics.drawLine(起始坐标.getX(),起始坐标.getY(),坐标儿.getX(), 坐标儿.getY());
//    }
}
