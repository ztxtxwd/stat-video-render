package xyz.tapeless.entity;

import lombok.Data;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import xyz.tapeless.TestFrame;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
public class 帧 {
    private final static int WIDTH = 1920;
    private final static int HEIGHT = 1080;
    private List<元素> shapeList;
    private BufferedImage image;
    private static BufferedImage 底图;

    public void addShape(元素 shape){
        if (shapeList==null){
            shapeList = new ArrayList<>();
        }
        shapeList.add(shape);
    }
    public void draw(String path) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Graphics2D graphics2D;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.setRenderingHints(hints);
        // 设置颜色
        graphics2D.setColor(Color.WHITE);
        // 绘制矩形
        graphics2D.fillRect(0, 0, WIDTH, HEIGHT);

        shapeList.stream().forEach(shape -> {
            try {
                shape.draw(graphics2D);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 释放资源
        graphics2D.dispose();

        // 保存图片
        File outputFile = new File(path);
        ImageIO.write(image, "png", outputFile);
    }
    public void draw() throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Graphics2D graphics2D;
        TestFrame frame = new TestFrame();
        frame.setVisible(true);
        graphics2D = (Graphics2D) frame.getGraphics();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.setRenderingHints(hints);
        // 设置颜色
        graphics2D.setColor(Color.WHITE);
        // 绘制矩形
        graphics2D.fillRect(0, 0, WIDTH, HEIGHT);
        shapeList.stream().filter(shape -> shape instanceof 折线).forEach(shape -> {
            try {
                shape.draw(graphics2D);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        底图 = copy(image);

        shapeList.stream().filter(shape -> !(shape instanceof 折线)).forEach(shape -> {
            try {
                shape.draw(graphics2D);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        shapeList.stream().forEach(shape -> {
//            try {
//                shape.draw(graphics2D);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
        // 释放资源
        graphics2D.dispose();
    }
    public void draw2(String path) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Graphics2D graphics2D;
        Graphics2D graphics2D1;
        if (底图==null){
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            底图 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            graphics2D = image.createGraphics();
            graphics2D1 = 底图.createGraphics();
            // 设置颜色
            graphics2D.setColor(Color.WHITE);
            graphics2D1.setColor(Color.WHITE);
            // 绘制矩形
            graphics2D.fillRect(0, 0, WIDTH, HEIGHT);
            graphics2D1.fillRect(0, 0, WIDTH, HEIGHT);
        }else{
            long 开始渲染图片时间 = System.currentTimeMillis();
            image = copy(底图);

            System.out.println("渲染图片耗时："+(System.currentTimeMillis()-开始渲染图片时间)+"ms");
            graphics2D = image.createGraphics();
            graphics2D1 = 底图.createGraphics();
        }

        shapeList.stream().filter(shape -> shape instanceof 折线).forEach(shape -> {
            try {
                shape.draw(graphics2D);
                shape.draw(graphics2D1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        底图 = copy(image);

        shapeList.stream().filter(shape -> !(shape instanceof 折线)).forEach(shape -> {
            try {
                shape.draw(graphics2D);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 释放资源
        graphics2D.dispose();
        graphics2D1.dispose();

        // 保存图片
        File outputFile = new File(path);
        ImageIO.write(image, "png", outputFile);
    }

    private BufferedImage copy1(BufferedImage sourceImage){
// create a Mat object from the source BufferedImage
        Mat sourceMat = new Mat(sourceImage.getHeight(), sourceImage.getWidth(), CvType.CV_8UC3);

// create a new Mat object that is a clone of the source Mat object
        Mat cloneMat = sourceMat.clone();

// convert the clone Mat object to a BufferedImage object
        return (BufferedImage) HighGui.toBufferedImage(cloneMat);
    }

    /**
     * @param sourceImage
     */
    private BufferedImage copy(BufferedImage sourceImage){
        Raster src = sourceImage.getRaster();
        WritableRaster dst = src.createCompatibleWritableRaster();
        dst.setRect(src);
        BufferedImage copy = new BufferedImage(sourceImage.getColorModel(), dst, sourceImage.isAlphaPremultiplied(), null);
        return copy;

    }
    public void add一人的数据(List<元素> 单人数据) {
        for (元素 元素 : 单人数据) {
            addShape(元素);
        }
    }
}
