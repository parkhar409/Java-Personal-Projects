package view;

import java.io.IOException;

import model.IModel;

/**
 * Represents the messages when one alters an image.
 */
public class View implements IView {
  private final IModel modelImage;
  private final Appendable out;

  /**
   * Constructs a view for the image model, where the constructor take in a model image as its
   * argument and appends it to the output.
   *
   * @param modelImage the image that is being viewed
   * @param out        the output
   * @throws IllegalArgumentException if the model image or output is null
   */
  public View(IModel modelImage, Appendable out) throws IllegalArgumentException {
    if (modelImage == null || out == null) {
      throw new IllegalArgumentException("Null modelImage or null appendable");
    } else {
      this.modelImage = modelImage;
      this.out = out;
    }
  }

  /**
   * Constructs a view for the image model, where the constructor takes in a model image as its
   * argument and produces the output.
   *
   * @param modelImage the image that is being viewed
   * @throws IllegalArgumentException if the model image is null
   */
  public View(IModel modelImage) throws IllegalArgumentException {
    if (modelImage == null) {
      throw new IllegalArgumentException("Null modelImage");
    } else {
      this.modelImage = modelImage;
      this.out = System.out;
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message);
    } catch (IOException e) {
      this.out.append("\n invalid message. try again.");
    }
  }
}
