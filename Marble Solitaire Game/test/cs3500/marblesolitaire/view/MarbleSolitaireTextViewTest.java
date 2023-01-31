package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * EnglishSolitaireModel boards to be used in testing.
 */
public class MarbleSolitaireTextViewTest {
  EnglishSolitaireModel b1;
  EnglishSolitaireModel b2;
  EnglishSolitaireModel b3;

  /**
   * Testing if the first constructor successfully throws an
   * IllegalArgumentException when any of the parameters is null.
   */

  @Test
  public void testFirstConstructor() {
    try {
      EnglishSolitaireModel b1 = null;
      cs3500.marblesolitaire.view.MarbleSolitaireView viewB1 = new cs3500.marblesolitaire.view.MarbleSolitaireTextView(b1);
      fail("The board can't be null");
    } catch (IllegalArgumentException e) {
      assertEquals("The board can't be null", e.getMessage());
    }
  }

  /**
   * Testing if the second constructor successfully throws
   * an IllegalArgumentException when any of the parameters is null.
   */
  @Test
  public void testSecondConstructor() {
    b1 = new EnglishSolitaireModel(3,3,3);
    b2 = null;
    Appendable ap = new StringBuilder();
    Appendable ap2 = null;

    try {
      MarbleSolitaireView viewB2 = new MarbleSolitaireTextView(b2, ap);
      fail("The board or destination is null");
    } catch (IllegalArgumentException e) {
      assertEquals("The board or destination is null", e.getMessage());
    }

    try {
      MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(b1, ap2);
      fail("The board or destination is null");
    } catch (IllegalArgumentException e) {
      assertEquals("The board or destination is null", e.getMessage());
    }
  }

  /**
   * Testing if {@code viewBoard()} can accurately visualize the initial version of the board.
   */
  @Test
  public void testToString() {
    EnglishSolitaireModel b3 = new EnglishSolitaireModel(3,3,3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b3).toString());
    EnglishSolitaireModel b4 = new EnglishSolitaireModel(5);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", new MarbleSolitaireTextView(b4).toString());
  }

  /**
   * Testing if {@code viewBoard()} can accurately visualize the board
   * even after multiple moves have been made.
   */
  @Test
  public void testToStringAfterMove() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b1.move(1,3,3,3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b1).toString());
    this.b1.move(4,3,2,3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O _ O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b1).toString());
    this.b1.move(6,3,4,3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O _ O", new MarbleSolitaireTextView(b1).toString());
    this.b1.move(3,1,3,3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O _ O", new MarbleSolitaireTextView(b1).toString());

    this.b1 = new EnglishSolitaireModel(3,2,3);
    this.b1.move(0,3,2,3);
    assertEquals("    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b1).toString());
  }


  /**
   * Checking if a board can be rendered using the controller.
   */
  @Test
  public void renderBoard() {
    Appendable ap = new StringBuilder();
    b1 = new EnglishSolitaireModel(3,3,3);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(b1, ap);
    try {
      viewB1.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] result = ap.toString().split("\n");
    assertEquals("    O O O", result[0]);
    assertEquals("    O O O", result[1]);
    assertEquals("O O O O O O O", result[2]);
    assertEquals("O O O _ O O O", result[3]);
    assertEquals("O O O O O O O", result[4]);
    assertEquals("    O O O", result[5]);
    assertEquals("    O O O", result[6]);
  }

  /**
   * Checking if the controller can render the user
   * inputs and render messages accordingly for a board size of 3.
   */
  @Test
  public void renderMessageTest() {
    Appendable ap = new StringBuilder();
    b1 = new EnglishSolitaireModel(3,3,3);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.b1, ap);
    Reader inputForB1 = new StringReader("2 4 4 4 q"); // Make sure add +1 to all the moves
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.b1, viewB1, inputForB1);
    testControllerForB1.playGame();
    assertEquals("Welcome to the game\n" + "Play:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n",ap.toString());

    String[] result = ap.toString().split("\n");

    assertEquals("Welcome to the game", result[0]);
    assertEquals("Play:", result[1]);
    assertEquals("    O O O", result[2]);
  }

  /**
   * Checking if the controller can render the user
   * inputs and render messages accordingly for a board size of 5.
   */
  @Test
  public void renderMessageTest2() {
    Appendable ap = new StringBuilder();
    b2 = new EnglishSolitaireModel(5);
    MarbleSolitaireView viewB2 = new MarbleSolitaireTextView(this.b2, ap);

    Reader inputForB2 = new StringReader("5 7 7 7 q");
    MarbleSolitaireController testControllerForB2 =
            new MarbleSolitaireControllerImpl(b2, viewB2, inputForB2);
    testControllerForB2.playGame();
    assertEquals("Welcome to the game\n"
            + "Play:\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 104\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103\n", ap.toString());

    String[] result = ap.toString().split("\n");

    assertEquals("Welcome to the game", result[0]);
    assertEquals("Play:", result[1]);
    assertEquals("O O O O O O O O O O O O O", result[6]);
  }

  /**
   * Checking if the error messages are being rendered.
   */
  @Test
  public void renderErrorMessageTest() {
    Appendable ap = new StringBuilder();
    b1 = new EnglishSolitaireModel(3,3,3);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.b1, ap);

    Reader inputForB1 = new StringReader("1 1 1 2 q"); // Make sure add +1 to all the moves
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.b1, viewB1, inputForB1);
    testControllerForB1.playGame();
    assertTrue(ap.toString().contains("Invalid move. Play again. "
            + "Marble can only make a vertical or horizontal move of 2 "));
  }

  /**
   * Checking if the error messages are being rendered.
   */
  @Test
  public void renderErrorMessageTest2() {
    Appendable ap = new StringBuilder();
    b1 = new EnglishSolitaireModel(3,3,3);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.b1, ap);

    Reader inputForB1 = new StringReader("1 1 1"); // Make sure add +1 to all the moves
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.b1, viewB1, inputForB1);
    try {
      testControllerForB1.playGame();
      fail("The code didn't throw an error");
    } catch (NoSuchElementException e) {
      assertEquals("There are no more inputs to scan",e.getMessage());
    }
  }
}