package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Counts;
import cs3500.set.model.hw02.Fillings;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shapes;

/**
 * This is an abstract class for all the different possible boards, which takes care of
 * everything from initializing the boards to playing the game.
 */
public abstract class AbstractSetGameModel implements SetGameModel<Card> {
  protected int row;
  protected int col;
  // Board/grid of the game
  protected Card[][] board;
  // Deck that the user starts with
  protected List<Card> startingDeck;
  // List of set of cards that are claimed by the user during the game
  // this is public to allow users to have access to their claimed cards
  public List<List<Card>> claimedCards;
  // indicated if the game has ever been started
  protected boolean gameStarted;
  // indicates if the game is over
  protected boolean gameOver;

  /**
   * This is a constructor for the abstract class which initiates some variables
   * that can be universally used.
   */
  public AbstractSetGameModel() {
    this.startingDeck = new ArrayList<Card>();
    this.board = new Card[row][col];
    this.claimedCards = new ArrayList<>(new ArrayList<>());
    this.gameStarted = false;
    this.gameOver = true;
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) throws IllegalArgumentException {
    List<Card> set = new ArrayList<Card>();
    if (!this.gameStarted) {
      throw new IllegalStateException("The game hasn't been started");
    } // Given coordinates are null
    if (coord1 == null || coord2 == null || coord3 == null) {
      throw new IllegalArgumentException("The coordinates can't be null");
    }
    // Given coordinates are not on the gid
    if (coord1.row < 0 || coord1.col < 0 || coord1.row >= this.row || coord1.col >= this.col ||
            coord2.row < 0 || coord2.col < 0 || coord2.row >= this.row || coord2.col >= this.col ||
            coord3.row < 0 || coord3.col < 0 || coord3.row >= this.row || coord3.col >= this.col) {
      throw new IllegalArgumentException("Coordinate of a card needs to be within the grid");
    }
    // Cards at the given coordinates are not a valid set
    else if (!isValidSet(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("This is not a valid set");
    } // there is a set on the board and enough cards in the deck to replace them
    else if (isValidSet(coord1, coord2, coord3) && !this.noCardsInDeck()) {
      // add it to the claimed deck
      set.add(getCardAtCoord(coord1));
      set.add(getCardAtCoord(coord2));
      set.add(getCardAtCoord(coord3));
      this.claimedCards.add(set);

      // get rid of the cards on the grid && replace them with new cards from the deck
      this.board[coord1.row][coord1.col] = this.startingDeck.get(0);
      this.startingDeck.remove(0);

      this.board[coord2.row][coord2.col] = this.startingDeck.get(0);
      this.startingDeck.remove(0);

      this.board[coord3.row][coord3.col] = this.startingDeck.get(0);
      this.startingDeck.remove(0);

      // Check if there is any set present if not,
      while (!this.anySetsPresent()) {
        // check if i can add a new row to the board, if yes,
        if (this.enoughCardsToAddNewRow()) {
          this.board = modifyBoardClaimSet(this.board, this.startingDeck);
          this.row = this.board.length;
          this.col = this.board[0].length;
        } else {
          this.gameOver = true;
          throw new IllegalArgumentException("Not enough cards to add a new row nor any " +
                  "valid sets available after claiming the set");
        }
      }

    } // there is a set but no cards in the deck to replace them
    else if (isValidSet(coord1, coord2, coord3) && this.noCardsInDeck()) {
      // add it to the claimed deck
      set.add(getCardAtCoord(coord1));
      set.add(getCardAtCoord(coord2));
      set.add(getCardAtCoord(coord3));

      this.claimedCards.add(set);

      // after adding the set to the claimed deck, notify that the game is over
      this.gameOver = true;
    }
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {
    if (height * width < 3) {
      this.gameStarted = false;
      this.gameOver = true;
      throw new IllegalArgumentException("The board should be able to have " +
              "at least three cards on it");
    }

    if (deck == null) {
      this.gameStarted = false;
      this.gameOver = true;
      this.row = height;
      this.col = width;
      throw new IllegalArgumentException("The deck is null");
    }

    if (height < 0 || width < 0) {
      this.startingDeck = deck;
      this.gameStarted = false;
      this.gameOver = true;
      throw new IllegalArgumentException("Either height or width is not positive");
    }

    if (deck.size() < height * width) {
      this.gameStarted = false;
      this.gameOver = true;
      this.row = height;
      this.col = width;
      throw new IllegalArgumentException("Not enough cards to fill the board");
    }
    // If all the conditions are met, first declare the fields
    List<Card> coppiedDeck = copyOfDeck(deck);
    this.startingDeck = coppiedDeck;
    this.gameStarted = true;
    this.gameOver = false;
    this.row = height;
    this.col = width;
    this.board = new Card[row][col];
    this.board = this.makeBoard(coppiedDeck);
    this.claimedCards = new ArrayList<>(new ArrayList<>());

    // check if we need to add a new row
    while (!this.anySetsPresent()) {
      // check if it can add a new row to the board, if yes,
      if (this.enoughCardsToAddNewRow()) {
        this.board = modifyBoardClaimSet(this.board, this.startingDeck);
        this.row = this.board.length;
        this.col = this.board[0].length;
      } else {
        this.gameOver = true;
        throw new IllegalArgumentException("Not enough cards to add a new row nor " +
                "any valid sets available");
      }
    }
  }

  // adds new rows until there are no more cards left in the deck to do so
  private Card[][] modifyBoardClaimSet(Card[][] originalBoard, List<Card> originalDeck)
          throws IllegalArgumentException {
    Card[][] modifiedBoard = copyOfBoard(originalBoard);
    for (int c = 0;c < modifiedBoard[0].length; c++) {
      int r = modifiedBoard.length;
      modifiedBoard[r - 1][c] = originalDeck.get(0);
      originalDeck.remove(0);
    }
    this.startingDeck = originalDeck;
    return modifiedBoard;
  }

  // Copy and paste all the cards from the original boards to the new board with a new row
  private Card[][] copyOfBoard(Card[][] boardToCopy) {
    Card[][] modifiedBoard = new Card[row + 1][col];
    for (int r = 0; r < boardToCopy.length; r++) {
      for (int c = 0; c < boardToCopy[r].length; c++) {
        modifiedBoard[r][c] = boardToCopy[r][c];
      }
    }
    return modifiedBoard;
  }

  // checks if the current deck has enough cards to add a new row
  // true if enough cards
  // false otherwise
  private boolean enoughCardsToAddNewRow() {
    return this.startingDeck.size() >= this.getWidth();
  }

  @Override
  public int getWidth() throws IllegalStateException {
    if (this.gameStarted) {
      return this.col;
    }
    throw new IllegalStateException("The game hasn't been initiated yet");
  }

  @Override
  public int getHeight() throws IllegalStateException {
    if (this.gameStarted) {
      return this.row;
    }
    throw new IllegalStateException("The game hasn't been initiated yet");
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (this.gameStarted) {
      return this.claimedCards.size();
    }
    throw new IllegalStateException("The game hasn't been initiated yet");
  }

  @Override
  public boolean anySetsPresent() {
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        for (int k = 0; k < this.getHeight(); k++) {
          for (int z = 0; z < this.getWidth(); z++) {
            for (int x = 0; x < this.getHeight(); x++) {
              for (int y = 0; y < this.getWidth(); y++) {
                Coord coord1 = new Coord(i,j);
                Coord coord2 = new Coord(k,z);
                Coord coord3 = new Coord(x,y);
                if (isValidSet(coord1, coord2, coord3)) {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    this.gameOver = true;
    return false;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3) {
    // all three counts are different
    boolean countDif = isDifferentCount(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));
    // all three counts are the same
    boolean countSame = isSameCount(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));
    // all three fillings are different
    boolean fillingDif = isDifferentFillings(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));
    // all three fillings are the same
    boolean fillingSame = isSameFillings(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));
    // all three shapes are different
    boolean shapeDif = isDifferentShape(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));
    // all three shapes are the same
    boolean shapeSame = isSameShape(getCardAtCoord(coord1),
            getCardAtCoord(coord2),getCardAtCoord(coord3));

    if (!this.gameStarted) {
      throw new IllegalStateException("The game hasn't been initiated yet");
    } // Given coordinates are null
    if (coord1 == null || coord2 == null || coord3 == null) {
      throw new IllegalArgumentException("The coordinates can't be null");
    }
    else if (coord1.row < 0 || coord1.col < 0 || coord1.row > this.row || coord1.col > this.col ||
            coord2.row < 0 || coord2.col < 0 || coord2.row > this.row || coord2.col > this.col ||
            coord3.row < 0 || coord3.col < 0 || coord3.row > this.row || coord3.col > this.col) {
      throw new IllegalArgumentException("Coordinate of a card needs to be within the grid");
    }
    // same count, different filling, different shape
    return (countSame && fillingDif && shapeDif)
            // same count, same filling, different shape
            || (countSame && fillingSame && shapeDif)
            // same count, different filling, same shape
            || (countSame && fillingDif && shapeSame)
            // different count, same filling, different shape
            || (countDif && fillingSame && shapeDif)
            // different count, same fillings, same shape
            || (countDif && fillingSame && shapeSame)
            // different count, different filling, same shape
            || (countDif && fillingDif && shapeSame)
            // everything different
            || (countDif && fillingDif && shapeDif);
  }

  @Override
  public Card getCardAtCoord(int row, int col) {
    if (!this.gameStarted) {
      throw new IllegalStateException("The game hasn't been initiated yet");
    } if (this.board[row][col] == null) {
      throw new IllegalArgumentException("The coordinates can't be null");
    }
    return this.board[row][col];
  }

  @Override
  public Card getCardAtCoord(Coord coord) {
    if (!this.gameStarted) {
      throw new IllegalStateException("The game hasn't been initiated yet");
    } if (this.board[coord.row][coord.col] == null) {
      throw new IllegalArgumentException("The coordinates can't be null");
    }
    return this.board[coord.row][coord.col];
  }

  @Override
  public boolean isGameOver() {
    if (this.anySetsPresent() && !this.gameOver) {
      this.gameOver = false;
      return false;
    }
    return (!this.anySetsPresent() || this.gameOver);
  }

  @Override
  public List<Card> getCompleteDeck() {
    List<Card> completeDeck = new ArrayList<Card>();
    for (Counts counts : Counts.values()) {
      for (Fillings fillings : Fillings.values()) {
        for (Shapes shapes : Shapes.values()) {
          completeDeck.add(new Card(counts, fillings, shapes));
        }
      }
    }
    return completeDeck;
  }

  protected Card[][] makeBoard(List<Card> deck) {
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        // places the cards from the deck to the grid
        board[i][j] = deck.get(0);
        // gets rid of the cards form the deck as they are being placed to the grid
        deck.remove(0);
      }
    }
    return board;
  }

  protected List<Card> copyOfDeck(List<Card> deck) {
    List<Card> coppiedeck = new ArrayList<Card>();
    coppiedeck.addAll(deck);
    return coppiedeck;
  }

  // Check if there is less than 3 cards left in the deck
  protected boolean noCardsInDeck() {
    return this.startingDeck.size() < 3;
  }

  // Check if the cards have different counts
  protected boolean isDifferentCount(Card card1, Card card2, Card card3) {
    return card1.getCount() != card2.getCount() && card1.getCount() != card3.getCount()
            && card2.getCount() != card3.getCount();
  }

  // Check if the cards have same counts
  protected boolean isSameCount(Card card1, Card card2, Card card3) {
    return (card1.getCount() == card2.getCount()) && (card2.getCount() == card3.getCount())
            && (card1.getCount() == card3.getCount());
  }

  // Check if the cards have different fillings
  protected boolean isDifferentFillings(Card card1, Card card2, Card card3) {
    return card1.getFilling() != card2.getFilling() && card1.getFilling() != card3.getFilling()
            && card2.getFilling() != card3.getFilling();
  }

  // Check if the cards have same fillings
  protected boolean isSameFillings(Card card1, Card card2, Card card3) {
    return (card1.getFilling() == card2.getFilling()) && (card2.getFilling() == card3.getFilling())
            && (card1.getFilling() == card3.getFilling());
  }

  // Check if the cards have different shapes
  protected boolean isDifferentShape(Card card1, Card card2, Card card3) {
    return card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape()
            && card2.getShape() != card3.getShape();
  }

  // Check if the cards have same shape
  protected boolean isSameShape(Card card1, Card card2, Card card3) {
    return (card1.getShape() == card2.getShape()) && (card2.getShape() == card3.getShape())
            && (card1.getShape() == card3.getShape());
  }


}
