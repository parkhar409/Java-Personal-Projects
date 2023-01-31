package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to
 * make their image grey according to a specific component.
 */
public class CommandGreyscaleImpl implements ICommand {
  private final String name;
  private final String typeofComponent;
  private final String alteredImage;

  /**
   * The constructor that constructs a new CommandGreyScaleImpl object.
   * @param arguments the user's inputted arguments.
   */
  public CommandGreyscaleImpl(ArrayList<String> arguments) {
    this.name = arguments.get(0);
    this.typeofComponent = arguments.get(1);
    this.alteredImage = arguments.get(2);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.greyscaleImage(this.name, this.typeofComponent, this.alteredImage);
  }
}
