package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * To represent an implementation of an image, with the appropriate methods and features that an
 * Image object should have.
 */
public class Image {
  private final ArrayList<ArrayList<Pixel>> imageMakeup;
  private final String title;

  /**
   * Constructs a new Image object given the pixels that make up the image as well as the title of
   * the image.
   *
   * @param imageMakeup To represent the pixels that make up an image
   * @param title       To represent the name of the given image.
   * @throws IllegalArgumentException if either the imageMakeup or title fields passed to the
   *                                  constructor are null.
   */
  public Image(ArrayList<ArrayList<Pixel>> imageMakeup, String title)
          throws IllegalArgumentException {

    if (title == null || imageMakeup == null) {
      throw new IllegalArgumentException("Invalid set of Image pixels and/or Image name.");
    }
    this.imageMakeup = imageMakeup;
    this.title = title;
  }

  /**
   * Copies the set of pixels from the constructed Image to prevent unwanted mutation of the
   * original ArrayList of an ArrayList of pixels.
   *
   * @return a duplicate set of pixels that represent the constructed Image.
   */
  public ArrayList<ArrayList<Pixel>> imageCopy() {
    ArrayList<ArrayList<Pixel>> arrayCopy = new ArrayList<>();

    for (int x = 0; x < imageMakeup.size(); x++) {
      arrayCopy.add(new ArrayList<Pixel>());

      for (int y = 0; y < imageMakeup.get(0).size(); y++) {
        arrayCopy.get(x).add(new Pixel(x, y, imageMakeup.get(x).get(y).getColor()));
      }
    }

    return arrayCopy;
  }

  /**
   * Returns the Pixel at the given cartesian point.
   *
   * @param x the current x-position of a pixel in the image.
   * @param y the current y-position of that same pixel in the image.
   * @return the Pixel that exists at the given point as provided by the parameters of the method.
   * @throws IllegalArgumentException if the provided coordinates are invalid.
   */
  public Pixel getPixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x > this.getWidth() - 1 || y < 0 || y > this.getLength() - 1) {
      throw new IllegalArgumentException("Invalid position at (" + x + "," + y + ").");
    } else {
      return imageMakeup.get(y).get(x);
    }
  }

  /**
   * Serves as a getter for the title of the image.
   *
   * @return the name of the image.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the length (height) of the image.
   *
   * @return the length (height) of the image
   */
  public int getLength() {
    return this.imageMakeup.size();
  }

  /**
   * Gets the width of the image.
   *
   * @return the width of the image
   */
  public int getWidth() {
    return this.imageMakeup.get(0).size();
  }

  // TODO: implement redIntensity, greenIntensity, and blueIntensity methods to
  // create HashMaps of each RGB color quantity and the commonality of the occurrences.

  /**
   * This method gets the seeds in an image.
   * @param seeds the seeds on the image
   * @return a map of the seeds
   */
  public ArrayList<Pixel> getSeeds(int seeds) {
    Random rand = new Random();
    ArrayList<Pixel> seedMap = new ArrayList<>();
    if (seeds < 1) {
      throw new IllegalArgumentException("incorrect seed value");
    } else {
      for (int i = 0; i < seeds; i++) {
        int x = rand.nextInt(this.getWidth());
        int y = rand.nextInt(this.getLength());
        Pixel pixelSeed = new Pixel(x, y);
        seedMap.add(pixelSeed);
      }
    }
    return seedMap;
  }

  /**
   * To return a HashMap documenting the occurrence of each intensity of the Red Color in the
   * image.
   *
   * @return the count of each intensity of the Color Red in the current Image object.
   */
  public HashMap<Integer, Integer> redIntensity() {
    HashMap<Integer, Integer> redIntensity = new HashMap<>();

    // to create a slot for each red intensity.
    for (int i = 0; i < 256; i++) {
      redIntensity.put(i, 0);
    }

    // to get the current red Value at the current Pixel of the Image.
    for (int row = 0; row < this.getLength(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel's RGB values (in a int array).
        int[] pixelColors = this.getPixel(col, row).getColor();

        // extracts the Red value from the array.
        int currentRed = pixelColors[0];

        // increments the current count of Pixels with this red intensity by 1.
        redIntensity.put(currentRed, redIntensity.get(currentRed) + 1);

      }
    }

    return redIntensity;
  }

  /**
   * To return a HashMap documenting the occurrence of each intensity of the Green Color in the
   * image.
   *
   * @return the count of each intensity of the Color Green in the current Image object.
   */
  public HashMap<Integer, Integer> greenIntensity() {
    HashMap<Integer, Integer> greenIntensity = new HashMap<>();

    // to create a slot for each green intensity.
    for (int i = 0; i < 256; i++) {
      greenIntensity.put(i, 0);
    }

    // to get the current green Value at the current Pixel of the Image.
    for (int row = 0; row < this.getLength(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel's RGB values (in a int array).
        int[] pixelColors = this.getPixel(col, row).getColor();

        // extracts the green value from the array.
        int currentGreen = pixelColors[1];

        // increments the current count of Pixels with this green intensity by 1.
        greenIntensity.put(currentGreen, greenIntensity.get(currentGreen) + 1);

      }
    }

    return greenIntensity;
  }

  /**
   * To return a HashMap documenting the occurrence of each intensity of the Blue Color in the
   * image.
   *
   * @return the count of each intensity of the Color Blue in the current Image object.
   */
  public HashMap<Integer, Integer> blueIntensity() {
    HashMap<Integer, Integer> blueIntensity = new HashMap<>();

    // to create a slot for each green intensity.
    for (int i = 0; i < 256; i++) {
      blueIntensity.put(i, 0);
    }

    // to get the current green Value at the current Pixel of the Image.
    for (int row = 0; row < this.getLength(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel's RGB values (in a int array).
        int[] pixelColors = this.getPixel(col, row).getColor();

        // extracts the green value from the array.
        int currentGreen = pixelColors[2];

        // increments the current count of Pixels with this green intensity by 1.
        blueIntensity.put(currentGreen, blueIntensity.get(currentGreen) + 1);

      }
    }

    return blueIntensity;
  }

}