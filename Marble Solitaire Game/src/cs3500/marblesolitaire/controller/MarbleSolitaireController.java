package cs3500.marblesolitaire.controller;

/**
 * This is a controller for the Marble Solitaire Game.
 */
public interface MarbleSolitaireController {

  /**
   * Runs the game until it's over or has been quitted.
   * @throws IllegalArgumentException when the controller can't
   *                                  successfully execute the game.
   */
  void playGame() throws IllegalArgumentException;
}
