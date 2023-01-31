package cs3500.marblesolitaire.model.hw04;

/**
 * Class to represent a EuropeanSolitaireModel game.
 */
public class EuropeanSolitaireModel extends SolitaireModels {

  /**
   * To initiate a default version of a European model.
   */
  public EuropeanSolitaireModel() {
    super(3);
    this.changeToEuropean();
  }

  /**
   * To initiate a European model with the given sidelength.
   * @param sideLength the sidelength of the board.
   * @throws IllegalArgumentException when the sidelength isn't positive
   *                                  odd number that is larger than 3.
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    super(sideLength);
    this.changeToEuropean();
  }

  /**
   * To initiate a European model with the given empty cells on the deafult version.
   * @param sRow row of the empty cell.
   * @param sCol column of the empty cell.
   * @throws IllegalArgumentException if the empty cell is invalid.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3);
    this.changeToEuropean();
    this.emptySlot(sRow, sCol);
  }

  /**
   * To initiate a European model with customized sidelength and empty cell.
   * @param sideLength side length of the board.
   * @param sRow row of the empty cell.
   * @param sCol column of the empty cell.
   * @throws IllegalArgumentException when the sidelength isn't
   *         a positive odd number that is larger than 3.
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol)
          throws IllegalArgumentException {
    super(sideLength);
    this.changeToEuropean();
    this.emptySlot(sRow, sCol);
  }

  private void changeToEuropean() {
    int armThickness = (this.getBoardSize() + 2) / 3;
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      if (row < armThickness - 1) {
        for (int col = armThickness - 1; col >= armThickness - 1 - row; col -= 1) {
          this.board.get(row).set(col, SlotState.Marble);
        }
        for (int col = this.getBoardSize() - armThickness + 1; col < this.getBoardSize()
                - armThickness + 1 + row; col += 1) {
          this.board.get(row).set(col, SlotState.Marble);
        }
      }
      if (row > this.getBoardSize() - armThickness) {
        for (int col = (armThickness - 1) - ((this.getBoardSize() - 1) - row);
             col < armThickness - 1; col += 1) {
          this.board.get(row).set(col, SlotState.Marble);
        }
        for (int col = this.getBoardSize() - armThickness + 1;
             col < (this.getBoardSize() - armThickness) + ((this.getBoardSize()) - row); col += 1) {
          this.board.get(row).set(col, SlotState.Marble);
        }
      }
    }
  }
}
