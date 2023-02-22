package cs3500.set.controller;

/**
 * A controller class for the game which takes cares of the user's playing experience.
 */
public interface SetGameController {
  /**
   * Runs the game until it's either over or one decides to quit the game.
   * @throws IllegalStateException when the controller can't render
   *                               the board with scores nor appropriate messages.
   */
  public void playGame() throws IllegalStateException;
}
