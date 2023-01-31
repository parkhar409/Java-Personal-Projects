package model;

/**
 * This class represents a pixel from an image, with information about the X position, Y position,
 * and the red, green, and blue color components.
 */
public class Pixel {
  private int x;
  private int y;
  private final int[] rGBColor;

  /**
   * Constructs a pixel based on the given X position, Y position, and color.
   *
   * @param x        the X position of the pixel within the given image
   * @param y        the Y position of the pixel within the given image
   * @param rGBColor the color of the pixel within the given image
   * @throws IllegalArgumentException if the X or Y positions are negative or if there is a null
   *                                  argument for the color
   */
  public Pixel(int x, int y, int[] rGBColor) throws IllegalArgumentException {
    if (x < 0 || y < 0 || rGBColor == null) {
      throw new IllegalArgumentException("Pixel positions are invalid or color can't be null.");
    }
    this.x = x;
    this.y = y;
    this.rGBColor = rGBColor;
  }

  /**
   * A constructor which represents the position of the pixel.
   * @param x        the X position of the pixel within the given image
   * @param y        the Y position of the pixel within the given image
   * @throws IllegalArgumentException if the X or Y positions are negative or if there is a null
   *                                  argument for the color
   */
  public Pixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Pixel positions are invalid or color can't be null.");
    }
    this.x = x;
    this.y = y;
    this.rGBColor = new int[]{0, 0, 0};
  }

  /**
   * Set the X position of the pixel to the given X position from the image.
   *
   * @param x the X position of the pixel within the given image
   * @throws IllegalArgumentException if the X position is negative
   */
  public void setXPosition(int x) throws IllegalArgumentException {
    if (x < 0) {
      throw new IllegalArgumentException("X position can't be negative.");
    } else {
      this.x = x;
    }
  }

  /**
   * Gets the X position of a pixel.
   *
   * @return the X position of a pixel
   */
  public int getXPosition() {
    return this.x;
  }

  /**
   * Set the Y position of the pixel to the given Y position from the image.
   *
   * @param y the Y position of the pixel within the given image
   * @throws IllegalArgumentException if the Y position is negative
   */
  public void setYPosition(int y) throws IllegalArgumentException {
    if (y < 0) {
      throw new IllegalArgumentException("Y position can't be negative.");
    } else {
      this.y = y;
    }
  }

  /**
   * Gets the Y position of a pixel.
   *
   * @return the Y position of a pixel
   */
  public int getYPosition() {
    return this.y;
  }

  /**
   * Get the color of the pixel from the image.
   *
   * @return a new integer array with the RGB values of the color of the pixel
   */
  public int[] getColor() {
    return new int[]{rGBColor[0], rGBColor[1], rGBColor[2]};
  }

  /**
   * Set the color of the pixel to the given color from the image.
   *
   * @param rGBColors the RGB components of the pixel within the given image
   * @throws IllegalArgumentException if the RGB values are invalid
   */
  public void setColor(int[] rGBColors) throws IllegalArgumentException {
    if (rGBColors[0] < 0 || rGBColors[0] > 255
            || rGBColors[1] < 0 || rGBColors[1] > 255
            || rGBColors[2] < 0 || rGBColors[2] > 255) {
      throw new IllegalArgumentException("Invalid color.");
    } else {
      this.rGBColor[0] = rGBColors[0];
      this.rGBColor[1] = rGBColors[1];
      this.rGBColor[2] = rGBColors[2];
    }
  }

  /**
   * Gets the maximum value of the three RGB components for the pixel.
   *
   * @return the maximum value of the three RGB components for the pixel
   */
  public int value() {
    return Math.max(Math.max(rGBColor[0], rGBColor[1]), rGBColor[2]);
  }

  /**
   * Gets the average of the three RGB components for the pixel.
   *
   * @return the average of the three RGB components for the pixel
   */
  public int intensity() {
    return ((int) (((double) (rGBColor[0] + rGBColor[1] + rGBColor[2])) / 3));
  }

  /**
   * Gets the weighted sum of the three RGB components for the pixel.
   *
   * @return the weighted sum of the three RGB components for the pixel
   */
  public int luma() {
    return ((int) (0.2126 * rGBColor[0] + 0.7152 * rGBColor[1] + 0.0722 * rGBColor[2]));
  }

  /**
   * Changes the color of the current Pixel based on the filter applied by the user.
   * Added in Part 2 of the Assignment to accommodate the new sepia and greyscale transformations.
   *
   * @param filter a 2-dimensional array that represents a filter to be applied to the pixel.
   */
  public int[] changeColor(double[][] filter) {
    int[] changed = new int[3];

    // iterate through the matrix to be applied to the pixel.
    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter.length; j++) {
        double diffColor = this.rGBColor[i] * filter[i][j]; // current color multiplied by matrix.
        changed[i] = changed[i] + (int) diffColor; // save the current color in the changed array.
      }
    }

    return changed;
  }
}
