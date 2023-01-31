package cs3500.marblesolitaire.model.hw02;

/**
 * {@code EnglishSolitaireModel} helps initiating the game.
 */
public final class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private SlotState[][] englishBoard;

  /**
   * Initializing the game with armthickness of 3 and empty slot at (3,3).
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;
    this.makeBoard(sRow, sCol, armThickness);
  }

  /**
   * Initializes the game with an arm thickness of 3 and an empty slot.
   * @param sRow    the row that the empty slot is located at.
   * @param sCol    the column that the empty slot is located at.
   * @throws IllegalArgumentException    statement to be thrown when the empty slot is invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    String illegalStatement = "Invalid empty cell position (" + String.valueOf(sRow)
            + "," + String.valueOf(sCol) + ")";

    if (2 > sRow && (sCol < 2 || sCol > 4)) {
      throw new IllegalArgumentException(illegalStatement);
    }
    if (4 < sRow && (sCol < 2 || sCol > 4)) {
      throw new IllegalArgumentException(illegalStatement);
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.armThickness = 3;
    this.makeBoard(sRow, sCol, armThickness);
  }

  /**
   * Initializes the game with the empty slot at the middle of the board.
   * @param armThickness    armthickness of the game.
   * @throws IllegalArgumentException    Statement to be thrown if the armthickness
   *                                     is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    if ((armThickness % 2 == 0) || (armThickness < 0)) {
      throw new IllegalArgumentException("ArmThickness needs to be a positive odd number");
    }

    this.armThickness = armThickness;
    this.sRow = (this.armThickness - 1) + ((this.armThickness - 1) / 2);
    this.sCol = (this.armThickness - 1) + ((this.armThickness - 1) / 2);
    this.makeBoard(sRow, sCol, armThickness);
  }

  /**
   * Initializes the game with a given armThickness with the empty slot in (sRow, sCol).
   * @param armThickness  armthickness of the board.
   * @param sRow  the row that the empty slot is located on.
   * @param sCol  the column that the empty is located on.
   * @throws IllegalArgumentException Statement to be thrown when the given armthickness
   *                                  is not a positive odd number.
   *  @throws IllegalArgumentException Statement to be thrown if the empty cell is located
   *                                   on an invalid cell.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 0) {
      throw new IllegalArgumentException("ArmThickness needs to be a positive odd number");
    }
    else if (((sRow < armThickness - 1) && (sCol < armThickness - 1))
            || ((sCol >= 2 * armThickness - 1) && (sRow < armThickness - 1))
            || ((sCol < armThickness - 1) && (sRow >= 2 * armThickness - 1))
            || ((sCol >= 2 * armThickness - 1) && (sRow >= 2 * armThickness - 1))) {
      throw new IllegalArgumentException("Invalid empty cell");
    }

    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.makeBoard(sRow, sCol, armThickness);
  }
  //(armThickness - 1 > sRow && (sCol < armThickness - 1 || sCol > 2 * armThickness - 2))
  //            || (2 * armThickness - 2 < sRow && (sCol < armThickness - 1
  //            || sCol > 2 * armThickness - 2))

  /**
   * Creates a board that the game is going to be played on.
   * @param sRow  row that the empty cell is located on.
   * @param sCol  column that the empty cell is located on.
   * @param armThickness  armthickness of the board.
   */
  private void makeBoard(int sRow, int sCol, int armThickness) {

    this.englishBoard =
            new MarbleSolitaireModelState.SlotState[3 * armThickness - 2][3 * armThickness - 2];

    for (int row = 0; row < this.englishBoard.length; row++) {
      for (int col = 0; col < this.englishBoard.length; col++) {
        if ((row < armThickness - 1) && (col < armThickness - 1)) {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Invalid; // top left
        }
        else if ((col >= 2 * armThickness - 1) && (row < armThickness - 1)) {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Invalid;  // top right
        }
        else if ((col < armThickness - 1) && (row >= 2 * armThickness - 1)) {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Invalid;  // bottom left
        }
        else if ((col >= 2 * armThickness - 1) && (row >= 2 * armThickness - 1)) {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Invalid; // bottom right
        }
        else if (row == sRow && col == sCol) {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Empty; // Empty
        }
        else {
          this.englishBoard[row][col] = MarbleSolitaireModelState.SlotState.Marble;
        }
      }
    }
  }

  /**
   * Moves a marble to a valid slot.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0).
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0).
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0).
   * @throws IllegalArgumentException statement to be thrown when either the destination is Empty
   *                                  or user tries to make an invalid move.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {

    if ((fromRow >= this.getBoardSize()) || (fromCol >= this.getBoardSize())
            || (toRow >= this.getBoardSize()) || (toCol >= this.getBoardSize())) {
      throw new IllegalArgumentException(
              "Either the starting position of detination is out of bound.");
    }

    if ((fromRow < 0) || (fromCol < 0) || (toRow < 0) || (toCol < 0)) {
      throw new IllegalArgumentException("Invalid Cell can't be moved");
    }
    if (((this.englishBoard[fromRow][fromCol] == SlotState.Marble)
            && (this.englishBoard[toRow][toCol] != SlotState.Empty))) {
      throw new IllegalArgumentException("The destination needs to be Empty");
    }
    if ((Math.abs((fromRow - toRow)) != 2) && (Math.abs((fromCol - toCol)) != 2)) {
      throw new IllegalArgumentException("Marble can only make a "
              + "vertical or horizontal move of 2 ");
    }
    if (this.englishBoard[fromRow][fromCol] != SlotState.Marble) {
      throw new IllegalArgumentException(
              "The starting slot needs to have a Marble to move");
    }
    if (!this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException(
              "Marbles can't move diagonally");
    }
    // when the marble is moving left & right
    else if ((fromRow == toRow)) {
      this.englishBoard[fromRow][fromCol] = SlotState.Empty;
      this.englishBoard[toRow][(fromCol + toCol) / 2] = SlotState.Empty;
      this.englishBoard[toRow][toCol] = SlotState.Marble;
    }
    // when the marble is moving up & down
    else if ((fromCol == toCol)) {
      this.englishBoard[fromRow][fromCol] = SlotState.Empty;
      this.englishBoard[(fromRow + toRow) / 2][fromCol] = SlotState.Empty;
      this.englishBoard[toRow][toCol] = SlotState.Marble;
    }
  }

  /**
   * Checks if any moves can be made on a given slot.
   * @param row row that the slot is located on.
   * @param col column that the slot is located on.
   * @return  a boolean value that tells if any moves can be made or not.
   */
  protected boolean hasValidMove(int row, int col) {
    int size = this.getBoardSize();
    if (this.englishBoard[row][col] != MarbleSolitaireModelState.SlotState.Marble) {
      throw new IllegalArgumentException("There is no Marble in the given slot");
    }
    return row < (size - 1) && this.isValidMove(row, col, row + 2, col)
            || row > 1 && this.isValidMove(row, col, row - 2, col)
            || col < (size - 1) && this.isValidMove(row, col, row, col + 2)
            || col > 1 && this.isValidMove(row, col, row, col - 2);
  }

  /**
   * Checks is the intended move is a valid move or not.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                 (starts at 0).
   * @param toRow the row number of the position to be moved to
   *              (starts at 0).
   * @param toCol the column number of the position to be moved to
   *              (starts at 0).
   * @return  a boolean value that tells if the intended move is valid or not.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int skippedRow = (fromRow + toRow) / 2;
    int skippedCol = (fromCol + toCol) / 2;
    int size = this.getBoardSize();
    return ((fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0))
            && ((fromRow < size && fromCol < size) && (toRow < size && toCol < size))
            && (this.englishBoard[fromRow][fromCol] == MarbleSolitaireModelState.SlotState.Marble)
            && ((Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            || (Math.abs(fromCol - toCol) == 2 && fromRow - toRow == 0))
            && (this.englishBoard[skippedRow][skippedCol]
            == MarbleSolitaireModelState.SlotState.Marble)
            && (this.englishBoard[toRow][toCol] == MarbleSolitaireModelState.SlotState.Empty);
  }

  /**
   * Checks if the game is over due to no more avaliable moves.
   * @return  a boolean value that tells if the game is over or not.
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.englishBoard.length; row++) {
      for (int col = 0; col < this.englishBoard.length; col++) {
        if (this.englishBoard[row][col].equals(MarbleSolitaireModelState.SlotState.Marble)
                && this.hasValidMove(row, col)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Gets the size of the given board.
   * @return  and integer value of the size of the given board.
   */
  @Override
  public int getBoardSize() {
    return 3 * this.armThickness - 2;
  }

  /**
   * Determines if the given slot is Marble, Invalid, or Empty.
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   * @return  the slotstate of the given slot.
   * @throws IllegalArgumentException statement to be thrown when the given coordinates of the slot
   *                                  is beyond the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException(
              "The row or the column are beyond the dimension of the board");
    }
    return this.englishBoard[row][col];
  }

  /**
   * Calculates the score of the game at a current state.
   * @return  integer value of the score.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.englishBoard.length; row++) {
      for (int col = 0; col < this.englishBoard[row].length; col++) {
        if (englishBoard[row][col] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}

