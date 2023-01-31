package cs3500.marblesolitaire.controller;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import cs3500.marblesolitaire.model.hw02.EnglishMockModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Different types of boards to be used in the testing.
 */
public class MarbleSolitaireControllerImplTest {
  EnglishSolitaireModel b1;
  EnglishMockModel mockB1;
  EnglishMockModel mockB2;
  EnglishMockModel mockB3;

  /**
   * Checks if all the methods are being
   * called when the mock model is being played.
   */
  @Test
  public void playGame() {
    StringBuilder mockAp = new StringBuilder();
    mockB1 = new EnglishMockModel(mockAp);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(mockB1, mockAp);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();

    assertEquals("Welcome to the game\n" + "Play:\n"
            + "getBoardSize called\n"
            + "getScore called\n"
            + "\n"
            + "Score: 0\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "move method called on fromRow: 1, "
            + "fromCol: 3, toRow: 3, toCol: 3\n"
            + "getBoardSize called\n"
            + "getScore called\n"
            + "\n"
            + "Score: 0\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "isGameOver called\n"
            + "move method called on fromRow: 4, fromCol: 3, toRow: 2, toCol: 3\n"
            + "getBoardSize called\n"
            + "getScore called\n"
            + "\n"
            + "Score: 0\n"
            + "isGameOver called\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "getBoardSize called\n"
            + "getScore called\n"
            + "\n"
            + "Score: 0\n"
            + "isGameOver called\n",mockAp.toString());
  }

  /**
   * Check if the messages are being renderd.
   */
  @Test
  public void renderMessageHelper() {
    StringBuilder mockAp = new StringBuilder();
    mockB1 = new EnglishMockModel(mockAp);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(mockB1, mockAp);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();

    assertTrue(mockAp.toString().contains("Welcome to the game\nPlay:\n"));
  }

  /**
   * Check if the board and the score are being rendered.
   */
  @Test
  public void renderBoardWithScoreHelper() {
    StringBuilder mockAp = new StringBuilder();
    mockB1 = new EnglishMockModel(mockAp);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(mockB1, mockAp);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();

    String[] result = mockAp.toString().split("\n");
    assertTrue(mockAp.toString().contains("Score: 0"));
  }

  /**
   * Checking if the start message is being rendered.
   */
  @Test
  public void startMessage() {
    StringBuilder mockAp = new StringBuilder();
    mockB1 = new EnglishMockModel(mockAp);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(mockB1, mockAp);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();

    assertTrue(mockAp.toString().contains("Welcome to the game\nPlay:\n"));
  }

  /**
   * Check if the board and the score are being rendered.
   */
  @Test
  public void renderBoardWithScore() {
    StringBuilder mockAp = new StringBuilder();
    mockB1 = new EnglishMockModel(mockAp);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(mockB1, mockAp);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();

    String[] result = mockAp.toString().split("\n");
    assertTrue(mockAp.toString().contains("Score: 0"));
  }

  /**
   * Check if the message is being rendered
   * signalling the end of the game.
   */
  @Test
  public void endGame() {
    StringBuilder ap = new StringBuilder();
    b1 = new EnglishSolitaireModel(3);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.b1, ap);

    Reader inputForB1 = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 2 4 "
            + "4 2 3 4 3 5 3 3 3 7 3 5 3 4 4 6 4 6 5 6 3 5 3 7 3 4 5 6 5 5 "
            + "7 5 5 6 5 4 5 4 6 4 4 3 4 5 4 5 1 5 3 5 3 5 5 3 1 5 1 3 2 3 "
            + "4 2 5 4 5 4 5 6 5 7 5 5 5 3 7 5 7 ");
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.b1, viewB1, inputForB1);
    testControllerForB1.playGame();

    String[] result = ap.toString().split("\n");
    assertEquals("Game over!",result[194]);
  }

  /**
   * Checking if the right message is being rendered
   * when invalid inputs are given.
   */
  @Test
  public void invalidInputMessage() {
    StringBuilder ap = new StringBuilder();
    mockB1 = new EnglishMockModel(ap);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.mockB1, ap);

    Reader inputForB1 = new StringReader("y y y y q"); // Make sure add +1 to all the moves
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();
    assertTrue(ap.toString().contains("Please re-enter a valid input that is a "
            + "positive number or press 'q' to quit:"));
  }

  /**
   * Checking if the right message if being rendered when
   * non positive inputs are given.
   */
  @Test
  public void checkPositiveTest() {
    StringBuilder ap = new StringBuilder();
    mockB1 = new EnglishMockModel(ap);
    MarbleSolitaireView viewB1 = new MarbleSolitaireTextView(this.mockB1, ap);

    Reader inputForB1 = new StringReader("-1 -1 -1 -1 q"); // Make sure add +1 to all the moves
    MarbleSolitaireController testControllerForB1 =
            new MarbleSolitaireControllerImpl(this.mockB1, viewB1, inputForB1);
    testControllerForB1.playGame();
    assertTrue(ap.toString().contains("Please re-enter a valid input that is a "
            + "positive number or press 'q' to quit:"));
  }
}