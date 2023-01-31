package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class for the methods regarding displaying the boards.
 */
public abstract class SolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState board;
  protected final Appendable destination;

  /**
   * Takes the given board as an input and display the board as an output.
   * @param board the given board.
   * @throws IllegalArgumentException if the board is null;
   */
  public SolitaireTextView(MarbleSolitaireModelState board) throws IllegalArgumentException {
    if (board == null) {
      throw new IllegalArgumentException("Board can't be null");
    }
    this.board = board;
    this.destination = System.out;
  }

  /**
   * Take the given board as an input and display the board as an
   * output by appending it to the appendable object.
   * @param board the given board.
   * @param output the appendable object.
   * @throws IllegalArgumentException if either the board or output is null;
   */
  public SolitaireTextView(MarbleSolitaireModelState board, Appendable output)
          throws IllegalArgumentException {
    if (board == null) {
      throw new IllegalArgumentException("Provided state cannot be null");
    }
    if (output == null) {
      throw new IllegalArgumentException("Appendable destination cannot be null");
    }
    this.board = board;
    this.destination = output;
  }

  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }

  @Override
  public abstract String toString();

  protected String notOnTheRightEdgeValidSlot(int row, int col, String slotSymbol) {
    if (this.isRightEdgeSlot(row, col)) {
      return slotSymbol;
    } else {
      return slotSymbol + " ";
    }
  }

  protected boolean isRightEdgeSlot(int row, int col) {
    boolean atRightEdge;
    try {
      atRightEdge = this.board.getSlotAt(row, col + 1)
              == MarbleSolitaireModelState.SlotState.Invalid;
    } catch (IllegalArgumentException e) {
      atRightEdge = true;
    }
    return atRightEdge;
  }
}
