package controller;

import java.io.IOException;

/**
 * This interface represents the image processor controller and allows the user to alter an image
 * based on their inputs.
 */
public interface IController {

  /**
   * Processes an image manipulation to an image based on the command.
   */
  void playCommand() throws IOException;
}
