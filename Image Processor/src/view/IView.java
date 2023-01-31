package view;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by
 * a view for the Image processor.
 */
public interface IView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the altered image to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
