package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to either
 * sharpen or blur their Image.
 */
public class CommandFilterImpl implements ICommand {
  private final String name;
  private final String typeOfFiltering;
  private final String alteredImage;

  /**
   * The constructor of our Filter Class.
   * @param arguments The user's arguments.
   */
  public CommandFilterImpl(ArrayList<String> arguments) {
    this.name = arguments.get(0);
    this.typeOfFiltering = arguments.get(1);
    this.alteredImage = arguments.get(2);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.filter(this.name, this.typeOfFiltering, this.alteredImage);
  }
}
