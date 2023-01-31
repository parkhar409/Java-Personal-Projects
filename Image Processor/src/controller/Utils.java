package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Pixel;

/**
 * To represent an utils class with the proper utilities to read a ppm file.
 */
public class Utils {

  /**
   * To read a given PPM file and return it as a 2 dimensional array of pixels.
   *
   * @param filepath the file path of a ppm file.
   * @return a two-dimensional array of pixels to represent an image.
   * @throws FileNotFoundException if there is no file path that is valid.
   */
  public static ArrayList<ArrayList<Pixel>> readPPM(String filepath)
          throws FileNotFoundException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filepath + " not found!");
      throw new FileNotFoundException(e.getMessage());
    }
    StringBuilder builder = new StringBuilder();

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    ArrayList<ArrayList<Pixel>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      image.add(new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        image.get(i).add(new Pixel(j, i, new int[]{r, g, b}));
      }
    }
    return image;
  }

  /**
   * To read the provided file at the given filepath depending on the type of file that it is.
   * Different file types will warrant different helper methods being called
   * to help interpret the file.
   *
   * @param filepath the file path of the image in question.
   * @return an ArrayList of an ArrayList of Pixels, meant to represent a more basic
   *         interpretation of our image.
   * @throws FileNotFoundException in the case that there was an error locating or converting the
   *                               file.
   */
  public static ArrayList<ArrayList<Pixel>> readFile(String filepath)
          throws FileNotFoundException {

    File fileType; // A File Object that will represent the file at our file path.
    BufferedImage buffered; // A BufferImage Object to represent non-PPM files that are read.
    ArrayList<ArrayList<Pixel>> basicImage; // to be returned.

    try {
      fileType = new File(filepath);
    } catch (NullPointerException e) {
      throw new FileNotFoundException("Provided file name was invalid.");
    }
    if (filepath.endsWith("ppm")) {
      try {
        basicImage = readPPM(filepath); // if the file is ppm, read it here in utils class
      } catch (IOException e) {
        throw new FileNotFoundException("PPM file was not properly read.");
      }
    } else {
      try {
        buffered = ImageIO.read(fileType);
        basicImage = decipherBuffer(buffered);
      } catch (IOException e) {
        throw new FileNotFoundException("File type could not be read.");
      }
    }

    return basicImage;
  }


  /**
   * To decode the BufferedImage Object type that is returned by usage of the ImageIO.read method.
   *
   * @param buffered A BufferedImage that is not fully accessible by the Utils class as of now.
   * @return an ArrayList of an ArrayList of Pixels, which can more easily be manipulated
   *         and interpreted by the existing methods and classes elsewhere in our project.
   */
  private static ArrayList<ArrayList<Pixel>> decipherBuffer(BufferedImage buffered) {
    ArrayList<ArrayList<Pixel>> imageList = new ArrayList<>();

    // iterate through the arraylist and add the necessary Pixel elements.
    for (int rowNum = 0; rowNum < buffered.getHeight(); rowNum++) {
      // to add a new row of pixels to the existing List.
      // a new row is needed to prevent the off-chance of an IndexOutOfBoundsException.
      imageList.add(new ArrayList<>());

      for (int colNum = 0; colNum < buffered.getWidth(); colNum++) {
        int colour = buffered.getRGB(colNum, rowNum); // get RGB at the current pixel.
        Color c = new Color(colour); // convert the color to a Color Object.
        int blue = c.getBlue();
        int red = c.getRed();
        int green = c.getGreen();
        int[] setOfColorsInArray = new int[]{red, green, blue};
        Pixel p = new Pixel(rowNum, colNum, setOfColorsInArray); // new Pixel.
        imageList.get(rowNum).add(p);
      }
    }
    return imageList;
  }
}

