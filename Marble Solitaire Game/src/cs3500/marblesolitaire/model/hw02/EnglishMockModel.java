package cs3500.marblesolitaire.model.hw02;

/**
 * This is a mockmodel to be used if the methods are being called successfully.
 */
public class EnglishMockModel implements MarbleSolitaireModel {
  private final StringBuilder renderedResult;

  /**
   * Creates a mockmodel with the given output/result.
   * @param result is the output of a method that's being called.
   */
  public EnglishMockModel(StringBuilder result) {
    this.renderedResult = result;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.renderedResult.append("move method called on fromRow: " + fromRow + ", fromCol: " + fromCol
            + ", toRow: " + toRow + ", toCol: " + toCol + "\n");
  }

  @Override
  public boolean isGameOver() {
    this.renderedResult.append("isGameOver called\n");
    return false;
  }

  @Override
  public int getBoardSize() {
    this.renderedResult.append("getBoardSize called\n");
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    this.renderedResult.append("getSlotAt called on row: " + row + ", col: " + col + "\n");
    return null;
  }

  @Override
  public int getScore() {
    this.renderedResult.append("getScore called\n");
    return 0;
  }
}
