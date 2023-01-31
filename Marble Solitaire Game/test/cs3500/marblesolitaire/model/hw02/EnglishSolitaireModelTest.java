package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.SolitaireModels;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Testing the constructors for {@code EnglishSolitaireModel} and each method from the interface.
 */
public class EnglishSolitaireModelTest {

  SolitaireModels b1;
  SolitaireModels b2;
  SolitaireModels b3;

  /**
   * Testing if the board can be initialized with a given constructor.
   */
  @Before
  public void init() {
    this.b1 = new EnglishSolitaireModel(3, 3);
  }

  /**
   * Testing if the second constructor successfully throws an IllegalArgument when needed.
   */
  @Test
  public void testInvalidEnglishSolitaireModel_1() {
    try {
      this.b1 = new EnglishSolitaireModel(1,5);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Invalid empty cell position (" + String.valueOf(1)
              + "," + String.valueOf(5) + ")", e.getMessage());
    }

  }

  /**
   * Testing if the third constructor successfully throws an IllegalArgument when needed.
   */
  @Test
  public void testInvalidEnglishSolitaireModel_2() {
    try {
      this.b1 = new EnglishSolitaireModel(4);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("ArmThickness needs to be a positive odd number", e.getMessage());
    }

    try {
      this.b1 = new EnglishSolitaireModel(-1);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Length/ArmThickness should be a positive number", e.getMessage());
    }
  }

  /**
   * Testing if the fourth constructor successfully throws an IllegalArgument when needed.
   */
  @Test
  public void testInvalidEnglishSolitaireModel_3() {
    try {
      this.b1 = new EnglishSolitaireModel(3,0,0);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Invalid empty cell position (0,0)",e.getMessage());
    }
  }

  /**
   * Testing if the board can be successfully created.
   */
  @org.junit.Test
  public void makeBoardTest() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertEquals(this.b1.getSlotAt(0,0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.b1.getSlotAt(0,5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.b1.getSlotAt(5,0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.b1.getSlotAt(6,6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.b1.getSlotAt(0,2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.b1.getSlotAt(2,0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.b1.getSlotAt(2,3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.b1.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.b1.getSlotAt(2,5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.b1.getSlotAt(5,2), MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * Testing if the {@code move()} can successfully update the SlotState of the Marbles that's being
   * moved.
   */
  @org.junit.Test
  public void move() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertEquals(this.b1.getSlotAt(1,3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.b1.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);

    this.b1.move(1,3,3,3);
    assertEquals(this.b1.getSlotAt(1,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.b1.getSlotAt(2,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.b1.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Marble);

    this.b1.move(2,1,2,3);
    assertEquals(this.b1.getSlotAt(2,1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.b1.getSlotAt(2,2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.b1.getSlotAt(2,3), MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * Testing if {@code move()} can successfully throw an IllegalArgument
   * when an invalid move is trying to be made.
   */
  @Test
  public void testInvalidMove() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    try {
      this.b1.move(2,0,2,1);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Invalid move", e.getMessage());
    }

    this.b1.move(1,3,3,3);

    this.b1.move(2,1,2,3);

    try {
      this.b1.move(2,0,2,1);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Invalid move", e.getMessage());
    }

    this.b1 = new EnglishSolitaireModel(3,0,2);
    try {
      this.b1.move(0,2,0,4);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Invalid move", e.getMessage());
    }
  }

  /*
  @Test
  public void hasValidMoveIllegal() {
    SolitaireModels b1 = new EnglishSolitaireModel(3,3,3);
    try {
      this.b1.hasValidMove(0,0);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("There is no Marble in the given slot", e.getMessage());
    }
    try {
      this.b1.hasValidMove(3,3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("There is no Marble in the given slot", e.getMessage());
    }
  }*/

  /*
  @Test
  public void hasValidMoveTest() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b1.move(1,3,3,3);
    this.b1.move(4,3,2,3);
    this.b1.move(6,3,4,3);
    this.b1.move(3,1,3,3);
    this.b1.move(1,2,3,2);
    this.b1.move(4,2,2,2);
    this.b1.move(6,2,4,2);
    this.b1.move(3,3,5,3);
    this.b1.move(5,4,5,2);
    this.b1.move(4,2,6,2);
    this.b1.move(3,4,5,4);
    this.b1.move(4,6,4,4);
    this.b1.move(5,4,3,4);
    this.b1.move(3,5,3,3);
    assertTrue(this.b1.hasValidMove(2,3));

    this.b2 = new EnglishSolitaireModel(3,3,3);
    assertFalse(this.b2.hasValidMove(3,0));
  }*/

  /*
  @Test
  public void isValidMoveTest() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertTrue(this.b1.isValidMove(1,3,3,3));
    assertFalse(this.b1.isValidMove(0,2,1,2));
    assertFalse(this.b1.isValidMove(0,0,1,2));
    assertFalse(this.b1.isValidMove(0,0,1,0));
  }*/

  /**
   * Testing if {@code isGameOver()} can successfully tell if the game is NOT over.
   */
  @org.junit.Test
  public void isGameOverTest() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertFalse(this.b1.isGameOver());
    this.b1.move(1,3,3,3);
    assertFalse(this.b1.isGameOver());
  }

  /**
   * Testing if {@code isGameOver()} can successfully tell if the game IS over.
   */
  @org.junit.Test
  public void isGameOverTest2() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b1.move(1,3,3,3);
    this.b1.move(4,3,2,3);
    this.b1.move(6,3,4,3);
    this.b1.move(3,1,3,3);
    this.b1.move(1,2,3,2);
    this.b1.move(4,2,2,2);
    this.b1.move(6,2,4,2);
    this.b1.move(3,3,5,3);
    this.b1.move(5,4,5,2);
    this.b1.move(4,2,6,2);
    this.b1.move(3,4,5,4);
    this.b1.move(4,6,4,4);
    this.b1.move(5,4,3,4);
    this.b1.move(3,5,3,3);
    this.b1.move(2,3,4,3);
    this.b1.move(4,0,4,2);
    this.b1.move(4,2,4,4);
    this.b1.move(2,0,4,0);
    this.b1.move(2,1,2,3);
    this.b1.move(1,4,3,4);
    this.b1.move(3,4,5,4);
    this.b1.move(6,4,4,4);
    this.b1.move(2,6,4,6);
    assertTrue(this.b1.isGameOver());
  }

  /**
   * Testing if an accurate boardsize can be calculated.
   */
  @org.junit.Test
  public void getBoardSize() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertEquals(this.b1.getBoardSize(), 7);

    this.b2 = new EnglishSolitaireModel(5);
    assertEquals(this.b2.getBoardSize(), 13);
  }

  /**
   * Testing if {@code getSlotAt()} can successfully throw an
   * IllegalArgument when an invalid cell is being tried to be analyzed.
   */
  @org.junit.Test
  public void getSlotAtIllegal() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b2 = new EnglishSolitaireModel(5);
    try {
      this.b1.getSlotAt(7,7);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("The row or the column are beyond the dimension of the board", e.getMessage());
    }
    try {
      this.b1.getSlotAt(100,100);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("The row or the column are beyond the dimension of the board", e.getMessage());
    }
  }

  /**
   * Testing if {@code getSlotAt()} can determine the SlotSate of
   * any given slot even after the board being updated with the {@code move()}.
   */
  @org.junit.Test
  public void getSlotAtTest() {
    EnglishSolitaireModel b5 = new EnglishSolitaireModel(3,3,3);
    assertEquals(b5.getSlotAt(6,5), MarbleSolitaireModelState.SlotState.Invalid);

    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b1.move(1,3,3,3);
    this.b1.move(4,3,2,3);
    this.b1.move(6,3,4,3);
    this.b1.move(3,1,3,3);
    this.b1.move(1,2,3,2);
    this.b1.move(4,2,2,2);
    this.b1.move(6,2,4,2);
    this.b1.move(3,3,5,3);
    this.b1.move(5,4,5,2);
    this.b1.move(4,2,6,2);
    this.b1.move(3,4,5,4);
    this.b1.move(4,6,4,4);
    this.b1.move(5,4,3,4);
    this.b1.move(3,5,3,3);
    this.b1.move(2,3,4,3);
    this.b1.move(4,0,4,2);
    this.b1.move(4,2,4,4);
    this.b1.move(2,0,4,0);
    this.b1.move(2,1,2,3);
    this.b1.move(1,4,3,4);
    this.b1.move(3,4,5,4);
    this.b1.move(6,4,4,4);
    this.b1.move(2,6,4,6);

    assertEquals(b1.getSlotAt(4,6), MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * Testing if {@code getScoreTest()} can accurately calculate the score of the board
   * even after being updated with the {@code move()}.
   */
  @org.junit.Test
  public void getScoreTest() {
    this.b1 = new EnglishSolitaireModel(3,3,3);
    assertEquals(32,b1.getScore());
    this.b1.move(1,3,3,3);
    assertEquals(31,b1.getScore());
    this.b1.move(4,3,2,3);
    assertEquals(30,b1.getScore());

    this.b1 = new EnglishSolitaireModel(3,3,3);
    this.b1.move(1,3,3,3);
    this.b1.move(4,3,2,3);
    this.b1.move(6,3,4,3);
    this.b1.move(3,1,3,3);
    this.b1.move(1,2,3,2);
    this.b1.move(4,2,2,2);
    this.b1.move(6,2,4,2);
    this.b1.move(3,3,5,3);
    this.b1.move(5,4,5,2);
    this.b1.move(4,2,6,2);
    this.b1.move(3,4,5,4);
    this.b1.move(4,6,4,4);
    this.b1.move(5,4,3,4);
    this.b1.move(3,5,3,3);
    this.b1.move(2,3,4,3);
    this.b1.move(4,0,4,2);
    this.b1.move(4,2,4,4);
    this.b1.move(2,0,4,0);
    this.b1.move(2,1,2,3);
    this.b1.move(1,4,3,4);
    this.b1.move(3,4,5,4);
    this.b1.move(6,4,4,4);
    this.b1.move(2,6,4,6);
    assertEquals(9,b1.getScore());
  }

  /**
   * Testing if {@code viewBoard()} can accurately visualize the initial version of the board.
   */
  @org.junit.Test
  public void viewBoard() {
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
  @org.junit.Test
  public void viewBoardAfterMove() {
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
  }

  /**
   * Testing if {@code viewBoard()} can accurately visualize the board
   * has been initialized with an invalid cell that's not in the middle.
   */
  @org.junit.Test
  public void viewBoardAfterMove2() {
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
}