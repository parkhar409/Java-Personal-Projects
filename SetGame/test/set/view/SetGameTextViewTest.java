package set.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Counts;
import cs3500.set.model.hw02.Fillings;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shapes;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class that contains all the testing methods for all the public
 * methods that has to do with visualizing thew game.
 */
public class SetGameTextViewTest {
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
   * Testing if toString method can accurately visualize the board.
   */
  @Test
  public void testToString() {
    init();
    game1.startGameWithDeck(deck,3,3);
    assertEquals("1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD", gameview1.toString());

    game1.startGameWithDeck(deck,3,3);
    assertEquals("1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD", gameview1.toString());

    game1.startGameWithDeck(deck7,3,3);
    assertEquals("1EO 1SQ 1FD\n" +
            "2EO 2SQ 2FD\n" +
            "2FQ 2SO 2ED", gameview1.toString());

    game1.startGameWithDeck(deck9,3,3);
    assertEquals("1EO 2EQ 3ED\n" +
            "1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO", gameview1.toString());

    game1.startGameWithDeck(deck12,3,3);
    assertEquals("1EQ 2FO 1ED\n" +
            "2SD 3SO 2ED\n" +
            "1FQ 2EQ 3ED", gameview1.toString());
  }

  @Test
  public void testToStringCard() {
    init();
    assertEquals(card1.toString(), "1EO");
    assertEquals(card20.toString(), "3EQ");
    assertEquals(card10.toString(), "2EO");
    assertEquals(card2.toString(), "1EQ");
    assertEquals(card13.toString(), "2SO");
    assertEquals(card16.toString(), "2FO");
  }

  /**
   * Checking if toString throws IllegalArgumentExceptions when
   * the given game/board is null.
   */
  @Test
  public void inValidToString() {
    init();
    try {
      game1 = null;
      gameview1 = new SetGameTextView(game1);
      fail("No error thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The given board is null", e.getMessage());
    }
  }

  /**
   * Testing if toString works after the board gets updated as the game is being played.
   */
  @Test
  public void testClaimSet() {
    init();
    game1.startGameWithDeck(deck11,3,3);
    game1.claimSet(coord1, coord2, coord3);
    assertEquals("2EO 2SQ 2FD\n" +
            "1EQ 1SO 1FO\n" +
            "1SD 1FO 2ED", gameview1.toString());
    game1.claimSet(coord1,coord2,coord3);
    assertEquals("3EQ 3ED 3SQ\n" +
            "1EQ 1SO 1FO\n" +
            "1SD 1FO 2ED", gameview1.toString());

    game1.startGameWithDeck(deck12,3,3);
    game1.claimSet(coord5,coord6,coord7);
    assertEquals("1EQ 2FO 1ED\n" +
            "2SD 1EO 3EQ\n" +
            "3SD 2EQ 3ED", gameview1.toString());

    game1.startGameWithDeck(deck11,3,3);
    assertEquals("1EO 1SQ 1FD\n" +
            "1EQ 1SO 1FO\n" +
            "1SD 1FO 2ED", gameview1.toString());
  }

  /**
   * Testing if the renderGrid method can accurately render the grid.
   */
  @Test
  public void testRenderGrid() {
    Appendable ap = new StringBuilder();
    game1.startGameWithDeck(deck,3,3);
    SetGameView gameView1 = new SetGameTextView(game1, ap);
    try {
      gameView1.renderGrid();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    String[] result = ap.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", result[0]);
    assertEquals("1SO 1SQ 1SD", result[1]);
    assertEquals("1FO 1FQ 1FD", result[2]);
  }
}