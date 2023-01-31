package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * This class represents the command object for mosaic.
 */
public class CommandMosaic implements ICommand {
  private final int numSeed;
  private final String name;
  private final String alteredImage;

  /**
   * This constructor represents the arguments the command object needs to take in.
   * @param arguments the different fields that the command object utilizes
   */
  public CommandMosaic(ArrayList<String> arguments) {
    this.numSeed = Integer.parseInt(arguments.get(0));
    this.name = arguments.get(1);
    this.alteredImage = arguments.get(2);
  }

  /**
   * Perform the command that the user desires to the image.
   *
   * @param modelImage the image that the command is performed on
   */
  @Override
  public void command(IModel modelImage) {
    modelImage.mosaic(this.numSeed, this.name, this.alteredImage);
  }
}
