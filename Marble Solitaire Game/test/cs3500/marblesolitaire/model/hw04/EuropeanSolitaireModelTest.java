package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To test initialization and methods regarding the European model.
 */
public class EuropeanSolitaireModelTest {

  EuropeanSolitaireModel b1;
  EuropeanSolitaireModel b2;
  EuropeanSolitaireModel b3;
  EuropeanSolitaireModel b4;
  EuropeanSolitaireModel b5;

  /**
   * Initializing boards to be used in the tests.
   */
  @Before
  public void init() {
    b1 = new EuropeanSolitaireModel();
    b2 = new EuropeanSolitaireModel(3);
    b3 = new EuropeanSolitaireModel(3,2);
    b4 = new EuropeanSolitaireModel(3,2,1);
    b5 = new EuropeanSolitaireModel(5);

  }

  /**
   * Testing if the second constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidEuropean_1() {
    try {
      SolitaireModels b1 = new EuropeanSolitaireModel(1);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness/Length should be a positive " +
              "odd number that is greater than 3", e.getMessage());
    }

    try {
      SolitaireModels b1 = new EuropeanSolitaireModel(2);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness/Length should be a positive " +
              "odd number that is greater than 3", e.getMessage());
    }

    try {
      SolitaireModels b1 = new EuropeanSolitaireModel(-2);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness/Length should be a positive " +
              "odd number that is greater than 3", e.getMessage());
    }
  }

  /**
   * Testing if the third constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidEuropean_2() {
    try {
      SolitaireModels b2 = new EuropeanSolitaireModel(-1,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,0)",e.getMessage());
    }

    try {
      SolitaireModels b2 = new EuropeanSolitaireModel(-1,-1);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,-1)",e.getMessage());
    }

    try {
      SolitaireModels b2 = new EuropeanSolitaireModel(0,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("(0, 0) is an invalid cell",e.getMessage());
    }

    try {
      SolitaireModels b2 = new EuropeanSolitaireModel(100,100);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (100,100)",e.getMessage());
    }
  }

  /**
   * Testing if the fourth constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidEuropean_3() {
    try {
      SolitaireModels b3 = new EuropeanSolitaireModel(3,-1,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,0)",e.getMessage());
    }

    try {
      SolitaireModels b3 = new EuropeanSolitaireModel(3,-1,-1);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,-1)",e.getMessage());
    }

    try {
      SolitaireModels b3 = new EuropeanSolitaireModel(3,0,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("(0, 0) is an invalid cell",e.getMessage());
    }

    try {
      SolitaireModels b3 = new EuropeanSolitaireModel(5,100,100);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (100,100)",e.getMessage());
    }
  }

  /**
   * Testing if the board is being accurately created.
   */
  @Test
  public void testMakeBoard() {
    // First constructor (Deafult)
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b1).toString());

    // Second constructor
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b2).toString());

    // Second constructor
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", new MarbleSolitaireTextView(b3).toString());

    // Third constructor
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new MarbleSolitaireTextView(b4).toString());

    // First constructor (Length of 5)
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O",new MarbleSolitaireTextView(b5).toString());

  }


  /**
   * Testing the {@code getSlotAt()} method.
   */
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, b1.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(0,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, b5.getSlotAt(12,12));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b5.getSlotAt(5,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b5.getSlotAt(6,6));
  }

  /**
   * Testing if {@code move()} method works and throws an illegal argument when needed.
   */
  @Test
  public void testMove() {
    b1.move(1,3,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(2,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(1,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3,3));
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O",new MarbleSolitaireTextView(b1).toString());

    // Now checking if the move method throws an illegal argument when the move is invalid
    try {
      b1.move(0,0,3,3);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("No marble to move",e.getMessage());
    }

    b5 = new EuropeanSolitaireModel(5,3,3);
    b5.move(5,3,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,b5.getSlotAt(5,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,b5.getSlotAt(4,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,b5.getSlotAt(3,3));
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O _ O O O O O O O O O\n" +
            "O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", new MarbleSolitaireTextView(b5).toString());

    // Now checking if the move method throws an illegal argument when the move is invalid
    try {
      b5.move(3,3,4,3);
    } catch (IllegalArgumentException e) {
      assertEquals("from and to positions need to be 2 cells away",e.getMessage());
    }
  }

  /**
   * Testing if {@code hasValidMove()} works successfully.
   */
  @Test
  public void testHasValidMove() {
    assertFalse(b1.hasValidMove(2, 0));
    assertFalse(b1.hasValidMove(3, 2));

    assertTrue(b1.hasValidMove(1, 3));
    assertTrue(b1.hasValidMove(5, 3));
    assertTrue(b1.hasValidMove(3, 5));

    assertFalse(b5.hasValidMove(3, 3));
    assertFalse(b5.hasValidMove(7, 7));
    assertFalse(b5.hasValidMove(5, 6));

    assertTrue(b5.hasValidMove(4, 6));
    assertTrue(b5.hasValidMove(8, 6));
  }

  /**
   * Testing if {@code isValidMove()} works successfully and throws appropriate
   * illegal arguments.
   */
  @Test
  public void testIsValidMove() {
    assertTrue(b1.isValidMove(1,3,3,3));

    try {
      b1.isValidMove(3,2,1,3);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The destination needs to be empty", e.getMessage());
    }

    assertTrue(b5.isValidMove(4,6,6,6));

    try {
      b1.isValidMove(3,0,3,2);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The destination needs to be empty", e.getMessage());
    }
  }


  /**
   * Testing if {@code isGameOver()} works successfully.
   */
  @Test
  public void testIsGameOver() {
    assertEquals(false, b1.isGameOver());
    b1.move(1,3,3,3);
    b1.move(4,3,2,3);
    b1.move(6,3,4,3);
    b1.move(3,1,3,3);
    b1.move(1,2,3,2);
    b1.move(4,2,2,2);
    b1.move(6,2,4,2);
    b1.move(3,3,5,3);
    b1.move(5,4,5,2);
    b1.move(4,2,6,2);
    b1.move(3,4,5,4);
    b1.move(4,6,4,4);
    b1.move(5,4,3,4);
    b1.move(3,5,3,3);
    b1.move(2,3,4,3);
    b1.move(4,0,4,2);
    b1.move(4,2,4,4);
    b1.move(2,0,4,0);
    b1.move(2,1,2,3);
    b1.move(1,4,3,4);
    b1.move(3,4,5,4);
    b1.move(6,4,4,4);
    b1.move(2,6,4,6);
    b1.move(1,5,3,5);
    assertEquals("    O O O\n" +
            "  O _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "  O _ _ _ O\n" +
            "    O _ _", new MarbleSolitaireTextView(b1).toString());
    assertTrue(this.b1.isGameOver());
  }

  /**
   * Testing if {@code getBoardSize()} works successfully.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(7,b1.getBoardSize());
    assertEquals(7,b2.getBoardSize());
    assertEquals(7,b3.getBoardSize());
    assertEquals(7,b4.getBoardSize());
    assertEquals(13,b5.getBoardSize());
  }

  /**
   * Testing if {@code getScore()} works successfully.
   */
  @Test
  public void testGetScore() {
    assertEquals(36,b1.getScore());
    b1.move(1,3,3,3);
    b1.move(4,3,2,3);
    b1.move(6,3,4,3);
    b1.move(3,1,3,3);
    b1.move(1,2,3,2);
    b1.move(4,2,2,2);
    b1.move(6,2,4,2);
    b1.move(3,3,5,3);
    b1.move(5,4,5,2);
    b1.move(4,2,6,2);
    b1.move(3,4,5,4);
    b1.move(4,6,4,4);
    b1.move(5,4,3,4);
    b1.move(3,5,3,3);
    b1.move(2,3,4,3);
    b1.move(4,0,4,2);
    b1.move(4,2,4,4);
    b1.move(2,0,4,0);
    b1.move(2,1,2,3);
    b1.move(1,4,3,4);
    b1.move(3,4,5,4);
    b1.move(6,4,4,4);
    b1.move(2,6,4,6);
    b1.move(1,5,3,5);
    assertEquals(12,b1.getScore());
  }
}