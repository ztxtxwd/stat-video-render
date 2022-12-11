package xyz.tapeless;

import xyz.tapeless.entity.人;
import xyz.tapeless.entity.元素;
import xyz.tapeless.entity.帧;
import xyz.tapeless.util.抗锯齿器;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TestFrame extends JFrame {
    public TestFrame() {
        setTitle("TestFrame");
        setSize(1920, 1080);
        // Set the JFrame window to full screen mode.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Get the number of available monitors.
        int numMonitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length;

        // Check if there are multiple monitors available.
        if (numMonitors > 1) {
            // Set the JFrame window to the second monitor.
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
            setLocation(device.getDefaultConfiguration().getBounds().x, device.getDefaultConfiguration().getBounds().y);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        //读数据
//        List<String[]> 人气数据 = CsvReader.读人气数据("data.csv");
        //下载头像
//        for (int i = 1; i < 人气数据.size(); i++) {
//            ImageDownloader.download(人气数据.get(i)[1]+".jpeg",人气数据.get(i)[0]);
//        }
        //遍历人气数据 一个时间点生成一张图片
        //构造测试数据
        int 总时长 = 2*60*60; //2个小时
        int 步长 = 4; //4秒
        java.util.List<人> 人List = new ArrayList<>();
        人List.add(new 人("宋六","https://p3.douyinpic.com/aweme/100x100/aweme-avatar/tos-cn-avt-0015_5c70b2f584a8d92753b8ab3a544e6f93.jpeg?from=4010531038"));
        人List.add(new 人("卡片儿","https://p11.douyinpic.com/aweme/100x100/aweme-avatar/tos-cn-avt-0015_5b12d72027c030e3ef353b02c3a7c811.jpeg?from=4010531038"));
        人List.add(new 人("uubb","https://p3.douyinpic.com/aweme/100x100/aweme-avatar/tos-cn-avt-0015_a2f0e17cc5e5c5debbeb7860441529d6.jpeg?from=4010531038"));
        人List.add(new 人("哏的","https://p26.douyinpic.com/aweme/100x100/aweme-avatar/douyin-user-image-file_671fef8dc62914d81f665b744ad63b1c.jpeg?from=4010531038"));
        //人气数据

        for (人 人儿 : 人List) {
            String name = 人儿.getName();
            java.util.List<Integer> integers = new ArrayList<>();
            int 初始值 = (int)(Math.random()*10000);
            for (int i = 0; i < 总时长 / 步长; i++) {
                初始值 += (int)(Math.random()*400-200);
                integers.add(初始值);
            }
            人儿.set人气数据(integers);
        }
//        抗锯齿器.抗锯齿(人List,10);
        long 开始时间 = System.currentTimeMillis();
        帧 帧儿 = new 帧();
        for (int j = 0; j < 人List.size(); j++) {
            人 人 = 人List.get(j);
//                List<元素> 单人数据 = 人.get单人数据(总时长, 步长 * i, 人.get人气数据().get(i));
            List<元素> 单人数据 = 人.get单人数据(总时长, 步长 * 900, 人.get人气数据().subList(0,901),人List.get(j).getNameWidth());
            帧儿.add一人的数据(单人数据);

        }
        帧儿.draw();
        System.out.println("渲染图片耗时："+(System.currentTimeMillis()-开始时间)+"ms");
    }
}
