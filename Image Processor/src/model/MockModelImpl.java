package model;

import java.util.HashMap;

/**
 * To make a mock model that will be utilized for testing how the controller handles user inputs.
 */
public class MockModelImpl implements IModel {
  StringBuilder log;

  public MockModelImpl(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void flipImage(String name, String typeOfFlip, String alteredImage)
          throws IllegalArgumentException {
    if (name == null || typeOfFlip == null || alteredImage == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("ImageName: " + name + " FlipType: " +
            typeOfFlip + " AlteredImageName: " + alteredImage);
  }

  @Override
  public void brightenOrDarkenImage(String name, int amount, String alteredImage) {
    log.append("ImageName: " + name + " AmountBrightened: " + amount +
            " AlteredImageName: " + alteredImage);
  }

  @Override
  public void greyscaleImage(String name, String typeOfComponent, String alteredImage)
          throws IllegalArgumentException {
    if (name == null || typeOfComponent == null || alteredImage == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("ImageName: " + name + " ComponentType: " +
            typeOfComponent + " AlteredImageName: " + alteredImage);
  }

  @Override
  public void filter(String name, String typeOfFiltering, String alteredImage)
          throws IllegalArgumentException {
    if (name == null || typeOfFiltering == null || alteredImage == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("ImageName: " + name + " Filtering Type: " +
            typeOfFiltering + " AlteredImageName: " + alteredImage);
  }

  @Override
  public void colorTransformation(String name, String typeOfTransformation, String alteredImage)
          throws IllegalArgumentException {
    if (name == null || typeOfTransformation == null || alteredImage == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("ImageName: " + name + " Transformation Type: " +
            typeOfTransformation + " AlteredImageName: " + alteredImage);
  }

  @Override
  public void loadImage(String name, String fileName) throws IllegalArgumentException {
    if (name == null || fileName == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("LoadedImageName: " + name + " LoadedFileName: " + fileName);
  }

  @Override
  public void saveImage(String name, String fileName) throws IllegalArgumentException {
    if (name == null || fileName == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("SavedImageName: " + name + " SavedFileName: " + fileName);
  }

  @Override
  public HashMap<String, Image> getAlteredImage() {
    return null;
  }

  @Override
  public void mosaic(int seedNum, String name, String alteredImage) {
    if (name == null || seedNum < 1 || alteredImage == null) {
      throw new IllegalArgumentException("Invalid input values.");
    }

    log.append("ImageName: " + name + " ComponentType: " +
            seedNum + " AlteredImageName: " + alteredImage);
  }
}
