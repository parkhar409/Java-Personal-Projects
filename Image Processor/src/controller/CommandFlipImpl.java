package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to either
 * Flip their Image either vertically or horizontally.
 */
public class CommandFlipImpl implements ICommand {
  private final String name;
  private final String typeOfFlip;
  private final String alteredImage;

  /**
   * The constructor for the CommandFlipImpl Class.
   * @param arguments the user's inputted arguments.
   */
  public CommandFlipImpl(ArrayList<String> arguments) {
    this.name = arguments.get(0);
    this.typeOfFlip = arguments.get(1);
    this.alteredImage = arguments.get(2);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.flipImage(this.name, this.typeOfFlip, this.alteredImage);
  }
}
