package xyz.tapeless.entity;

import lombok.Data;
import xyz.tapeless.entity.*;
import xyz.tapeless.util.ImageCliper;
import xyz.tapeless.util.ImageDownloader;
import xyz.tapeless.util.Image取色器;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class 人 {
    private String name;
    private int nameWidth;
    private String 头像;
    private Color color;
    private java.util.List<Integer> 人气数据;
    private 坐标 上一点的坐标;

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
    }
    public 人(String name, String 头像) throws IOException {
        setName(name);
        set头像(头像);
    }

    public void setName(String name) {
        this.name = name;
        BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        Font font = new Font("PingFang SC", Font.BOLD, 60);
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics();
        nameWidth = metrics.stringWidth(name);
        graphics.dispose();
    }

    public void set头像(String 头像Url) throws IOException {
        this.头像 = "头像/"+name+".png";
        //下载图片
        ImageDownloader.download("头像/"+name+".jpeg",头像Url);
        Color color = Image取色器.取色();
//        Color color = Image取色器.取色("头像/"+name + ".jpeg");
        ImageCliper.clip("头像/"+name + ".png");
        //根据头像算出color
        setColor(color);
    }
    public List<元素> get单人数据(int total,int current,int 当前值){
        int Y轴最大值 = 10000;
        int 基准x = 1920*current/total;
        int 基准y = 1080*当前值/Y轴最大值;
        坐标 基准坐标 = new 坐标(基准x, 基准y);
        List<元素> shapes = new ArrayList<>();
        shapes.add(new 折线(基准坐标,上一点的坐标,color));
        shapes.add(new 头像(基准坐标,头像,color));
        shapes.add(new 文字(基准坐标,当前值+"",color));
        shapes.add(new 文字(基准坐标,name,color));
        set上一点的坐标(基准坐标);
        return shapes;
    }
    public List<元素> get单人数据(int total,int current,List<Integer> 当前值,int nameWidth){
        int Y轴最大值 = 10000;
        int 基准x = 1920*current/total;
        int 基准y = 1080*当前值.get(当前值.size()-1)/Y轴最大值;
        坐标 基准坐标 = new 坐标(基准x, 基准y);
        List<元素> shapes = new ArrayList<>();
        int[] xPoints=new int[当前值.size()];
        int[] yPoints=new int[当前值.size()];
        for (int i = 0; i < 当前值.size(); i++) {
            int c=当前值.get(i);
            Integer lastC=i-1>0?当前值.get(i-1):null;
            坐标 起始坐标 = i>0?new 坐标(4*1920*(i-1)/total,1080*当前值.get(i-1)/Y轴最大值):null;
            坐标 结束坐标 = new 坐标(4*1920*i/total,1080*当前值.get(i)/Y轴最大值);
            xPoints[i]=结束坐标.getX();
            yPoints[i]=结束坐标.getY();
        }
        shapes.add(new 折线(xPoints,yPoints,color));
        shapes.add(new 头像(基准坐标.offset(-50,-50),头像,color));
        shapes.add(new 文字(基准坐标.offset(70,0),name,color));
        shapes.add(new 文字(基准坐标.offset(90+nameWidth,0),当前值.get(当前值.size()-1)+"",color));
        set上一点的坐标(基准坐标);
        return shapes;
    }
}
