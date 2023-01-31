package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To test all the methods and constructors regarding the
 * triangle model.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel b1;
  TriangleSolitaireModel b2;
  TriangleSolitaireModel b3;
  TriangleSolitaireModel b4;
  TriangleSolitaireModel b5;

  /**
   * Initializing triangle boards that are going to be during
   * the testing process.
   */
  @Before
  public void init() {
    b1 = new TriangleSolitaireModel();
    b2 = new TriangleSolitaireModel(5);
    b3 = new TriangleSolitaireModel(0,0);
    b4 = new TriangleSolitaireModel(7,3,3);
    b5 = new TriangleSolitaireModel(7);
  }

  /**
   * Testing if the second constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidTriangle_1() {
    try {
      SolitaireModels b1 = new TriangleSolitaireModel(1);
      fail("No Illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness/Length should be a positive " +
              "odd number that is greater than 3", e.getMessage());
    }


    try {
      SolitaireModels b1 = new TriangleSolitaireModel(-22);
      fail("No Illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness/Length should be a positive " +
              "odd number that is greater than 3", e.getMessage());
    }
  }

  /**
   * Testing if the third constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidTriangle_2() {
    try {
      SolitaireModels b2 = new TriangleSolitaireModel(-1,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,0)", e.getMessage());
    }

    try {
      SolitaireModels b2 = new TriangleSolitaireModel(-1,-1);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,-1)", e.getMessage());
    }

    try {
      SolitaireModels b2 = new TriangleSolitaireModel(100,100);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (100,100)", e.getMessage());
    }
  }

  /**
   * Testing if the fourth constructor throws an illegal argument when needed.
   */
  @Test
  public void testInvalidTriangle_3() {
    try {
      SolitaireModels b3 = new TriangleSolitaireModel(7,-1,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,0)", e.getMessage());
    }

    try {
      SolitaireModels b3 = new TriangleSolitaireModel(7,-1,-1);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,-1)", e.getMessage());
    }

    try {
      SolitaireModels b3 = new TriangleSolitaireModel(7,100,100);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (100,100)", e.getMessage());
    }
  }

  /**
   * Testing if the board is being accurately created.
   */
  @Test
  public void testMakeBoard() {
    // First constructor (Default)
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(b1).toString());

    // Second constructor
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(b2).toString());

    // Third constructor
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(b3).toString());

    // Fourth constructor (Dimension of 7)
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O _\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", new TriangleSolitaireTextView(b4).toString());

    // Third Constructor (Dimension of 7)
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b5.getSlotAt(0,0));
    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", new TriangleSolitaireTextView(b5).toString());
  }

  /**
   * Testing the {@code getSlotAt()} method.
   */
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b4.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b4.getSlotAt(4,4));
  }

  /**
   * Testing if {@code move()} method works and throws an illegal argument when needed.
   */
  @Test
  public void testMove() {
    b1.move(2,0,0,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(2,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(1,0));
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(b1).toString());

    b4.move(3,1,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b4.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b4.getSlotAt(3,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b4.getSlotAt(3,1));
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O _ _ O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", new TriangleSolitaireTextView(b4).toString());
  }

  /**
   * Testing if {@code hasValidMove()} works successfully.
   */
  @Test
  public void hasValidMove() {
    assertTrue(b1.hasValidMove(2,0));
    b1.move(2,0,0,0);
    b1.move(4,0,2,0);
    b1.move(3,2,3,0);
    b1.move(4,2,4,0);
    b1.move(3,0,1,0);
    b1.move(0,0,2,0);
    b1.move(2,2,0,0);
    b1.move(2,0,2,2);
    b1.move(3,3,1,1);
    b1.move(4,4,4,2);
    b1.move(0,0,2,2);
    assertEquals("    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _", new TriangleSolitaireTextView(b1).toString());
    assertFalse(b1.hasValidMove(4,0));
  }

  /**
   * Testing if {@code isValidMove()} works successfully and throws appropriate
   * illegal arguments.
   */
  @Test
  public void isValidMove() {
    assertTrue(b1.isValidMove(2,0,0,0));
    b1.move(2,0,0,0);
    assertTrue(b1.isValidMove(4,0,2,0));

    try {
      b1.isValidMove(-1,0,0,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (-1,0)", e.getMessage());
    }

    try {
      b1.isValidMove(0,0,2,0);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("There is no marble between from and to slots", e.getMessage());
    }

  }

  /**
   * Testing if {@code getBoardSize()} works successfully.
   */
  @Test
  public void getBoardSize() {
    assertEquals(5,b1.getBoardSize());
    assertEquals(5,b2.getBoardSize());
    assertEquals(5,b3.getBoardSize());
    assertEquals(7,b4.getBoardSize());
    assertEquals(7,b5.getBoardSize());
  }

  /**
   * Testing if {@code isGameOver()} works successfully.
   */
  @Test
  public void testIsGameOver() {
    assertFalse(b1.isGameOver());
    b1.move(2,0,0,0);
    b1.move(4,0,2,0);
    b1.move(3,2,3,0);
    b1.move(4,2,4,0);
    b1.move(3,0,1,0);
    b1.move(0,0,2,0);
    b1.move(2,2,0,0);
    b1.move(2,0,2,2);
    b1.move(3,3,1,1);
    b1.move(4,4,4,2);
    b1.move(0,0,2,2);
    assertTrue(b1.isGameOver());
  }

  /**
   * Testing if {@code getScore()} works successfully.
   */
  @Test
  public void testGetScore() {
    assertEquals(14,b1.getScore());
    b1.move(2,0,0,0);
    b1.move(4,0,2,0);
    b1.move(3,2,3,0);
    b1.move(4,2,4,0);
    b1.move(3,0,1,0);
    b1.move(0,0,2,0);
    b1.move(2,2,0,0);
    b1.move(2,0,2,2);
    b1.move(3,3,1,1);
    b1.move(4,4,4,2);
    b1.move(0,0,2,2);
    assertEquals(3,b1.getScore());
    assertEquals(27,b4.getScore());
  }
}