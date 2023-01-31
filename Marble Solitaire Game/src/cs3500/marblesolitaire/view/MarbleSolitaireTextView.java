package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * {@code MarbleSolitaireTextView} helps visualizing the game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState board;
  protected Appendable destination;

  /**
   * Determines if the given board is null or has been created.
   * @param board the board that's intended to be created.
   * @throws IllegalArgumentException statement to be thrown when the board is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState board) throws IllegalArgumentException {
    if (board == null) {
      throw new IllegalArgumentException("The board can't be null");
    }
    this.board = board;
  }

  /**
   * Determines if the given board is null or has been created.
   * @param board is the board to be displayed.
   * @param destination is what the output is being appended to.
   * @throws IllegalArgumentException throws if any of the parameters is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState board, Appendable destination)
          throws IllegalArgumentException {
    if (board == null || destination == null) {
      throw new IllegalArgumentException("The board or destination is null");
    }
    this.board = board;
    this.destination = destination;
  }

  /**
   * Visualizes the board at any phase of the game.
   * @return  Visualization of the board in String.
   */
  @Override
  public String toString() {
    String visualizedBoard = "";
    for (int row = 0; row < this.board.getBoardSize(); row++) {
      for (int col = 0; col < this.board.getBoardSize(); col++) {
        if (this.board.getSlotAt(row,col) == MarbleSolitaireModelState.SlotState.Empty) {
          visualizedBoard = visualizedBoard + "_";
          visualizedBoard = visualizedBoard + " ";
        }
        else if (this.board.getSlotAt(row,col) == MarbleSolitaireModelState.SlotState.Marble) {
          visualizedBoard = visualizedBoard + "O";
          visualizedBoard = visualizedBoard + " ";
        }
        else if (this.board.getSlotAt(row,col) == MarbleSolitaireModelState.SlotState.Invalid) {
          visualizedBoard = visualizedBoard + " ";
          visualizedBoard = visualizedBoard + " ";
        }
      }
      visualizedBoard = visualizedBoard.stripTrailing();
      visualizedBoard = visualizedBoard + "\n";
    }
    visualizedBoard = visualizedBoard.stripTrailing();
    return visualizedBoard;
  }

  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}


