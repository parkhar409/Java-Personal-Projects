package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.SolitaireModels;

/**
 * {@code EnglishSolitaireModel} helps initiating the game.
 */
public final class EnglishSolitaireModel extends SolitaireModels {
  /**
   * Initializing the game with armthickness of 3 and empty slot at (3,3).
   */
  public EnglishSolitaireModel() {
    super(3);
  }

  /**
   * Initializes the game with an arm thickness of 3 and an empty slot.
   * @param sRow    the row that the empty slot is located at.
   * @param sCol    the column that the empty slot is located at.
   * @throws IllegalArgumentException    statement to be thrown when the empty slot is invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3);
    super.emptySlot(sRow, sCol);
  }

  /**
   * Initializes the game with the empty slot at the middle of the board.
   * @param armThickness    armthickness of the game.
   * @throws IllegalArgumentException    Statement to be thrown if the armthickness
   *                                     is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);
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
    super(armThickness);
    super.emptySlot(sRow, sCol);
  }
}

