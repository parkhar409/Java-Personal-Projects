package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to either
 * greyscale (luma) or Sepia tone their Image.
 */
public class CommandColorTransformationImpl implements ICommand {
  private final String name;
  private final String typeOfTransformation;
  private final String alteredImage;

  /**
   * The constructor of our ColorTransformation Class.
   * @param arguments The user's arguments.
   */
  public CommandColorTransformationImpl(ArrayList<String> arguments) {
    this.name = arguments.get(0);
    this.typeOfTransformation = arguments.get(1);
    this.alteredImage = arguments.get(2);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.colorTransformation(this.name, this.typeOfTransformation, this.alteredImage);
  }
}
