package cs3500.set.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Counts;
import cs3500.set.model.hw02.Fillings;
import cs3500.set.model.hw02.SetGameMockModel;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shapes;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests to check if the controller works accurately.
 */
public class SetGameControllerImplTest {
  SetThreeGameModel game1;
  SetThreeGameModel game2;
  SetThreeGameModel game3;
  GeneralSetGameModel generalGame1;
  SetGameTextView gameview1;
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
  }

  /**
   * Testing if the controller constructor throws an exception when needed.
   */
  @Test
  public void invalidControllerConstructor() {
    // When the model is null
    try {
      Appendable ap = new StringBuilder();
      SetGameView gameView = new SetGameTextView(game1, ap);
      Readable input1 = new StringReader("a a q");
      SetGameController testController = new SetGameControllerImpl(null, gameView, input1);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("The given game is null", e.getMessage());
    }

    // When the view is null
    try {
      Appendable ap = new StringBuilder();
      Readable input1 = new StringReader("a a q");
      SetGameController testController = new SetGameControllerImpl(game1, null, input1);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("The given view is null", e.getMessage());
    }

    // When the input is null
    try {
      Appendable ap = new StringBuilder();
      SetGameView gameView = new SetGameTextView(game1, ap);
      SetGameController testController = new SetGameControllerImpl(game1, gameView, null);
      fail("No error");
    } catch (IllegalArgumentException e) {
      assertEquals("The given input is null", e.getMessage());
    }

  }


  /**
   * Invalid height and width inputs.
   */
  @Test
  public void invalidSetUp() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("a a q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());

    Appendable ap2 = new StringBuilder();
    SetGameView gameView2 = new SetGameTextView(game1, ap2);
    Readable input2 = new StringReader("2 2 q");
    SetGameController testController2 = new SetGameControllerImpl(game1, gameView2, input2);
    testController2.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());
  }


  /**
   * Width is not 3.
   */
  @Test
  public void invalidSetUp2() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("3 a q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());

    Appendable ap2 = new StringBuilder();
    SetGameView gameView2 = new SetGameTextView(game1, ap2);
    Readable input2 = new StringReader("3 2 q");
    SetGameController testController2 = new SetGameControllerImpl(game1, gameView2, input2);
    testController2.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Height is not 3.
   */
  @Test
  public void invalidSetUp3() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("a 3 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());

    Appendable ap2 = new StringBuilder();
    SetGameView gameView2 = new SetGameTextView(game1, ap2);
    Readable input2 = new StringReader("2 3 q");
    SetGameController testController2 = new SetGameControllerImpl(game1, gameView2, input2);
    testController2.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. It should be an integer. \n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Invalid claimSet attempt that are positive inputs.
   */
  @Test
  public void invalidInput2() {
    Appendable ap = new StringBuilder();
    SetGameView gameView1 = new SetGameTextView(game1, ap);
    Reader input1 = new StringReader("3 3 1 1 1 1 1 1 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "Invalid claim. Try again. This is not a valid set\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n", ap.toString());
  }


  /**
   * Quitting after only inputting the height and width.
   */
  @Test
  public void setUp() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("3 3 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Initiating the game, playing the game, then quitting the game.
   */
  @Test
  public void setUp2() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("3 3 1 1 1 2 1 3 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "2EO 2EQ 2ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EO 2EQ 2ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 1\n", ap.toString());
  }


  /**
   * Quitting the game immediately.
   */
  @Test
  public void setUp3() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Testing if the game quits when q or Q has been typed in for the width of the board.
   */
  @Test
  public void setUp4() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(game1, ap);
    Readable input1 = new StringReader("3 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Game quit!\n" +
            "Score: 0\n", ap.toString());
  }


  /**
   * Checking if the controller can successfully execute claimSet with the given inputs when
   * there is one set available on the grid - using the playGame method.
   * With one attempt of creating a game with invalid inputs.
   */
  @Test
  public void playGame() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView1 = new SetGameTextView(game1, ap);
    Reader input1 = new StringReader("2 2 3 3 1 1 2 2 3 3 q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
    testController.playGame();

    // claims a set properly, updates the score properly and renders
    // the grid properly as the game is being played
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "Invalid height/width. Try again. Either height or width is not 3.\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "2EO 1EQ 1ED\n" +
            "1SO 2EQ 1SD\n" +
            "1FO 1FQ 2ED\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EO 1EQ 1ED\n" +
            "1SO 2EQ 1SD\n" +
            "1FO 1FQ 2ED\n" +
            "Score: 1\n", ap.toString());
  }

  /**
   * Checking if the controller can successfully execute claimSet with the given inputs when
   * there are two sets available on the grid - using the playGame method.
   */
  @Test
  public void playGame2() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView1 = new SetGameTextView(game1, ap);
    Reader input1 = new StringReader("3 3 1 1 1 2 1 3 2 1 2 2 2 3 Q");
    SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
    testController.playGame();

    // claims the two sets properly, updates the score accordingly and renders
    // the grid properly as the game is being played
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "2EO 2EQ 2ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 1\n" +
            "2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 2\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 2\n", ap.toString());
  }

  /**
   * Testing if the controller throws an appropriate IllegalStateArgument.
   */
  @Test
  public void playGame3() {
    init();
    // when there are no more inputs to scan
    try {
      Appendable ap = new StringBuilder();
      game1.startGameWithDeck(deck,3,3);
      SetGameView gameView1 = new SetGameTextView(game1, ap);
      Reader input1 = new StringReader("");
      SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
      testController.playGame();

      fail("The code didn't throw any error");
    } catch (Exception e) {
      assertEquals("There are no more inputs to scan\n", e.getMessage());
    }

    try {
      Appendable ap = new StringBuilder();
      game1.startGameWithDeck(deck,3,3);
      SetGameView gameView1 = new SetGameTextView(game1, ap);
      Reader input1 = new StringReader("");
      SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
      testController.playGame();

      fail("The code didn't throw any error");
    } catch (Exception e) {
      assertEquals("There are no more inputs to scan\n", e.getMessage());
    }

    // When the appendable object can't transmit output
    try {
      game1.startGameWithDeck(deck,3,3);
      SetGameView gameView1 = new SetGameTextView(game1, null);
      Reader input1 = new StringReader("");
      SetGameController testController = new SetGameControllerImpl(game1, gameView1, input1);
      testController.playGame();

      fail("The code didn't throw any error");
    } catch (Exception e) {
      assertEquals("The given appendable is null", e.getMessage());
    }
  }



  /**
   * Checking if the methods are being called at the right time.
   * When one initiates the board, play the game, then quit.
   */
  @Test
  public void mockTest() {
    init();
    StringBuilder ap = new StringBuilder();
    SetGameModel mock = new SetGameMockModel(ap);
    SetGameView mockView = new SetGameTextView(mock, ap);
    Readable input1 = new StringReader("3 3 1 1 1 2 1 3 q");
    SetGameController testController = new SetGameControllerImpl(mock, mockView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "get CompleteDekc called \n" +
            "Board rendered with 3 3\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "getHeight called \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n", ap.toString());
  }

  /**
   * Checking if the methods are being called at the right time.
   * When one initiates the board then quit.
   */
  @Test
  public void mockTest2() {
    init();
    StringBuilder ap = new StringBuilder();
    SetGameModel mock = new SetGameMockModel(ap);
    SetGameView mockView = new SetGameTextView(mock, ap);
    Readable input1 = new StringReader("3 3 q");
    SetGameController testController = new SetGameControllerImpl(mock, mockView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "get CompleteDekc called \n" +
            "Board rendered with 3 3\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "getHeight called \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n", ap.toString());
  }

  /**
   * Checking if the methods are being called at the right time.
   * When one quits the game immediately.
   */
  @Test
  public void mockTest3() {
    init();
    StringBuilder ap = new StringBuilder();
    SetGameModel mock = new SetGameMockModel(ap);
    SetGameView mockView = new SetGameTextView(mock, ap);
    Readable input1 = new StringReader("q");
    SetGameController testController = new SetGameControllerImpl(mock, mockView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "getHeight called \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Checking if the methods are being called at the right time.
   * When the game automatically ends after there are no more sets to claim.
   */
  @Test
  public void mockTest4() {
    init();
    StringBuilder ap = new StringBuilder();
    SetGameModel mock = new SetGameMockModel(ap);
    SetGameView mockView = new SetGameTextView(mock, ap);
    Readable input1 = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 " +
            "1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 q");
    SetGameController testController = new SetGameControllerImpl(mock, mockView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "get CompleteDekc called \n" +
            "Board rendered with 3 3\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "isGameOver called \n" +
            "claimSet called\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n" +
            "getHeight called \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "getHeight called \n" +
            "getScore called \n" +
            "\n" +
            "Score: 0\n" +
            "isGameOver called \n", ap.toString());
  }

  // Following tests are newly created tests for Assignment 3

  /**
   * Testing if the controller can create a game with a board size that's not 3x3.
   */
  @Test
  public void setUpGeneral() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(generalGame1, ap);
    Readable input1 = new StringReader("4 4 q");
    SetGameController testController = new SetGameControllerImpl(generalGame1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO\n" +
            "Score: 0\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO\n" +
            "Score: 0\n", ap.toString());
  }

  /**
   * Testing claiming a set works with the controller on a 4 x 4 size grid
   * (This is an example where adding a new row is not needed).
   */
  @Test
  public void generalSetup2() {
    init();
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(generalGame1, ap);
    Readable input1 = new StringReader("4 4 1 1 1 2 1 3 q");
    SetGameController testController = new SetGameControllerImpl(generalGame1, gameView, input1);
    testController.playGame();
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO\n" +
            "Score: 0\n" +
            "2FQ 2FD 3EO 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2FQ 2FD 3EO 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "2SO 2SQ 2SD 2FO\n" +
            "Score: 1\n", ap.toString());
  }

  /**
   * Testing if the controller can properly claim a set and automatically end the game
   * when needed.
   */
  @Test
  public void generalClaimSet() {
    Appendable ap = new StringBuilder();
    SetGameView gameView = new SetGameTextView(generalGame1, ap);
    Readable input1 = new StringReader("3 4 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3 " +
            "1 1 1 2 1 3  q");
    SetGameController testController = new SetGameControllerImpl(generalGame1, gameView, input1);
    testController.playGame();

    // Even though I try to claim a set 7 times, it automatically ends
    // the game after claiming the 6th set
    // since there is no more card left in the deck to replace them.

    // Which is why the score is 6 and the controller renders "Game over!"
    // message rather than "Game quit!"
    assertEquals("Welcome to the game! \n" +
            "Please input a desired height and width of the grid. \n" +
            "Remember that the grid and height should be 3 :)\n" +
            "1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 0\n" +
            "2SO 2SQ 2SD 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 1\n" +
            "2FO 2FQ 2FD 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 2\n" +
            "3EO 3EQ 3ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 3\n" +
            "3SO 3SQ 3SD 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 4\n" +
            "3FO 3FQ 3FD 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 5\n" +
            "3FO 3FQ 3FD 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "Score: 6\n", ap.toString());
  }

}