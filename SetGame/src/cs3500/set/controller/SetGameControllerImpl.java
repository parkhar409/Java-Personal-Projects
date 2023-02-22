package cs3500.set.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * This is a controller for the game which allows the game
 * to be modified based on user's input.
 */
public class SetGameControllerImpl implements SetGameController {
  private final SetGameModel model;
  private final SetGameView view;
  private final Readable input;

  /**
   * Constructor for the controller class to be used when one tries to play the game.
   * @param model board/grid that the game is going to be played on.
   * @param view is an output of the game that would be transmitted
   *             to the controller so that the user can see live updates of the game.
   * @param input sets of inputs that the user would input to play the game.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public SetGameControllerImpl(SetGameModel model, SetGameView view, Readable input)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The given game is null");
    } else if (view == null) {
      throw new IllegalArgumentException("The given view is null");
    } else if (input == null) {
      throw new IllegalArgumentException("The given input is null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  // user inputs height and width
  // game gets generated with the given dimensions and getCompleteDeck
  // current the current game state
  // user inputs six values
  // row of the first card (starts from1)
  // col of the first card (starts from1)
  // row of the second card (starts from1)
  // col of the second card (starts from1)
  // row of the third card (starts from1)
  // col of the third card (starts from1)
  // try claiming the set
  // if the game is over, render quitMessage

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    boolean gameQuit = false;

    ArrayList<Integer> userInputs = new ArrayList<>();

    this.startMessage();

    while (!gameQuit) {
      String nextInput;
      try {
        nextInput = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("There are no more inputs to scan\n");
      }
      // quitting the game
      if (nextInput.equalsIgnoreCase("q")) {
        gameQuit = true;
        this.quitMessage();
        break;
      }
      if (this.positiveInput(nextInput)) {
        userInputs.add(Integer.parseInt(nextInput));
        if (userInputs.size() >= 2) {
          try {
            this.model.startGameWithDeck(this.model.getCompleteDeck(),
                    userInputs.remove(0), userInputs.remove(0));
            this.renderGridAndScore();
            // Now try playing the game with the inputs
            while (!gameQuit && !this.model.isGameOver()) {
              //String nextInput;
              try {
                nextInput = scan.next();
              } catch (NoSuchElementException e) {
                throw new IllegalStateException("There are no more inputs to scan\n");
              }
              // quitting the game
              if (nextInput.equalsIgnoreCase("q")) {
                gameQuit = true;
                this.quitMessage();
              }
              // Then start using the user inputs for claimSet
              else if (this.positiveInput(nextInput)) {
                userInputs.add(Integer.parseInt(nextInput) - 1);
                if (userInputs.size() >= 6) {
                  try {
                    Coord coord1 = new Coord(userInputs.remove(0), userInputs.remove(0));
                    Coord coord2 = new Coord(userInputs.remove(0), userInputs.remove(0));
                    Coord coord3 = new Coord(userInputs.remove(0), userInputs.remove(0));
                    model.claimSet(coord1, coord2, coord3);
                    this.renderGridAndScore();
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage("Invalid claim. Try again. "
                              + e.getMessage() + "\n");
                    } catch (IOException ee) {
                      throw new IllegalStateException("Can't render the claimSet action\n");
                    }
                  }
                }
              } else {
                this.renderInvalidInput();
              }
            }
            if (this.model.isGameOver()) {
              this.endMessage();
              gameQuit = true;
            }
          } catch (IllegalArgumentException e) {
            try {
              view.renderMessage("Invalid height/width. Try again. "
                      + e.getMessage() + ".\n");
            } catch (IOException ee) {
              throw new IllegalStateException("Can't render the board\n");
            }
          }
        }
      } else {
        this.renderInvalidHeightWidth();
      }
    }

  }

  // Checking if the input is a positive int
  private boolean positiveInput(String s) {
    int claimInput;
    try {
      claimInput = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return claimInput > 0;
  }

  private void renderMessageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Can't render the message\n");
    }
  }

  private void startMessage() throws IllegalStateException {
    this.renderMessageHelper("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n");
  }

  private void renderGridAndScoreHelper() {
    try {
      this.view.renderGrid();
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't render the grid");
    }
  }


  private void renderGridAndScore() {
    this.renderGridAndScoreHelper();
    this.renderMessageHelper("\nScore: " + this.model.getScore() + "\n");
  }

  private void renderInvalidInput() throws IllegalArgumentException {
    this.renderMessageHelper("Please re-enter a valid input that is a positive number "
            + "or press 'q' to quit:\n");
  }

  private void renderInvalidHeightWidth() throws IllegalArgumentException {
    this.renderMessageHelper("Invalid height/width. Try again. It should be an integer. \n");
  }

  private void endMessage() throws IllegalArgumentException {
    this.renderMessageHelper("Game over!\n");
    this.renderMessageHelper("Score: " + this.model.getScore() + "\n");
  }

  //
  private void quitMessage() {
    // If the game has started
    try {
      this.model.getHeight();
    } catch (IllegalStateException e) {
      this.renderMessageHelper("Game quit!\n");
      this.renderMessageHelper("Score: 0\n");
      return;
    }
    this.renderMessageHelper("Game quit!\n");
    this.renderMessageHelper("State of game when quit:\n");
    this.renderGridAndScore();
  }

}
