package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Image;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import model.Pixel;

/**
 * To represent a Histogram Plot Panel that will reflect the frequencies of various intensities
 * of our constituent RGB colors in our image. This plot will have three distant graphs
 * for each color, and each one will have differing bar heights to reflect the
 * frequency of each value.
 */
public class Histogram extends JPanel {
  private Image currentImage;
  private Color red = new Color(255, 0, 0);
  private Color green = new Color(0, 255, 0);
  private Color blue = new Color(0, 0, 255);

  /**
   * To construct a new Histogram object given an ArrayList of an ArrayList of Pixels representing
   * our current image. This constructor will take this List and construct a new Image object with
   * it, which will be stored in an image field to be manipulated in the rest of this class.
   *
   * @param image the current image to be analyzed by our Histogram class.
   */
  public Histogram(ArrayList<ArrayList<Pixel>> image) {
    this.currentImage = new Image(image, "Image");
  }

  /**
   * To implement the draw histogram method. This will take in the Image's Red, Green, and Blue
   * Intensity HashMaps, which document the frequency of each Color Intensity. From there,
   * the actual Histogram Plot can be drawn given the attributes of our image, which has
   * the necessary methods within the Image Object to count the Pixels and the intensities
   * of the RGB values for our graph.
   */
  @Override
  public void paintComponents(Graphics g) {
    super.paintComponents(g);

    Graphics2D background = (Graphics2D) g.create();
    background.setColor(Color.WHITE);

    HashMap<Integer, Integer> reds = this.currentImage.redIntensity();
    HashMap<Integer, Integer> greens = this.currentImage.greenIntensity();
    HashMap<Integer, Integer> blues = this.currentImage.blueIntensity();

    // to iterate through all 256 intensities and check the intensity frequencies of all
    // RGB Components at once.
    for (Integer key : reds.keySet()) {

      // first paint the Red Intensity on the Histogram.
      background.setPaint(this.red);

      // extract Red Intensity from the Image method by retrieving the HashMap with the iterator.
      int height = this.currentImage.redIntensity().get(key);

      // draw the bar at the given slot that reflects the height of the Intensity value.
      Rectangle2D singleBar = this.barHeightHelper(height, key);

      // apply the bar to the current graph.
      background.fill(singleBar);
      background.draw(singleBar);
    }

    for (Integer key : greens.keySet()) {

      // then paint the Green Intensity on the Histogram.
      background.setPaint(this.green);

      // extract Green Intensity from the Image method by retrieving the HashMap with the iterator.
      int height = this.currentImage.greenIntensity().get(key);

      // draw the bar at the given slot that reflects the height of the Intensity value.
      Rectangle2D singleBar = this.barHeightHelper(height, key);

      // apply the bar to the current graph.
      background.fill(singleBar);
      background.draw(singleBar);

    }

    for (Integer key : blues.keySet()) {

      // Lastly paint the Blue Intensity on the Histogram.
      background.setPaint(this.blue);

      // extract Blue Intensity from the Image method by retrieving the HashMap with the iterator.
      int height = this.currentImage.blueIntensity().get(key);

      // draw the bar at the given slot that reflects the height of the Intensity value.
      Rectangle2D singleBar = this.barHeightHelper(height, key);
      // apply the bar to the current graph.
      background.fill(singleBar);
      background.draw(singleBar);
    }
  }

  /**
   * To represent a helper to create the bars in our graph to reduce code duplication.
   * @param height height of the bar to be drawn.
   * @param slot current intensity value.
   * @return Rectangle2D.Double representing our bar for the current intensity value.
   */
  private Rectangle2D.Double barHeightHelper(double height, int slot) {
    return new Rectangle2D.Double((slot / 256) * this.getWidth(),
            this.getHeight() - (Math.min(255, 0.01 * height * height)),
            1,(Math.min(255, 0.01 * height * height)));
  }

}
