package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to
 * load in a new image to modify.
 */
public class CommandLoadImpl implements ICommand {
  private final String name;
  private final String fileName;

  /**
   * The constructor that will construct a new CommandLoadImpl for the program.
   * @param arguments the user's inputted arguments.
   */
  public CommandLoadImpl(ArrayList<String> arguments) {
    this.name = "image1";
    this.fileName = arguments.get(0);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.loadImage(this.name, this.fileName);
  }
}
