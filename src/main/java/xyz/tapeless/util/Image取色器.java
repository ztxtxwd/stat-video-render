package xyz.tapeless.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Image取色器 {
    private static Deque<Color> 候选色;
    static {
        候选色=new ArrayDeque<>();
        候选色.add(new Color(67,40,231));
        候选色.add(new Color(150,84,229));
        候选色.add(new Color(255,98,131));
        候选色.add(new Color(255,136,0));
        候选色.add(new Color(255,197,2));
        候选色.add(new Color(0,125,142));
        候选色.add(new Color(26,167,238));
        候选色.add(new Color(41,218,228));
        候选色.add(new Color(136,233,154));
        候选色.add(new Color(1,156,0));
        候选色.add(new Color(193,31,31));
        候选色.add(new Color(115,0,0));
    }
    public static Color 取色(){
        return 候选色.pop();
    }
    public static Color 取色(String path) throws IOException {
        // 读取图片文件
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

// 获取图片的宽和高
        int width = image.getWidth();
        int height = image.getHeight();

// 统计图片中每个像素的颜色
        Map<Integer, Integer> colorCount = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = image.getRGB(x, y);
                colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
            }
        }

// 找到出现次数最多的颜色
        int maxColor = 0;
        int maxCount = 0;
        for (int color : colorCount.keySet()) {
            int count = colorCount.get(color);
            if (count > maxCount) {
                maxColor = color;
                maxCount = count;
            }
        }
        return new Color(maxColor);
//

    }
}
