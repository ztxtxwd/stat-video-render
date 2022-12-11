package xyz.tapeless;

import xyz.tapeless.entity.帧;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class RenderTask implements Runnable {
    private 帧 帧儿;
    private int i;
    private CountDownLatch latch;

    public RenderTask( 帧 帧儿,int i, CountDownLatch latch) {
        this.i = i;
        this.帧儿 = 帧儿;
        this.latch = latch;
    }

    public void run() {
//        System.out.println(i);
        try {
            帧儿.draw("frames/"+String.format("%04d", i)+".png");

            // 减少CountDownLatch计数直到计数器变为0
            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}