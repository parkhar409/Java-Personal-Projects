package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class that represent all three types of the game.
 */
public abstract class SolitaireModels implements MarbleSolitaireModel {
  protected final List<List<SlotState>> board;

  /**
   * Creates an English Solitaire game board with the given armthickness.
   * @param armThickness armthickness of the game.
   * @throws IllegalArgumentException if the armthickness is not a positive
   *                                  odd number that is greater than 3.
   */
  public SolitaireModels(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("Arm thickness/Length should be a " +
              "positive odd number that is greater than 3");
    }
    this.board = new ArrayList<>();
    int boardSize = 3 * armThickness - 2;
    for (int row = 0; row < boardSize; row += 1) {
      if (row < armThickness - 1 || row > boardSize - armThickness) {
        ArrayList<SlotState> armThicknessRow = new ArrayList<>();
        for (int col = 0; col < boardSize; col += 1) {
          if (col < armThickness - 1 || col > boardSize - armThickness) {
            armThicknessRow.add(SlotState.Invalid);
          } else {
            armThicknessRow.add(SlotState.Marble);
          }
        }
        this.board.add(armThicknessRow);
      } else {
        ArrayList<SlotState> boardSizeRow = new ArrayList<>();
        for (int col = 0; col < boardSize; col += 1) {
          if (col == boardSize / 2 && row == boardSize / 2) {
            boardSizeRow.add(SlotState.Empty);
          } else {
            boardSizeRow.add(SlotState.Marble);
          }
        }
        this.board.add(boardSizeRow);
      }
    }
  }

  /**
   * Updates the given cell's slotstate to Empty.
   * @param sRow row of the cell.
   * @param sCol column of the cell.
   */
  protected void emptySlot(int sRow, int sCol) {
    if (this.getSlotAt(sRow,sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("(" + sRow + ", " + sCol + ")" + " is an invalid cell");
    } else {
      this.board.get(this.getBoardSize() / 2).set(this.getBoardSize() / 2, SlotState.Marble);
      this.board.get(sRow).set(sCol, SlotState.Empty);
    }
  }

  /**
   * Moves a marble based on the game rules. From and To positions need to have Marbles,
   * a marble can only jump over a cell that has a marble, can only move to a cell that
   * is 2 cells away and empty.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is invalid.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int skippedRow = (fromRow + toRow) / 2;
    int skippedCol = (fromCol + toCol) / 2;
    if (this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      this.board.get(toRow).set(toCol, SlotState.Marble);
      this.board.get(skippedRow).set(skippedCol, SlotState.Empty);
    }
  }

  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    boolean fromMarbleSlot;
    boolean toMarbleSlot;
    boolean skippedMarble;

    int skippedRow = (fromRow + toRow) / 2;
    int skippedCol = (fromCol + toCol) / 2;
    if (this.getSlotAt(fromRow,fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("No marble to move");
    } else {
      fromMarbleSlot = this.getSlotAt(fromRow, fromCol) == SlotState.Marble;
    }
    if (this.getSlotAt(toRow,toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("The destination needs to be empty");
    } else {
      toMarbleSlot = this.getSlotAt(toRow, toCol) == SlotState.Empty;
    }
    if (this.skippedSlot(fromRow, fromCol, toRow, toCol) != SlotState.Marble) {
      throw new IllegalArgumentException("There is no marble between from and to slots");
    } else {
      skippedMarble = this.getSlotAt(skippedRow,skippedCol) == SlotState.Marble;
    }
    return fromMarbleSlot &&
            toMarbleSlot &&
            skippedMarble &&
            this.correctDestination(fromRow, fromCol, toRow, toCol);
  }


  protected SlotState skippedSlot(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    int skippedRow = (fromRow + toRow) / 2;
    int skippedCol = (fromCol + toCol) / 2;
    return this.getSlotAt(skippedRow,skippedCol);
  }

  protected boolean correctDestination(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (((fromRow == toRow) && Math.abs(fromCol - toCol) == 2) ||
            ((fromCol == toCol) && Math.abs(fromRow - toRow) == 2)) {
      return true;
    } else {
      throw new IllegalArgumentException("from and to positions need to be 2 cells away");
    }
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      for (int col = 0; col < this.getBoardSize(); col += 1) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (this.hasValidMove(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  protected boolean hasValidMove(int row, int col) {
    return (this.isValidLeftMove(row, col) || this.isValidMoveRight(row, col)
            || this.isValidMoveUp(row, col) || this.isValidMoveDown(row, col));
  }

  protected boolean isValidLeftMove(int row, int col) {
    try {
      this.isValidMove(row, col, row, col - 2);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  protected boolean isValidMoveRight(int row, int col) {
    try {
      this.isValidMove(row, col, row, col + 2);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  protected boolean isValidMoveDown(int row, int col) {
    try {
      this.isValidMove(row, col, row + 2, col);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  protected boolean isValidMoveUp(int row, int col) {
    try {
      this.isValidMove(row, col, row - 2, col);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || row >= this.getBoardSize()) || (col < 0 || col >= this.getBoardSize())) {
      throw new IllegalArgumentException("Invalid cell position (" + row + "," + col +
              ")");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
