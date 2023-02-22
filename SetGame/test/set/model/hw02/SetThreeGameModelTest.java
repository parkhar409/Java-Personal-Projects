package set.model.hw02;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


/**
 * Class that contains all the testing methods for all the public methods.
 */
public class SetThreeGameModelTest {
  SetThreeGameModel game1;
  SetThreeGameModel game2;
  SetThreeGameModel game3;
  SetThreeGameModel game4;
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
  Coord invalidCoord1;
  Coord invalidCoord2;
  // same counts
  ArrayList<Card> sampleClaimedCards;
  // same fillings
  ArrayList<Card> sampleClaimedCards2;
  // same shapes
  ArrayList<Card> sampleClaimedCards3;
  // different attributes
  ArrayList<Card> sampleClaimedCards4;
  ArrayList<Card> sampleClaimedCards5;

  /**
   * Initializing the examples that are going to be used during testing.
   */
  @Before
  public void init() {
    game1 = new SetThreeGameModel();
    game2 = new SetThreeGameModel();
    game3 = new SetThreeGameModel();
    game4 = new SetThreeGameModel();

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
    deck12 = new ArrayList<>();
    nullDeck = null;
    sampleClaimedCards = new ArrayList<Card>();
    sampleClaimedCards2 = new ArrayList<Card>();
    sampleClaimedCards3 = new ArrayList<Card>();
    sampleClaimedCards4 = new ArrayList<Card>();
    sampleClaimedCards5 = new ArrayList<Card>();

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
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);
    deck5.add(card1);

    deck6.add(card1);
    deck6.add(card10);
    deck6.add(card16);
    deck6.add(card4);
    deck6.add(card5);
    deck6.add(card7);
    deck6.add(card8);
    deck6.add(card9);
    deck6.add(card12);
    deck6.add(card20);

    deck7.add(card1);
    deck7.add(card5);
    deck7.add(card9);
    deck7.add(card10);
    deck7.add(card14);
    deck7.add(card18);
    deck7.add(card17);
    deck7.add(card13);
    deck7.add(card12);
    deck7.add(card2);
    deck7.add(card4);
    deck7.add(card20);
    deck7.add(card21);
    deck7.add(card22);
    deck7.add(card23);
    deck7.add(card24);

    deck8.add(card1);
    deck8.add(card10);
    deck8.add(card19);
    deck8.add(card5);
    deck8.add(card6);
    deck8.add(card7);
    deck8.add(card8);
    deck8.add(card9);
    deck8.add(card20);
    deck8.add(card21);

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

    // different attributes
    deck11.add(card2);
    deck11.add(card13);
    deck11.add(card27);
    deck11.add(card1);
    deck11.add(card3);
    deck11.add(card4);
    deck11.add(card5);
    deck11.add(card6);
    deck11.add(card7);
    deck11.add(card8);
    deck11.add(card9);
    deck11.add(card10);
    deck11.add(card11);
    deck11.add(card12);

    deck12.add(card1);
    deck12.add(card5);
    deck12.add(card9);
    deck12.add(card10);
    deck12.add(card14);
    deck12.add(card18);
    deck12.add(card17);
    deck12.add(card13);
    deck12.add(card12);
    deck12.add(card2);
    deck12.add(card4);
    deck12.add(card20);
    deck12.add(card21);
    deck12.add(card22);
    deck12.add(card23);
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

    sampleClaimedCards4.add(card2);
    sampleClaimedCards4.add(card13);
    sampleClaimedCards4.add(card27);

    sampleClaimedCards5.add(card10);
    sampleClaimedCards5.add(card14);
    sampleClaimedCards5.add(card18);
  }

  /**
   * Checking if claimSet method throws appropriate IllegalArgumentExceptions
   * when the cards at the given coordinates are not a valid set.
   */
  @Test
  public void testInvalidClaimSet() {
    init();
    // When all three attributes are the same
    try {
      game1 = new SetThreeGameModel();
      game1.startGameWithDeck(deck5,3,3);
      game1.claimSet(coord1,coord2,coord3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("This is not a valid set", e.getMessage());
    }

    // When the first coordinate is not on the grid
    try {
      game2.startGameWithDeck(deck3,3,3);
      game2.claimSet(invalidCoord1,coord2,coord3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Coordinate of a card needs to be within the grid", e.getMessage());
    }

    // When the second coordinate is not on the grid
    try {
      game2.startGameWithDeck(deck3,3,3);
      game2.claimSet(coord1,invalidCoord2,coord3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Coordinate of a card needs to be within the grid", e.getMessage());
    }

    // When the third coordinate is not on the grid
    try {
      game2.startGameWithDeck(deck3,3,3);
      game2.claimSet(coord1,coord2,invalidCoord2);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Coordinate of a card needs to be within the grid", e.getMessage());
    }
  }

  /**
   * Checking if claimSet successfully updates the grid and the deck after a set has been claimed.
   */
  @Test
  public void testClaimSet() {
    init();
    // When count and fillings are the same
    game1 = new SetThreeGameModel();
    game1.startGameWithDeck(deck,3,3);
    game1.claimSet(coord1,coord2,coord3);
    assertEquals(game1.getCardAtCoord(coord1), card10);

    // When the counts are same
    // When there is one set present and enough cards in the deck to replace them.
    game2.startGameWithDeck(deck3,3,3);
    // card that is on coord1 before the grid gets updated.
    assertEquals(game2.getCardAtCoord(coord1), card1);
    game2.claimSet(coord1, coord5, coord9);
    // a set of cards that's being added to a list of claimed sets.
    assertEquals(game2.claimedCards.get(0), sampleClaimedCards);
    // card that is on coord 1 after the grid gets updated
    assertEquals(game2.getCardAtCoord(coord1), card10);

    // When the fillings are same
    game2.startGameWithDeck(deck9,3,3);
    game2.claimSet(coord1, coord2, coord3);
    assertEquals(game2.claimedCards.get(0), sampleClaimedCards2);

    // When the shapes are same
    game3.startGameWithDeck(deck10,3,3);
    game3.claimSet(coord1, coord2, coord3);
    assertEquals(game3.claimedCards.get(0), sampleClaimedCards3);

    // When all the attributes are different
    game4.startGameWithDeck(deck11,3,3);
    game4.claimSet(coord1, coord2, coord3);
    assertEquals(game4.claimedCards.get(0), sampleClaimedCards4);

    // When two sets are present
    game3 = new SetThreeGameModel();
    game3.startGameWithDeck(deck7,3,3);
    game3.claimSet(coord1, coord2, coord3);
    game3.claimSet(coord4,coord5,coord6);
    assertEquals(game3.claimedCards.get(0), sampleClaimedCards);
    assertEquals(game3.claimedCards.get(1), sampleClaimedCards5);

    // When there is one set present on the grid, but no cards to replace them in the deck.
    // It should still claim the cards and then indicate that the game is over.
    game2.startGameWithDeck(deck4,3,3);
    game2.claimSet(coord1,coord5,coord9);
    // The cards have been claimed
    assertEquals(game2.claimedCards.get(0), sampleClaimedCards);
    // Then the game is over
    assertTrue(game2.isGameOver());
  }

  /**
   * Checking if startGameWithDeck can throw IllegalArgumentException when needed.
   */
  @Test
  public void invalidStartGameWithDeck() {
    init();

    // Width is not 3
    try {
      game1.startGameWithDeck(deck,3,2);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Either height or width is not 3", e.getMessage());
    }

    // Height is not 3
    try {
      game1.startGameWithDeck(deck,2,3);
      //game1 = new SetThreeGameModel(deck, 2, 3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Either height or width is not 3", e.getMessage());
    }

    // Deck has less than 9 cards
    try {
      game1.startGameWithDeck(deck2, 3,3);
      fail("The code didn't throw an error");
    } catch (Exception e) {
      assertEquals("Starting deck should have minimum of 9 cards", e.getMessage());
    }

    // Deck is null
    try {
      game1.startGameWithDeck(nullDeck, 3,3);
      fail("The code didn't throw an error");
    } catch (IllegalArgumentException e) {
      assertEquals("The deck is null", e.getMessage());
    }

    // Deck is empty
    try {
      deck = new ArrayList<Card>();
      game1.startGameWithDeck(deck,3,3);
      fail("The code didn't throw an error");
    } catch (IllegalArgumentException e) {
      assertEquals("Starting deck should have minimum of 9 cards", e.getMessage());
    }
  }

  /**
   * Checking if startGameWithDeck can successfully set up
   * the game when all the conditions are met.
   */
  @Test
  public void validStartGameWithDeck() {
    // If game initiates properly, methods like getWidth should be working on the game.
    game1.startGameWithDeck(deck,3,3);
    assertEquals(3, game1.getWidth());
    game1.startGameWithDeck(deck7,3,3);
    assertEquals(deck7.get(0).toString(), card1.toString());
  }

  /**
   * Checks if getWidth returns the right int value and throw
   * IllegalArgumentException when the game hasn't been started yet.
   */
  @Test
  public void getWidth() {
    init();
    // When the game has been started
    game1.startGameWithDeck(deck,3,3);
    assertEquals(3,game1.getHeight());

    // When the game hasn't been started yet
    try {
      game2.getWidth();
      fail("No error thrown");
    } catch (IllegalStateException e) {
      assertEquals("The game hasn't been initiated yet", e.getMessage());
    }
  }

  /**
   * Checks if getHeight returns the right int value and throw
   * IllegalArgumentException when the game hasn't been started yet.
   */
  @Test
  public void getHeight() {
    init();
    // When the game has been started
    game1.startGameWithDeck(deck,3,3);
    assertEquals(3,game1.getWidth());

    // When the game hasn't been started yet
    try {
      game2.getHeight();
      fail("No error thrown");
    } catch (IllegalStateException e) {
      assertEquals("The game hasn't been initiated yet", e.getMessage());
    }
  }

  /**
   * Checks if getScore returns correct int value for the score of the game at that exact moment.
   */
  @Test
  public void getScore() {
    init();
    // before any set has been claimed.
    game1.startGameWithDeck(deck7,3,3);
    assertEquals(0, game1.getScore());

    // after 1 set has been claimed
    game1.claimSet(coord1, coord2, coord3);
    assertEquals(1, game1.getScore());

    // after 2 sets have been claimed
    game1.claimSet(coord4, coord5, coord6);
    assertEquals(2, game1.getScore());

    // after the game is over
    game1.startGameWithDeck(deck,3,3);
  }

  /**
   * Checks if anySetsPresent can check if there is any set present
   * on the grid at the exact moment accurately.
   */
  @Test
  public void anySetsPresent() {
    init();
    // When there is one set present
    game1.startGameWithDeck(deck,3,3);
    assertTrue(game1.anySetsPresent());

    // When there is no set present
    game2.startGameWithDeck(deck5,3,3);
    assertFalse(game2.anySetsPresent());
    assertTrue(game2.isGameOver());

    // When two sets are present
    game3.startGameWithDeck(deck12,3,3);
    assertTrue(game3.anySetsPresent());
  }

  /**
   * Checks if isValidSet can accurately determine if the cards
   * on the given coordinates are a valid set.
   */
  @Test
  public void isValidSet() {
    game1.startGameWithDeck(deck, 3,3);
    // Count and Fillings are the same
    assertTrue(game1.isValidSet(coord1, coord2, coord3));
    // When count and shapes are the same
    assertTrue(game1.isValidSet(coord1, coord4, coord7));
    // when fillings and shapes are the same
    game1.startGameWithDeck(deck8,3,3);
    assertTrue(game1.isValidSet(coord1, coord2, coord3));
    // when all three attributes are the same
    game1.startGameWithDeck(deck5,3,3);
    assertFalse(game1.isValidSet(coord1, coord2, coord3));
    // Same count
    game1.startGameWithDeck(deck,3,3);
    assertTrue(game1.isValidSet(coord1, coord5,coord9));
    // Same filling
    game1.startGameWithDeck(deck9,3,3);
    assertTrue(game1.isValidSet(coord1, coord2, coord3));
    // Same shape
    game1.startGameWithDeck(deck10,3,3);
    assertTrue(game1.isValidSet(coord1, coord2, coord3));
    // All attributes are different
    game1.startGameWithDeck(deck11,3,3);
    assertTrue(game1.isValidSet(coord1, coord2, coord3));
    try {
      game2.isValidSet(coord1, coord2, coord3);
      fail("No error thrown");
    } catch (IllegalStateException e) {
      assertEquals("The game hasn't been initiated yet", e.getMessage());
    }
  }

  /**
   * Checks if getCardAtCoord accurately returns cards on the given coordinates.
   */
  @Test
  public void getCardAtCoordWhenCoordGiven() {
    init();
    game1.startGameWithDeck(deck,3,3);
    assertEquals(card1, game1.getCardAtCoord(coord1));
    assertEquals(card2, game1.getCardAtCoord(coord2));

    // testing if it works after some cards got replaced
    assertEquals(card1,game1.getCardAtCoord(coord1));
    assertEquals(card5,game1.getCardAtCoord(coord5));
    assertEquals(card9,game1.getCardAtCoord(coord9));
    game1.claimSet(coord1, coord5, coord9);
    assertEquals(card10, game1.getCardAtCoord(coord1));
    assertEquals(card11, game1.getCardAtCoord(coord5));
    assertEquals(card12, game1.getCardAtCoord(coord9));
  }

  /**
   * Checks if getCardAtCoord accurately returns cards on the given row and col.
   */
  @Test
  public void getCardAtCoordWhenRowColGiven() {
    init();
    game1.startGameWithDeck(deck,3,3);
    assertEquals(card1, game1.getCardAtCoord(0,0));
    assertEquals(card2, game1.getCardAtCoord(0,1));

    // testing if it works after some cards got replaced
    assertEquals(card1,game1.getCardAtCoord(0,0));
    assertEquals(card5,game1.getCardAtCoord(1,1));
    assertEquals(card9,game1.getCardAtCoord(2,2));
    game1.claimSet(coord1, coord5, coord9);
    assertEquals(card10, game1.getCardAtCoord(0,0));
    assertEquals(card11, game1.getCardAtCoord(1,1));
    assertEquals(card12, game1.getCardAtCoord(2,2));
  }

  /**
   * Checks if isGameOver can successfully determine if the
   * game is over or not in different situations.
   */
  @Test
  public void isGameOver() {
    // when the game has no more set left on the grid
    game1.startGameWithDeck(deck,3,3);
    assertFalse(game1.isGameOver());
    game1.claimSet(coord1,coord5,coord9);
    assertFalse(game1.isGameOver());
    assertEquals(1,game1.getScore());

    // when the game has one set left but not enough cards in the deck to replace them
    game2.startGameWithDeck(deck4,3,3);
    assertFalse(game2.isGameOver());
    game2.claimSet(coord1, coord5, coord9);
    assertTrue(game2.isGameOver());
    assertEquals(1,game2.getScore());
  }

  /**
   * Checks if getCompleteDeck creates a new List of Cards with all
   * the possible cards (27 cards in this case).
   */
  @Test
  public void getCompleteDeck() {
    game1.startGameWithDeck(deck,3,3);
    assertEquals(27, game1.getCompleteDeck().size());
    assertEquals(deck3.get(0).toString(), game1.getCompleteDeck().get(0).toString());
    assertEquals(deck3.get(1).toString(), game1.getCompleteDeck().get(1).toString());
    assertEquals(deck3.get(2).toString(), game1.getCompleteDeck().get(2).toString());
  }

  /**
   * Testing if my card class can properly initialize a card with its constructor.
   */
  @Test
  public void cardConstructor() {
    Card sample = new Card(Counts.One, Fillings.Empty, Shapes.Oval);
    assertEquals(sample.getCount(), Counts.One);
    assertEquals(sample.getFilling(), Fillings.Empty);
    assertEquals(sample.getShape(), Shapes.Oval);
  }

  /**
   * Testing if the card constructor throws appropriate exceptions.
   */
  @Test
  public void invalidCard() {
    // Count field is null
    try {
      Card sample = new Card(null, Fillings.Empty, Shapes.Oval);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("None of the attributes can be null", e.getMessage());
    }

    // Filling field is null
    try {
      Card sample = new Card(Counts.One, null, Shapes.Oval);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("None of the attributes can be null", e.getMessage());
    }

    // Shape field is null
    try {
      Card sample = new Card(Counts.One, Fillings.Empty, null);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("None of the attributes can be null", e.getMessage());
    }
  }
}