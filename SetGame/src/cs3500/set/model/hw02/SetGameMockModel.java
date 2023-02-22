package cs3500.set.model.hw02;

import java.util.List;

/**
 * This is a mock model that is going to be used in tests to
 * check if correct methods are being called at the right time.
 */
public class SetGameMockModel implements SetGameModel {
  private final StringBuilder renderResult;

  /**
   * Constructor for the mockmodel with the given result.
   * @param result whatever would be rendered after methods
   *               have been called on the mock model.
   */
  public SetGameMockModel(StringBuilder result) {
    this.renderResult = result;
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    this.renderResult.append("claimSet called\n");
  }

  @Override
  public void startGameWithDeck(List deck, int height, int width)
          throws IllegalArgumentException {
    this.renderResult.append("Board rendered with " + height + " " + width + "\n");
  }

  @Override
  public int getWidth() throws IllegalStateException {
    this.renderResult.append("getWidth called \n");
    return 0;
  }

  @Override
  public int getHeight() throws IllegalStateException {
    this.renderResult.append("getHeight called \n");
    return 0;
  }

  @Override
  public int getScore() throws IllegalStateException {
    this.renderResult.append("getScore called \n");
    return 0;
  }

  @Override
  public boolean anySetsPresent() {
    this.renderResult.append("anySetsPresent called \n");
    return false;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3)
          throws IllegalArgumentException {
    this.renderResult.append("isValidset called \n");
    return false;
  }

  @Override
  public Object getCardAtCoord(int row, int col) {
    this.renderResult.append("getCardatCoord with integers called \n");
    return null;
  }

  @Override
  public Object getCardAtCoord(Coord coord) {
    this.renderResult.append("getCardatCoord with coords called \n");
    return null;
  }

  @Override
  public boolean isGameOver() {
    this.renderResult.append("isGameOver called \n");
    return false;
  }

  @Override
  public List getCompleteDeck() {
    this.renderResult.append("get CompleteDekc called \n");
    return null;
  }
}
