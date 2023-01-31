package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class that handles rendering the triangle board.
 */
public class TriangleSolitaireTextView extends SolitaireTextView {

  /**
   * Takes the given board as an input and display the board as an output.
   * @param board the board that's intended to be created.
   * @throws IllegalArgumentException when the board is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState board) {
    super(board);
  }

  /**
   * Creates a text view of a Triangular Marble Solitaire game from a given model game state that
   * transmits the output to a given Appendable destination.
   *
   * @param board the given board.
   * @param output the given appendable object to be appended to.
   * @throws IllegalArgumentException if either board or output is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState board, Appendable output)
          throws IllegalArgumentException {
    super(board, output);
  }

  @Override
  public String toString() {
    String result = "";
    for (int row = 0; row < this.board.getBoardSize(); row += 1) {
      for (int col = 0; col < (this.board.getBoardSize() * 2 - 1) / 2 - row; col += 1) {
        result = result.concat(" ");
      }
      for (int col = 0; col <= row; col += 1) {
        if (this.board.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          result = result.concat(this.notOnTheRightEdgeValidSlot(row, col, "O"));
        } else if (this.board.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          result = result.concat(this.notOnTheRightEdgeValidSlot(row, col, "_"));
        }
      }
      if (row != this.board.getBoardSize() - 1) {
        result = result.concat("\n");
      }
    }
    return result;
  }
}
