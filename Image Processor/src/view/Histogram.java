package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import model.Image;

/**
 * This class helps creating a histogram of an image.
 */
public class Histogram extends JPanel {
  private static final int BAR  = 5;
  private Image image;

  /**
   * Constructor for the background of the histogram.
   */
  public Histogram() {
    super();
    this.setPreferredSize(new Dimension(266,200));
  }

  /**
   * Sets the image that the histogram is referring to, to a given image.
   * @param image the image to be set
   */
  public void setImage(Image image) {
    this.image = image;
    this.repaint();
  }

  /**
   * Adds the bars representing each r,g,b value to the histogram.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph = (Graphics2D) g;
    int xWidth =  this.getWidth() / 256;
    graph.setColor(Color.WHITE);
    if (this.image == null) {
      return;
    }
    int[][] histogram = this.image.getHistogram();

    graph.setBackground(Color.WHITE);
    int[] xPoints = new int[256];
    for (int i = 0; i < 256; i++) {
      xPoints[i] = i * xWidth;
    }
    graph.setPaint(Color.RED);
    graph.drawPolyline(xPoints, histogram[0], 256);
    graph.setPaint(Color.GREEN);
    graph.drawPolyline(xPoints, histogram[1], 256);
    graph.setPaint(Color.BLUE);
    graph.drawPolyline(xPoints, histogram[2], 256);
  }

}
