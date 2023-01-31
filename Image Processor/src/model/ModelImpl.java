package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import controller.Utils;

/**
 * This class can load in an image, change the image, and save the altered image to an ArrayList of
 * altered images.
 */
public class ModelImpl implements IModel {

  // modified this variable from an ArrayList to a HashMap upon TA recommendation.
  private final HashMap<String, Image> alteredImages;

  /**
   * Constructs an ArrayList of Images, where altered images of the original image are added to it.
   *
   * @param alteredImages the ArrayList of images that are altered
   * @throws IllegalArgumentException if the altered image is null
   */
  public ModelImpl(HashMap<String, Image> alteredImages) throws IllegalArgumentException {
    if (alteredImages == null) {
      throw new IllegalArgumentException("Altered image can't be null.");
    } else {
      this.alteredImages = alteredImages;
    }
  }

  public ModelImpl() {
    this.alteredImages = new HashMap<>();
  }

  @Override
  public void flipImage(String name, String typeOfFlip, String alteredImage) {
    Image imageVersion = this.getCopiedAlteredImage(name, alteredImages, alteredImage);

    //used to get the original position of pixel colors and adjust the position color to the
    //flipped image
    Image defaultImage = this.getCopiedAlteredImage(name, alteredImages, alteredImage);

    for (int i = 0; i < imageVersion.getLength(); i++) {
      for (int j = 0; j < imageVersion.getWidth(); j++) {

        if (typeOfFlip.equals("horizontal")) {
          //changes the image to become horizontal
          imageVersion.getPixel(j, i).setXPosition(imageVersion.getWidth() - 1 - j);

          //changes the pixel colors to match the horizontal image
          imageVersion.getPixel(j, i).setColor(defaultImage.getPixel(
                  imageVersion.getWidth() - 1 - j, i).getColor());

        } else if (typeOfFlip.equals("vertical")) {
          //changes the image to become vertical
          imageVersion.getPixel(j, i).setYPosition(imageVersion.getLength() - 1 - i);

          //changes the pixel colors to match the vertical image
          imageVersion.getPixel(j, i).setColor(defaultImage.getPixel(
                  j, imageVersion.getLength() - 1 - i).getColor());

        } else {
          throw new IllegalArgumentException("Invalid type of flip.");
        }
      }
    }

    //adds the newly flipped image to the ArrayList of altered Images
    this.alteredImages.put(alteredImage, imageVersion);
  }

  @Override
  public void brightenOrDarkenImage(String name, int amount, String alteredImage) {
    Image imageVersion = this.getCopiedAlteredImage(name, alteredImages, alteredImage);

    for (int i = 0; i < imageVersion.getLength(); i++) {
      for (int j = 0; j < imageVersion.getWidth(); j++) {

        //make a copy of the RGB components of the pixel to be adjusted
        int[] changedColor = imageVersion.getPixel(j, i).getColor();

        //add or subtract each RGB component by the amount
        changedColor[0] = changedColor[0] + amount;
        changedColor[1] = changedColor[1] + amount;
        changedColor[2] = changedColor[2] + amount;

        //ensures that the RGB component value isn't above 255 or below 0, as those values
        //are invalid
        clamp(changedColor);

        //change the RGB components of the pixel for the image that is being altered to
        //the new RGB components
        imageVersion.getPixel(j, i).setColor(changedColor);
      }
    }
    //adds the newly brightened or darkened image to the ArrayList of altered images
    this.alteredImages.put(alteredImage, imageVersion);
  }

  /**
   * Set the maximum RGB component value to be 255 and the minimum RGB component value to be 0.
   *
   * @param changedColor the new color's RGB components
   */
  private void clamp(int[] changedColor) {
    for (int i = 0; i < 3; i++) {

      //RGB component max value is 255, if the value goes past that, automatically set it to 255
      if (changedColor[i] > 255) {
        changedColor[i] = 255;

        //RGB component min value is 0, if the value goes below that, automatically set it to 0
      } else if (changedColor[i] < 0) {
        changedColor[i] = 0;
      }
    }
  }

  @Override
  public void greyscaleImage(String name, String typeOfComponent, String alteredImage) {
    Image imageVersion = this.getCopiedAlteredImage(name, alteredImages, alteredImage);

    for (int i = 0; i < imageVersion.getLength(); i++) {
      for (int j = 0; j < imageVersion.getWidth(); j++) {
        //get the pixel for the image
        Pixel pixel = imageVersion.getPixel(j, i);

        //get the RGB component values for the pixel
        int[] colors = pixel.getColor();

        //changes the RGB component values based on the component type
        switch (typeOfComponent) {
          case "value":
            colors[0] = pixel.value();
            colors[1] = pixel.value();
            colors[2] = pixel.value();
            break;
          case "intensity":
            colors[0] = pixel.intensity();
            colors[1] = pixel.intensity();
            colors[2] = pixel.intensity();
            break;
          case "red":
            colors[1] = colors[0];
            colors[2] = colors[0];
            break;
          case "green":
            colors[0] = colors[1];
            colors[2] = colors[1];
            break;
          case "blue":
            colors[0] = colors[2];
            colors[1] = colors[2];
            break;
          default:
            throw new IllegalArgumentException("Invalid type of component.");
        }

        //change the RGB components of the pixel to the filtered RGB components.
        imageVersion.getPixel(j, i).setColor(colors);
      }
    }

    //adds the image with the luma filter applied to the ArrayList of altered images.
    this.alteredImages.put(alteredImage, imageVersion);
  }

  @Override
  public void filter(String name, String typeOfFiltering, String alteredImage)
          throws IllegalArgumentException {
    Image imageVersion = this.getCopiedAlteredImage(name, alteredImages, alteredImage);
    double[][] filterType;

    //set the filterType to what filter the user wants
    if (typeOfFiltering.equals("sharpen")) {
      filterType = new double[][]{ { -0.125, -0.125, -0.125, -0.125, -0.125 },
        { -0.125, 0.25, 0.25, 0.25, -0.125 },
        { -0.125, 0.25, 1, 0.25, -0.125 },
        { -0.125, 0.25, 0.25, 0.25, -0.125 },
        { -0.125, -0.125, -0.125, -0.125, -0.125 } };

    } else if (typeOfFiltering.equals("blur")) {
      filterType = new double[][]{ { 0.0625, 0.125, 0.0625 },
        { 0.125, 0.25, 0.125 },
        { 0.0625, 0.125, 0.0625 } };

    } else {
      throw new IllegalArgumentException("This type of filtering is not supported.");
    }

    for (int row = 0; row < imageVersion.getLength() - 1; row++) {
      for (int col = 0; col < imageVersion.getWidth() - 1; col++) {
        //get the surrounding kernels of the particular pixel
        double[][][] kernel = getSurroundingKernel(row, col, imageVersion, filterType.length);

        //apply the filter to those surrounding kernels
        int[] filter = applyFilter(filterType, kernel);

        //set a list of the newly changed RGB component values
        int[] changedColor = new int[]{ filter[0], filter[1], filter[2] };

        //ensure that the RGB component values doesn't go above 255 or below 0
        this.clamp(changedColor);

        //set the color to the corresponding pixels
        imageVersion.getPixel(col, row).setColor(changedColor);
      }
    }
    this.alteredImages.put(alteredImage, imageVersion);
  }

  /**
   * Get the surrounding kernels of the particular kernel you want, and set them to a new double
   * that is a 3D array consisting of the row, column, and RGB component values.
   *
   * @param row   the row of the particular kernel you want, which is the center kernel
   * @param col   the column of the particular kernel you want, which is the center kernel
   * @param image the image that you want to filter
   * @param size  the kernel size
   * @return the RGB components of the pixels surrounding the center kernel
   */
  private double[][][] getSurroundingKernel(int row, int col, Image image, int size) {
    //create a new double 3D array
    double[][][] surroundingKernel = new double[size][size][3];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        //if the kernel surrounding the particular kernel is valid
        if (i + (row - (size / 2)) < image.getLength()
                && j + (col - (size / 2)) < image.getWidth()
                && i + (row - (size / 2)) >= 0
                && j + (col - (size / 2)) >= 0) {

          //get the row, column, and RGB components of the surrounding kernel
          surroundingKernel[i][j][0] = image.getPixel(j + (col - (size / 2)),
                  i + (row - (size / 2))).getColor()[0];
          surroundingKernel[i][j][1] = image.getPixel(j + (col - (size / 2)),
                  i + (row - (size / 2))).getColor()[1];
          surroundingKernel[i][j][2] = image.getPixel(j + (col - (size / 2)),
                  i + (row - (size / 2))).getColor()[2];
        }
      }
    }
    return surroundingKernel;
  }

  /**
   * Apply the filter to the surrounding kernels.
   *
   * @param kernel            the kernel that contains the information to multiply for the accurate
   *                          filter
   * @param surroundingKernel the RGB components of the pixels surrounding the center kernel
   * @return the filtered color that is applied
   */
  private int[] applyFilter(double[][] kernel, double[][][] surroundingKernel) {
    int modifyRed = 0;
    int modifyGreen = 0;
    int modifyBlue = 0;

    for (int i = 0; i < surroundingKernel.length; i++) {
      for (int j = 0; j < surroundingKernel.length; j++) {
        int changedRed = (int) (surroundingKernel[i][j][0] * kernel[i][j]);
        modifyRed = modifyRed + changedRed;
        int changedGreen = (int) (surroundingKernel[i][j][1] * kernel[i][j]);
        modifyGreen = modifyGreen + changedGreen;
        int changedBlue = (int) (surroundingKernel[i][j][2] * kernel[i][j]);
        modifyBlue = modifyBlue + changedBlue;
      }
    }
    return new int[]{ modifyRed, modifyGreen, modifyBlue };
  }

  private ArrayList<ArrayList<Pixel>> getCluster(int seedNum, String name, String alteredImage) {
    Image imageCopy = this.getCopiedAlteredImage(name, alteredImages, alteredImage);
    ArrayList<Pixel> imageSeeds = imageCopy.getSeeds(seedNum);
    ArrayList<ArrayList<Pixel>> clusters = new ArrayList<>();

    for (int k = 0; k < imageSeeds.size(); k++) {
      clusters.add(new ArrayList<>());
    }
    for (int x = 0; x < imageCopy.getWidth(); x++) {
      for (int y = 0; y < imageCopy.getLength(); y++) {
        double shortDist = Double.POSITIVE_INFINITY;
        int seed = 0;
        for (int i = 0; i < imageSeeds.size(); i++) {
          int xSeed = imageSeeds.get(i).getXPosition();
          int ySeed = imageSeeds.get(i).getYPosition();
          double currDist = (Math.sqrt(Math.pow(x - xSeed, 2)) + (Math.pow(y - ySeed, 2)));
          if (currDist < shortDist) {
            shortDist = currDist;
            seed = i;
          }
        }
        clusters.get(seed).add(imageCopy.getPixel(x, y));
      }
    }
    return clusters;
  }

  /**
   * This method takes a specific seed number and mosaicks the method accordingly.
   *
   * @param seedNum      the number of seeds to place on the image
   * @param name         the name of the image
   * @param alteredImage the name of the altered image
   */
  public void mosaic(int seedNum, String name, String alteredImage) {
    if (name == null || alteredImage == null || seedNum < 1) {
      throw new IllegalArgumentException("inputs are invalid");
    }
    Image imageCopy = this.getCopiedAlteredImage(name, alteredImages, alteredImage);
    for (int i = 0; i < getCluster(seedNum, name, alteredImage).size(); i++) {
      int red = 0;
      int green = 0;
      int blue = 0;
      for (int j = 0; j < getCluster(seedNum, name, alteredImage).get(i).size(); j++) {
        ArrayList<Pixel> cluster = getCluster(seedNum, name, alteredImage).get(i);
        red += cluster.get(j).getColor()[0];
        green += cluster.get(j).getColor()[1];
        blue += cluster.get(j).getColor()[2];
      }
      for (int k = 0; k < getCluster(seedNum, name, alteredImage).get(i).size(); k++) {
        ArrayList<Pixel> cluster = getCluster(seedNum, name, alteredImage).get(i);

        int redAvg = getCluster(seedNum, name, alteredImage).get(i).size() / red;
        int greenAvg = getCluster(seedNum, name, alteredImage).get(i).size() / green;
        int blueAvg = getCluster(seedNum, name, alteredImage).get(i).size() / blue;
        cluster.get(k).setColor(new int[]{ redAvg, greenAvg, blueAvg });

      }
    }
    this.alteredImages.put(alteredImage, imageCopy);
  }

  /**
   * This method applies both greyscale and sepia to the image.
   *
   * @param name                 the name of the image that is color transformed
   * @param typeOfTransformation the type of color transformation
   * @param alteredImage         the image to color transform
   * @throws IllegalArgumentException when the type of transformation is not supported
   */
  @Override
  public void colorTransformation(String name, String typeOfTransformation, String alteredImage)
          throws IllegalArgumentException {
    Image imageVersion = this.getCopiedAlteredImage(name, alteredImages, alteredImage);

    for (int i = 0; i < imageVersion.getLength(); i++) {
      for (int j = 0; j < imageVersion.getWidth(); j++) {
        Pixel pixel = imageVersion.getPixel(j, i);

        if (typeOfTransformation.equals("sepia")) {

          // sepia filter
          double[][] sepia = new double[][]{ { 0.393, 0.769, 0.189 },
            { 0.349, 0.686, 0.168 },
            { 0.272, 0.534, 0.131 } };

          // apply sepia filter to the pixel
          int[] newRGB = pixel.changeColor(sepia);

          // protect against extreme color changes.
          this.clamp(newRGB);

          // set the current pixel's RGB
          imageVersion.getPixel(j, i).setColor(newRGB); // mutate the image's current pixel.

        } else if (typeOfTransformation.equals("luma")) {

          // the "luma" filter
          double[][] luma = new double[][]{ { 0.2126, 0.7152, 0.0722 },
              { 0.2126, 0.7152, 0.0722 },
              { 0.2126, 0.7152, 0.0722 } };

          // apply luma filter to the pixel.
          int[] newRGB = pixel.changeColor(luma);

          // protect against extreme color changes.
          this.clamp(newRGB);

          // set the current pixel's RGB
          imageVersion.getPixel(j, i).setColor(newRGB); // mutate the image's current pixel.

        } else {
          // if the user inputs any other String.
          throw new IllegalArgumentException("This type of transformation is not supported.");
        }
      }
    }

    // add the altered Image to the existing set of images.
    this.alteredImages.put(alteredImage, imageVersion);
  }

  /**
   * This loads the image to work with.
   *
   * @param name     the name of the image that is loaded
   * @param fileName the file for the image to load
   * @throws IllegalArgumentException when the file is not found.
   */
  @Override
  public void loadImage(String name, String fileName) throws IllegalArgumentException {
    //try to add a new Image read from a PPM file to the ArrayList of altered images using the
    //Utils class

    // CHANGED PUT STATEMENT FROM readPPM to readFile to accommodate jpg files and other types!
    try {
      this.alteredImages.put(name, new Image(Utils.readFile(fileName), name));

    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }
  }

  /**
   * This saves the image we are working with.
   *
   * @param alteredImage the name of the image that is saved
   * @param fileName     the file name for the image to save
   * @throws IllegalArgumentException when the file is not found.
   */
  @Override
  public void saveImage(String alteredImage, String fileName) throws IllegalArgumentException {
    //you want to get the altered image that you want to save,
    // so the first argument is alteredImage
    if (fileName.endsWith("ppm")) {
      this.savePPM(alteredImage, fileName);
    } else {
      // gets current Image.
      Image imageVersion = this.getCopiedAlteredImage(alteredImage, alteredImages, alteredImage);

      // convert to BufferedImage.
      BufferedImage tempBuffer = new BufferedImage(imageVersion.getWidth(),
              imageVersion.getLength(),
              BufferedImage.TYPE_INT_RGB);

      // Copies contents of the Image to the BufferedImage.
      for (int row = 0; row < imageVersion.getLength(); row++) {
        for (int col = 0; col < imageVersion.getWidth(); col++) {
          Pixel p = imageVersion.getPixel(col, row);

          // convert the pixel color to a java Color object.
          Color temporaryColor = new Color(p.getColor()[0],
                  p.getColor()[1],
                  p.getColor()[2]);

          tempBuffer.setRGB(col, row, temporaryColor.getRGB());
        }
      }

      // now write the BufferedImage into the intended fileType that was specified
      // in the method header.
      try {
        ImageIO.write(tempBuffer, fileName.substring(fileName.lastIndexOf(".")),
                new File(fileName));
      } catch (IOException e) {
        throw new IllegalArgumentException("Error saving the file.");
      }
    }
  }

  /**
   * To save an image in the PPM format. Finds the file path and writes a new file in the ppm format
   * to save.
   *
   * @param alteredImage the name to be assigned to this image.
   * @param fileName     the file path of the image to be saved in PPM format.
   */
  private void savePPM(String alteredImage, String fileName) {
    //you want to get the altered image that you want to save,
    // so the first argument is alteredImage
    Image imageVersion = this.getCopiedAlteredImage(alteredImage, alteredImages, alteredImage);

    PrintWriter savedImage;

    try {
      savedImage = new PrintWriter(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Couldn't find file name.");
    }

    //PPM format:
    //P3 defines the image format
    savedImage.write("P3");
    savedImage.write("\n");

    //Write the number of columns and rows of the image that's being saved.
    savedImage.write(imageVersion.getWidth() + " " + imageVersion.getLength());
    savedImage.write("\n");

    //Write the maximum RGB component value
    savedImage.write(255);
    savedImage.write("\n");

    //Write the RGB component values for each pixel in the image that's being saved
    for (int i = 0; i < imageVersion.getLength(); i++) {
      for (int j = 0; j < imageVersion.getWidth(); j++) {
        Pixel pixel = imageVersion.getPixel(j, i);

        //Red component value
        savedImage.write(pixel.getColor()[0]);
        savedImage.write("\n");

        //Green component value
        savedImage.write(pixel.getColor()[1]);
        savedImage.write("\n");

        //Blue component value
        savedImage.write(pixel.getColor()[2]);
        savedImage.write("\n");
      }
    }
    savedImage.close();
  }

  /**
   * This creates a copy of the altered image.
   *
   * @return a copy of the image.
   */
  @Override
  public HashMap<String, Image> getAlteredImage() {
    return this.alteredImages;
  }

  /**
   * Gets a copy of the altered image.
   *
   * @param name          the name of the copy
   * @param alteredImages the ArrayList of altered images
   * @param alteredImage  the altered image to copy
   * @return a copy of the altered image
   * @throws IllegalArgumentException the altered image doesn't exist
   */
  private Image getCopiedAlteredImage(String name, HashMap<String, Image> alteredImages,
                                      String alteredImage) throws IllegalArgumentException {
    for (String s : alteredImages.keySet()) {
      if (s.equals(name)) {
        return new Image(alteredImages.get(s).imageCopy(), alteredImage);
      }
    }
    throw new IllegalArgumentException("Altered image doesn't exist.");
  }
}