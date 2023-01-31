package controller;

import model.IModel;

/**
 * To represent a command to be passed to our program's controller, that will perform specialized
 * tasks based on the user's inputs.
 */
public interface ICommand {

  /**
   * Perform the command that the user desires to the image.
   *
   * @param modelImage the image that the command is performed on
   */
  void command(IModel modelImage);
}
