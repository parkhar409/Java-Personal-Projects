package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to either
 * Brighten or Darken their Image.
 */
public class CommandBrightenOrDarkenImpl implements ICommand {
  private final String name;
  private final int amount;
  private final String alteredImage;

  /**
   * The constructor of our BrightenOrDarken Class.
   * @param arguments The user's arguments.
   */
  public CommandBrightenOrDarkenImpl(ArrayList<String> arguments) {

    // We have to take care of cases where we go Out of Bounds or we get a parse error.
    // Could do try catch here or handle the exception in the controller.
    this.name = arguments.get(0);
    this.amount = Integer.parseInt(arguments.get(1));
    this.alteredImage = arguments.get(2);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.brightenOrDarkenImage(this.name, this.amount, this.alteredImage);
  }
}
