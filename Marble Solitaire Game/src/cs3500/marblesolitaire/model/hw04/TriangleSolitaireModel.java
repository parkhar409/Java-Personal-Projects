
package cs3500.marblesolitaire.model.hw04;


/**
 * Clss that represents the Triangle Solitaire model.
 */
public class TriangleSolitaireModel extends SolitaireModels {

  /**
   * Constructor that creates a game with dimension 5
   * with an empty call at 0,0 by deafault.
   */
  public TriangleSolitaireModel() {
    super(5);
    this.changeToTriangle();
    this.emptySlot(0,0);
  }


  /**
   * Constructor that creates a triangle board with the given dimension.
   * @param dimensions dimension of the game.
   * @throws IllegalArgumentException if the dimension doesn't
   *                                  match the game rule.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super((dimensions / 2) + (dimensions / 2 + 1));
    this.changeToTriangle();
    this.emptySlot(0,0);
    if (dimensions % 2 == 0) {
      this.board.remove((this.getBoardSize() - 1));
    }
  }


  /**
   * Constructor that creates a triangle board with the given empty cell.
   * @param sRow row of the empty cell.
   * @param sCol column of teh empty cell.
   * @throws IllegalArgumentException if the empty cell is being tried to be
   *                                  assigned to an invalid cell.
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5);
    this.changeToTriangle();
    this.emptySlot(sRow,sCol);
  }

  /**
   * Constructor that creates a triangle board with the given dimension and empty cell.
   * @param dimensions the given dimension.
   * @param sRow row of the empty cell.
   * @param sCol column of the empty cell.
   * @throws IllegalArgumentException if the dimension or empty cell is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions);
    this.changeToTriangle();
    this.board.get(0).set(0, SlotState.Marble);
    this.emptySlot(sRow,sCol);
  }

  /**
   * Void method that changes an English board to a Triangle board by
   * removing and re-assigning certain cells.
   */
  private void changeToTriangle() {
    int boardSize = this.getBoardSize();
    int armThickness = (boardSize + 2) / 3;
    for (int row = boardSize - 1; row > armThickness - 1; row -= 1) {
      this.board.remove(row);
    }
    for (int row = this.getBoardSize() - 1; row >= 0; row -= 1) {
      for (int col = boardSize - 1; col >= 0; col -= 1) {
        if (col > boardSize - armThickness) {
          this.board.get(row).remove(col);
        } else if (col < armThickness - 1) {
          this.board.get(row).remove(col);
        }
      }
      if (row < armThickness - 1) {
        for (int col = armThickness - 1; col >= armThickness - (armThickness - 1 - row); col -= 1) {
          this.board.get(row).set(col, SlotState.Invalid);
        }
      }
    }
  }

  @Override
  protected boolean hasValidMove(int row, int col) {
    return super.hasValidMove(row, col) ||
            this.northWestMove(row, col) ||
            this.southEastMove(row, col);
  }

  // 45 degrees
  private boolean northWestMove(int row, int col) {
    int toRight45Row = row - 2;
    int toRight45Col = (col + col) / 2;

    boolean isValid;
    try {
      isValid = this.isValidMove(row, col, toRight45Row, toRight45Col);
    } catch (IllegalArgumentException e) {
      isValid = false;
    }
    return isValid;
  }

  // 225 degrees
  private boolean southEastMove(int row, int col) {
    int toLeft225Row = row + 2;
    int toLeft225Col = (col + col) / 2;

    boolean isValid;
    try {
      isValid = this.isValidMove(row, col, toLeft225Row, toLeft225Col);
    } catch (IllegalArgumentException e) {
      isValid = false;
    }
    return isValid;
  }

  @Override
  protected boolean correctDestination(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (((fromRow == toRow) && Math.abs(fromCol - toCol) == 2) ||
            ((fromCol == toCol) && Math.abs(fromRow - toRow) == 2) ||
            (this.moveUpward(fromRow, fromCol, toRow, toCol))) {
      return true;
    } else {
      throw new IllegalArgumentException("from and to positions need to be 2 cells away");
    }
  }

  private boolean moveUpward(int fromRow, int fromCol, int toRow, int toCol) {
    boolean twoCellsAway = Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2;
    boolean upwardDirection = (toCol - fromCol) / (toRow - fromRow) > 0;
    return twoCellsAway && upwardDirection;
  }
}
