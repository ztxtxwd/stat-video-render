package xyz.tapeless;

import java.awt.*;
import javax.swing.*;

public class GradientPaintExample extends JFrame {
  public GradientPaintExample() {
    setTitle("GradientPaint Example");
    setSize(300, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void paint(Graphics g) {
    // Create a Graphics2D object from the specified Graphics object.
    Graphics2D g2d = (Graphics2D)g;

    // Create a GradientPaint object with the specified start and end colors.
    GradientPaint gradient = new GradientPaint(10, 10, Color.RED, 100, 100, Color.BLUE);

    // Set the paint property of the graphics object to the gradient.
    g2d.setPaint(gradient);

    // Draw a rectangle using the gradient.
//    g2d.fillRect(10, 10, 100, 100);
    g2d.drawPolyline(new int[]{10, 20, 200}, new int[]{10, 120, 200}, 3);
  }

  public static void main(String[] args) {
    TestFrame frame = new TestFrame();
    frame.setVisible(true);
  }
}
