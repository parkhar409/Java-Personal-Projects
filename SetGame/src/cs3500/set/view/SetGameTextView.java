package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * This class helps visualizing the game as a String.
 */
public class SetGameTextView implements SetGameView {
  protected SetGameModelState model;
  protected Appendable out;

  /**
   * A constructor that determines of the given board is null or not.
   * @param model the board that's intended to be visualized
   * @throws IllegalArgumentException when the given board is null
   */
  public SetGameTextView(SetGameModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The given board is null");
    }
    this.model = model;
  }

  /**
   * Constructor that would be most likely used in the controller.
   * @param model the board/grid to be displayed.
   * @param out what the output is being appended to.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public SetGameTextView(SetGameModelState model, Appendable out)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The given board is null");
    } if (out == null) {
      throw new IllegalArgumentException("The given appendable is null");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    String visualizedCard = "";
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        visualizedCard = visualizedCard + model.getCardAtCoord(row,col).toString();
        visualizedCard = visualizedCard + " ";
      }
      visualizedCard = visualizedCard.stripTrailing();
      visualizedCard = visualizedCard + "\n";
    }
    visualizedCard = visualizedCard.stripTrailing();
    return visualizedCard;
  }

  @Override
  public void renderGrid() throws IOException {
    this.out.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
