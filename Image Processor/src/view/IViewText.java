package view;

import java.io.IOException;

/**
 * Ths represents the view interface which helps display the messages.
 */
public interface IViewText {
  void renderMessage(String message) throws IOException;
}
