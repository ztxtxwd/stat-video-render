package xyz.tapeless.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCliper {
    public static void clip(String path) throws IOException {
        File file = new File(path.replace("png","jpeg"));
        BufferedImage sourceImage = ImageIO.read(file);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, width, height, width, height);
        BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = targetImage.createGraphics();
        // Set the rendering hints for the Graphics2D object.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Draw a circle with the specified width and height.
        // 设置绘制颜色
        g2d.setClip(roundRect);
        g2d.drawImage(sourceImage, 0, 0, null);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(14));
        g2d.drawOval(0, 0, width, height);
        File outputFile = new File(path);
        ImageIO.write(targetImage, "png", outputFile);


    }
}
