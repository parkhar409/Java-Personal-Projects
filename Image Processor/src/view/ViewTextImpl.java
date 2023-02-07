package view;

import java.io.IOException;

import model.IModel;

/**
 * This class deals with the view for text-based inputs.
 */
public class ViewTextImpl implements IViewText {
  protected IModel state;
  protected Appendable app;

  /**
   * Constructor for this class.
   * @param state the model that the user is inputting
   * @param app helps to append the strings
   */
  public ViewTextImpl(IModel state, Appendable app) {
    if (state == null || app == null) {
      throw new IllegalArgumentException("objects are null");
    }
    this.state = state;
    this.app = app;
  }

  /**
   * Constructor for this class.
   * @param state the model that the user is inputting
   */
  public ViewTextImpl(IModel state) {
    if (state == null) {
      throw new IllegalArgumentException("Provided model is null");
    }
    this.state = state;
    this.app = System.out;
  }

  /**
   * Renders the message inputted.
   *
   * @param message the message that needs to be inputted
   * @throws IOException when the appendable is not accurate
   */
  public void renderMessage(String message) throws IOException {
    app.append(message);
  }
}
