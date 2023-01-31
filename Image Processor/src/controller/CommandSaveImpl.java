package controller;

import java.util.ArrayList;

import model.IModel;

/**
 * To represent a Command Class that will take care of the case in which the user wants to
 * save an image as a ppm file.
 */
public class CommandSaveImpl implements ICommand {
  private final String name;
  private final String fileName;

  /**
   * The constructor of the CommandSaveImpl that is able to save an image.
   * @param arguments the user's inputted arguments.
   */
  public CommandSaveImpl(ArrayList<String> arguments) {
    this.name = arguments.get(0);
    this.fileName = arguments.get(1);
  }

  @Override
  public void command(IModel modelImage) {
    modelImage.saveImage(this.name, this.fileName);
  }
}
