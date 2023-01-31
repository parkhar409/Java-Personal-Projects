package model;

import java.util.HashMap;

/**
 * This interface represents the methods that can be used on an image. One can load in an image and
 * perform what they desire, whether it's flipping the image vertically or horizontally, brightening
 * or darkening the image, or greyscale the image. One can save the altered image afterwards.
 */
public interface IModel {

  /**
   * Flips an image horizontally or vertically.
   *
   * @param name         the name of the new image that is flipped
   * @param typeOfFlip   either a horizontal or vertical flip
   * @param alteredImage the image to flip
   * @throws IllegalArgumentException if the type of flip is invalid
   */
  void flipImage(String name, String typeOfFlip, String alteredImage)
          throws IllegalArgumentException;

  /**
   * Brightens or darkens an image based on the amount. If the amount is positive, the image will
   * brighten by that amount, and if the amount is negative, the image will darken by that amount.
   *
   * @param name         the name of the new image that is brightened or darkened.
   * @param amount       how brightened or darkened the image should become.
   * @param alteredImage the image to brighten or darken.
   */
  void brightenOrDarkenImage(String name, int amount, String alteredImage);

  /**
   * Greyscale an image based on the type of component, which could be by value, intensity, luma,
   * or the three RGB components.
   *
   * @param name            the name of the new image that is greyscaled
   * @param typeOfComponent the type of component to greyscale
   * @param alteredImage    the image to greyscale
   * @throws IllegalArgumentException if the type of component is invalid
   */
  void greyscaleImage(String name, String typeOfComponent, String alteredImage)
          throws IllegalArgumentException;

  /**
   * Filter an image based on the type of filter, which could be either blurring or sharpening the
   * image.
   *
   * @param name            the name of the new image that is filtered
   * @param typeOfFiltering the type of filtering
   * @param alteredImage    the image to filter
   * @throws IllegalArgumentException if the type of filtering is invalid
   */
  void filter(String name, String typeOfFiltering, String alteredImage)
          throws IllegalArgumentException;

  /**
   * Apply a color transformation based on the type of transformation, which could be either
   * greyscale (luma) or sepia tone.
   *
   * @param name                 the name of the image that is color transformed
   * @param typeOfTransformation the type of color transformation
   * @param alteredImage         the image to color transform
   * @throws IllegalArgumentException if the type of color transformation is invalid
   */
  void colorTransformation(String name, String typeOfTransformation, String alteredImage)
          throws IllegalArgumentException;

  /**
   * Loads an image from the file and given a name.
   *
   * @param name     the name of the image that is loaded
   * @param fileName the file for the image to load
   * @throws IllegalArgumentException if the file is not found
   */
  void loadImage(String name, String fileName) throws IllegalArgumentException;

  /**
   * Saves an image to the file as a ppm image and given a name.
   *
   * @param name     the name of the image that is saved
   * @param fileName the file name for the image to save
   * @throws IllegalArgumentException if the file is not found
   */
  void saveImage(String name, String fileName) throws IllegalArgumentException;

  /**
   * Gets the altered image of the original image.
   *
   * @return an ArrayList of Images consisting of the altered images of the original image
   */
  HashMap<String, Image> getAlteredImage();

  /**
   * This method takes a specific seed number and mosaicks the method accordingly.
   * @param seedNum the number of seeds to place on the image
   * @param name the name of the image
   * @param alteredImage the name of the altered image
   */
  void mosaic(int seedNum, String name, String alteredImage);

}
