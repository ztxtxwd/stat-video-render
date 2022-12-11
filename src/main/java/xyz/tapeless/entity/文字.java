package xyz.tapeless.entity;

import lombok.Data;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

@Data
public class 文字 extends 元素 {
    private String content;
    private Font font = new Font("PingFang SC", Font.BOLD, 60);

    public 文字(坐标 基准坐标, String 当前值, Color color) {
        坐标儿 = 基准坐标;
        content = 当前值;
        this.color = color;
        Map<TextAttribute, Object> textAttributeObjectHashMap = new HashMap<>();
        textAttributeObjectHashMap.put(TextAttribute.FAMILY,"PingFang SC");
        textAttributeObjectHashMap.put(TextAttribute.WEIGHT,TextAttribute.WEIGHT_ULTRABOLD);
        textAttributeObjectHashMap.put(TextAttribute.SIZE,60);
        font = new Font(textAttributeObjectHashMap);
    }

    @Override
    public void draw(Graphics2D graphics) {
//        GlyphVector v = font.createGlyphVector(graphics.getFontMetrics(font).getFontRenderContext(), content);
////        Shape shape = v.getOutline();
//        Shape shape = v.getOutline();
//        Rectangle bounds = shape.getBounds();
//
//        graphics.translate(
////                (getWidth() - bounds.width) / 2 - bounds.x,
////                (getHeight() - bounds.height) / 2 - bounds.y
//                坐标儿.getX(),坐标儿.getY()+20
//        );
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics.setColor(color);
//        graphics.fill(shape);
//        graphics.setColor(Color.WHITE);
//        graphics.setStroke(new BasicStroke(2));
//        graphics.draw(shape);
//        graphics.translate(-坐标儿.getX(),-坐标儿.getY()-20);
        graphics.setFont(font);
        graphics.setColor(color);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(content, 坐标儿.getX(), 坐标儿.getY()+100 - fontMetrics.getHeight());
    }
}
