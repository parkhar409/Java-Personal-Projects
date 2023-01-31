package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/** This is a controller for the Marble Solitaire Game which allows the game
 * to be modified based on user's input.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * This is a constructor class for the Marble Solitaire Game.
   * @param model is a board that's being played.
   * @param view  is an output that would be shown to the user
   *              based on one's inputs.
   * @param input is what's being inputted by the user.
   * @throws IllegalArgumentException when any of the parameters are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,Readable input)
          throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("model, view, and input can't be null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * A message that's being rendered at the start of the game.
   * @throws IllegalStateException when there is an error rendering the message.
   */
  private void startMessage() throws IllegalStateException {
    this.renderMessageHelper("Welcome to the game\nPlay:\n");
    this.renderBoardAndScore();
  }

  /**
   * A message that would render the board itself and the score of
   * it at a current state.
   * @throws IllegalStateException when there is an error rendering the message.
   */
  private void renderBoardAndScore() throws IllegalStateException {
    this.renderBoardAndScoreHelper();
    this.renderMessageHelper("\nScore: " + this.model.getScore() + "\n");
  }

  /**
   * A message that would be rendered if any of the inputs are invalid.
   * @throws IllegalStateException when there is an error rendering the message.
   */
  private void renderInvalidInput() throws IllegalStateException {
    this.renderMessageHelper("Please re-enter a valid input that is a positive number "
            + "or press 'q' to quit:\n");
  }

  /**
   * Checks is the user's inputs are positive integers.
   * @param s is the string value of the user's inputs.
   * @return true when positive, false when not.
   */
  private boolean checkPositive(String s) {
    int moveInput;
    try {
      moveInput = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return moveInput > 0;
  }

  /**
   * A message to be rendered when the user quits the game.
   * @throws IllegalStateException when there is an error rendering the message.
   */
  private void quitMessage() throws IllegalStateException {
    this.renderMessageHelper("Game quit!\n");
    this.renderMessageHelper("State of game when quit:\n");
    this.renderBoardAndScore();
  }

  /**
   * A message to be rendered when the game is over.
   * @throws IllegalStateException when there is an error rendering the message.
   */
  private void endMessage() throws IllegalStateException {
    this.renderMessageHelper("Game over!\n");
    this.renderBoardAndScore();
  }


  /**
   * A helper method for {@code renderBoardAndScore()} which would
   * also handle the exception being thrown in.
   */
  private void renderBoardAndScoreHelper() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Error in rendering the board\n");
    }
  }

  /**
   * A helper method to be used to handle any exceptions that's
   * being thrown when rendering a given message.
   * @param message is a String value of a message that's been given.
   */
  private void renderMessageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error in rendering the message\n");
    }
  }

  /**
   * Runs the game until it's over or has been quitted.
   * @throws IllegalStateException when the controller can't
   *                               successfully execute the game.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    boolean quitGame = false;

    this.startMessage();
    ArrayList<Integer> userInputs = new ArrayList<>();

    while (!quitGame && !this.model.isGameOver()) {
      String nextUserInput;
      try {
        nextUserInput = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("There are no more inputs to scan\n");
      }
      if (nextUserInput.equalsIgnoreCase("q")) {
        quitGame = true;
        this.quitMessage();
      } else if (this.checkPositive(nextUserInput)) {
        userInputs.add(Integer.parseInt(nextUserInput) - 1);
        if (userInputs.size() >= 4) {
          try {
            model.move(userInputs.remove(0),userInputs.remove(0),
                    userInputs.remove(0),userInputs.remove(0));
            this.renderBoardAndScore();
          } catch (IllegalArgumentException e) {
            try {
              view.renderMessage("Invalid move. Play again. "
                      + e.getMessage() + "\n");
            } catch (IOException ee) {
              throw new IllegalStateException("Error in rendering the move\n");
            }
          }
        }
      } else {
        this.renderInvalidInput();
      }
    }
    if (this.model.isGameOver()) {
      this.endMessage();
    }
  }
}
