package cs3500.set.model.hw03;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Counts;
import cs3500.set.model.hw02.Fillings;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shapes;
import cs3500.set.view.SetGameTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is a test class that tests all the methods and
 * edge cases regarding the GeneralSetGameModel class.
 */
public class GeneralSetGameModelTest {
  SetThreeGameModel game1;
  SetThreeGameModel game2;
  SetThreeGameModel game3;
  GeneralSetGameModel generalGame1;
  SetGameTextView gameview1;
  SetGameTextView generalView1;
  List<Card> deck;
  List<Card> deck2;
  List<Card> deck3;
  List<Card> deck4;
  List<Card> deck5;
  List<Card> deck6;
  List<Card> deck7;
  List<Card> deck8;
  List<Card> deck9;
  List<Card> deck10;
  List<Card> deck11;
  List<Card> deck12;
  List<Card> nullDeck;
  Card card1;
  Card card2;
  Card card3;
  Card card4;
  Card card5;
  Card card6;
  Card card7;
  Card card8;
  Card card9;
  Card card10;
  Card card11;
  Card card12;
  Card card13;
  Card card14;
  Card card15;
  Card card16;
  Card card17;
  Card card18;
  Card card19;
  Card card20;
  Card card21;
  Card card22;
  Card card23;
  Card card24;
  Card card25;
  Card card26;
  Card card27;
  Coord coord1;
  Coord coord2;
  Coord coord3;
  Coord coord4;
  Coord coord5;
  Coord coord6;
  Coord coord7;
  Coord coord8;
  Coord coord9;
  Coord coord10;
  Coord coord11;
  Coord coord12;
  Coord coord13;
  Coord coord14;
  Coord coord15;
  Coord invalidCoord1;
  Coord invalidCoord2;
  // same counts
  ArrayList<Card> sampleClaimedCards;
  // same fillings
  ArrayList<Card> sampleClaimedCards2;
  // same shapes
  ArrayList<Card> sampleClaimedCards3;

  /**
   * Initializing all the examples that are going to be used in the testing process.
   */
  @Before
  public void init() {
    game1 = new SetThreeGameModel();
    game2 = new SetThreeGameModel();
    game3 = new SetThreeGameModel();
    generalGame1 = new GeneralSetGameModel();
    gameview1 = new SetGameTextView(game1);
    generalView1 = new SetGameTextView(generalGame1);

    deck = new ArrayList<Card>();
    deck2 = new ArrayList<Card>();
    deck3 = new ArrayList<Card>();
    deck4 = new ArrayList<Card>();
    deck5 = new ArrayList<Card>();
    deck6 = new ArrayList<Card>();
    deck7 = new ArrayList<Card>();
    deck8 = new ArrayList<Card>();
    deck9 = new ArrayList<Card>();
    deck10 = new ArrayList<Card>();
    deck11 = new ArrayList<Card>();
    deck12 = new ArrayList<Card>();
    nullDeck = null;
    sampleClaimedCards = new ArrayList<Card>();
    sampleClaimedCards2 = new ArrayList<Card>();
    sampleClaimedCards3 = new ArrayList<Card>();

    card1 = new Card(Counts.One, Fillings.Empty, Shapes.Oval);
    card2 = new Card(Counts.One, Fillings.Empty, Shapes.Squiggle);
    card3 = new Card(Counts.One, Fillings.Empty, Shapes.Diamond);

    card4 = new Card(Counts.One, Fillings.Stripped, Shapes.Oval);
    card5 = new Card(Counts.One, Fillings.Stripped, Shapes.Squiggle);
    card6 = new Card(Counts.One, Fillings.Stripped, Shapes.Diamond);

    card7 = new Card(Counts.One, Fillings.Full, Shapes.Oval);
    card8 = new Card(Counts.One, Fillings.Full, Shapes.Squiggle);
    card9 = new Card(Counts.One, Fillings.Full, Shapes.Diamond);

    card10 = new Card(Counts.Two, Fillings.Empty, Shapes.Oval);
    card11 = new Card(Counts.Two, Fillings.Empty, Shapes.Squiggle);
    card12 = new Card(Counts.Two, Fillings.Empty, Shapes.Diamond);

    card13 = new Card(Counts.Two, Fillings.Stripped, Shapes.Oval);
    card14 = new Card(Counts.Two, Fillings.Stripped, Shapes.Squiggle);
    card15 = new Card(Counts.Two, Fillings.Stripped, Shapes.Diamond);

    card16 = new Card(Counts.Two, Fillings.Full, Shapes.Oval);
    card17 = new Card(Counts.Two, Fillings.Full, Shapes.Squiggle);
    card18 = new Card(Counts.Two, Fillings.Full, Shapes.Diamond);

    card19 = new Card(Counts.Three, Fillings.Empty, Shapes.Oval);
    card20 = new Card(Counts.Three, Fillings.Empty, Shapes.Squiggle);
    card21 = new Card(Counts.Three, Fillings.Empty, Shapes.Diamond);

    card22 = new Card(Counts.Three, Fillings.Stripped, Shapes.Oval);
    card23 = new Card(Counts.Three, Fillings.Stripped, Shapes.Squiggle);
    card24 = new Card(Counts.Three, Fillings.Stripped, Shapes.Diamond);

    card25 = new Card(Counts.Three, Fillings.Full, Shapes.Oval);
    card26 = new Card(Counts.Three, Fillings.Full, Shapes.Squiggle);
    card27 = new Card(Counts.Three, Fillings.Full, Shapes.Diamond);

    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);
    deck.add(card10);
    deck.add(card11);
    deck.add(card12);
    deck.add(card13);
    deck.add(card14);
    deck.add(card15);
    deck.add(card16);
    deck.add(card17);
    deck.add(card18);
    deck.add(card19);
    deck.add(card20);
    deck.add(card21);
    deck.add(card22);
    deck.add(card23);
    deck.add(card24);
    deck.add(card25);
    deck.add(card26);
    deck.add(card27);

    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card1);
    deck2.add(card2);
    deck2.add(card3);
    deck2.add(card4);


    deck3.add(card1);
    deck3.add(card2);
    deck3.add(card3);
    deck3.add(card4);
    deck3.add(card5);
    deck3.add(card6);
    deck3.add(card7);
    deck3.add(card8);
    deck3.add(card9);
    deck3.add(card10);
    deck3.add(card11);
    deck3.add(card12);
    deck3.add(card13);
    deck3.add(card14);
    deck3.add(card15);
    deck3.add(card16);
    deck3.add(card17);
    deck3.add(card18);
    deck3.add(card19);
    deck3.add(card20);
    deck3.add(card21);
    deck3.add(card22);
    deck3.add(card23);
    deck3.add(card24);
    deck3.add(card25);
    deck3.add(card26);
    deck3.add(card27);

    deck4.add(card1);
    deck4.add(card2);
    deck4.add(card3);
    deck4.add(card4);
    deck4.add(card5);
    deck4.add(card6);
    deck4.add(card7);
    deck4.add(card8);
    deck4.add(card9);

    deck5.add(card1);
    deck5.add(card2);
    deck5.add(card3);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);

    deck5.add(card10);
    deck5.add(card14);
    deck5.add(card18);

    deck6.add(card1);
    deck6.add(card2);
    deck6.add(card3);
    deck6.add(card1);
    deck6.add(card1);
    deck6.add(card1);
    deck6.add(card1);
    deck6.add(card1);
    deck6.add(card1);

    deck6.add(card12);
    deck6.add(card12);
    deck6.add(card12);

    deck6.add(card10);
    deck6.add(card14);
    deck6.add(card18);


    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);
    deck7.add(card1);


    deck8.add(card1);
    deck8.add(card2);
    deck8.add(card3);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);
    deck8.add(card1);


    // Same fillings
    deck9.add(card1);
    deck9.add(card11);
    deck9.add(card21);
    deck9.add(card2);
    deck9.add(card3);
    deck9.add(card4);
    deck9.add(card5);
    deck9.add(card6);
    deck9.add(card7);
    deck9.add(card8);
    deck9.add(card9);
    deck9.add(card20);

    // Same shapes
    deck10.add(card1);
    deck10.add(card13);
    deck10.add(card25);
    deck10.add(card2);
    deck10.add(card3);
    deck10.add(card4);
    deck10.add(card5);
    deck10.add(card6);
    deck10.add(card7);
    deck10.add(card8);
    deck10.add(card9);
    deck10.add(card20);

    deck11.add(card1);
    deck11.add(card5);
    deck11.add(card9);
    deck11.add(card2);
    deck11.add(card4);
    deck11.add(card7);
    deck11.add(card6);
    deck11.add(card7);
    deck11.add(card12);
    deck11.add(card10);
    deck11.add(card14);
    deck11.add(card18);
    deck11.add(card20);
    deck11.add(card21);
    deck11.add(card23);

    deck12.add(card2);
    deck12.add(card16);
    deck12.add(card3);
    deck12.add(card15);
    deck12.add(card22);
    deck12.add(card12);
    deck12.add(card8);
    deck12.add(card11);
    deck12.add(card21);
    deck12.add(card1);
    deck12.add(card20);
    deck12.add(card24);

    coord1 = new Coord(0,0);
    coord2 = new Coord(0,1);
    coord3 = new Coord(0,2);
    coord4 = new Coord(1,0);
    coord5 = new Coord(1,1);
    coord6 = new Coord(1,2);
    coord7 = new Coord(2,0);
    coord8 = new Coord(2,1);
    coord9 = new Coord(2,2);
    coord10 = new Coord(3,0);
    coord11 = new Coord(3,1);
    coord12 = new Coord(3,2);
    coord13 = new Coord(4,0);
    coord14 = new Coord(4,1);
    coord15 = new Coord(4,2);

    invalidCoord1 = new Coord(100,100);
    invalidCoord2 = new Coord(200,200);

    sampleClaimedCards.add(card1);
    sampleClaimedCards.add(card5);
    sampleClaimedCards.add(card9);

    sampleClaimedCards2.add(card1);
    sampleClaimedCards2.add(card11);
    sampleClaimedCards2.add(card21);

    sampleClaimedCards3.add(card1);
    sampleClaimedCards3.add(card13);
    sampleClaimedCards3.add(card25);
  }

  /**
   * Testing is startGameWithDeck throws appropriate exceptions when the conditions aren't met.
   */
  @Test
  public void invalidInitialization() {
    init();
    // Initializing with board sizes that do not have 3 slots.
    try {
      generalGame1.startGameWithDeck(deck, 1, 1);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least " +
              "three cards on it", e.getMessage());
    }

    try {
      generalGame1.startGameWithDeck(deck, 1, 0);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least " +
              "three cards on it", e.getMessage());
    }

    try {
      generalGame1.startGameWithDeck(deck, 0, 1);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least " +
              "three cards on it", e.getMessage());
    }

    // if deck is null
    try {
      generalGame1.startGameWithDeck(nullDeck, 3, 2);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The deck is null", e.getMessage());
    }

    // if height or width is NOT a positive integer
    try {
      generalGame1.startGameWithDeck(deck, -1, 2);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least three cards on it", e.getMessage());
    }

    try {
      generalGame1.startGameWithDeck(deck, 2, -1);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least three cards on it", e.getMessage());
    }

    try {
      generalGame1.startGameWithDeck(deck, -1, -1);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The board should be able to have at least three cards on it", e.getMessage());
    }

    // if deck doesn't have enough cards to start the game


  }

  /**
   * Initializing with different board sizes.
   */
  @Test
  public void initializationWithDifferentBoardSizes() {
    init();
    // These examples have a valid set on the grid from the start.
    // So the grid size shouldn't change from what was given.
    generalGame1.startGameWithDeck(deck, 4,4);
    assertEquals("1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO", generalView1.toString());

    generalGame1.claimSet(coord1, coord2, coord3);
    assertEquals("2FQ 2FD 3EO 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO", generalView1.toString());

    generalGame1.startGameWithDeck(deck,1,4);
    assertEquals("1EO 1EQ 1ED 1SO", generalView1.toString());
  }

  /**
   * Testing is startGameWithDeck successfully adds a new row to the grid
   * if there is no valid set on the grid at the start of the game.
   */
  @Test
  public void startGameWithDeck() {
    init();
    // Even though the size is 3x3, since there is no valid set on the grid
    // when the game starts, the grid should add a new row and change its size
    // to 4x3
    // This example happens to have a valid set on the grid after adding
    // one row of new cards
    generalGame1.startGameWithDeck(deck2, 3,3);
    assertEquals("1EO 1EO 1EO\n" +
            "1EO 1EO 1EO\n" +
            "1EO 1EO 1EO\n" +
            "1EQ 1ED 1SO", generalView1.toString());

    // This example happens to have a valid set on the grid after adding multiple rows
    // of new cards.
    generalGame1.startGameWithDeck(deck2, 2,2);
    assertEquals("1EO 1EO\n" +
            "1EO 1EO\n" +
            "1EO 1EO\n" +
            "1EO 1EO\n" +
            "1EO 1EQ\n" +
            "1ED 1SO", generalView1.toString());

  }

  /**
   * To test if startGameWithDeck throws an appropriate exception when there are
   * no more cards left in the deck to add a new row to the grid.
   */
  @Test
  public void invalidStartGameWithDeck() {
    // Checking if it throws the correct exception and changes the game-state to over
    try {
      generalGame1.startGameWithDeck(deck7,2,2);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Not enough cards to add a new row nor any valid " +
              "sets available", e.getMessage());
      assertTrue(generalGame1.isGameOver());
    }
  }

  /**
   * Testing if claimSet can claim a set properly and add a new row when
   * there are no valid sets after claiming the set.
   */
  @Test
  public void testClaimSet() {
    // deck5 has one valid set on the grid at the start, and first three
    // remaining cards from the deck, which are :
    // replacing that set, is also a valid set.
    generalGame1.startGameWithDeck(deck5, 3,3);
    assertEquals("1EO 1EQ 1ED\n" +
            "1EO 1EO 1EO\n" +
            "1EO 1EO 1EO",generalView1.toString());
    generalGame1.claimSet(coord1, coord2, coord3);
    assertEquals("2EO 2SQ 2FD\n" +
            "1EO 1EO 1EO\n" +
            "1EO 1EO 1EO", generalView1.toString());


    // deck6 has one valid set on the grid at the start
    // (first row from the following render),
    generalGame1.startGameWithDeck(deck6, 3,3);
    assertEquals("1EO 1EQ 1ED\n" +
            "1EO 1EO 1EO\n" +
            "1EO 1EO 1EO", generalView1.toString());

    generalGame1.claimSet(coord1, coord2, coord3);
    // first groups of three remaining cards from the deck is not a set
    // (first row from the following render)
    // So now it adds a new row to the grid

    // second groups of three remaining cards from the deck is a set
    // (fourth row from the following render)
    // So it stops adding a new row to the grid
    assertEquals("2ED 2ED 2ED\n" +
            "1EO 1EO 1EO\n" +
            "1EO 1EO 1EO\n" +
            "2EO 2SQ 2FD", generalView1.toString());
  }

  /**
   * Testing if it throws an exception when there are no more cards left in the
   * deck to add a new row after claiming a set and changes the game-state to over.
   */
  @Test
  public void invalidClaimSet() {
    // deck 8 only has one valid set
    try {
      generalGame1.startGameWithDeck(deck8, 3,3);
      generalGame1.claimSet(coord1, coord2, coord3);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Not enough cards to add a new row nor any valid sets available " +
                      "after claiming the set",
              e.getMessage());
    }
  }
}