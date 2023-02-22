package cs3500.set.model.hw02;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw03.AbstractSetGameModel;

/**
 * This class represents the class which initiates a 3 x 3 grid card set game.
 */
public class SetThreeGameModel extends AbstractSetGameModel {

  /**
   * First constructor of the game with default game settings of :
   * 3 x 3 grid, deck of 27 cards.
   */
  public SetThreeGameModel() {
    super();
    this.row = 3;
    this.col = 3;
    this.board = new Card[row][col];
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
    if (coord1.row < 0 || coord1.col < 0 || coord1.row >= 3 || coord1.col >= 3 ||
            coord2.row < 0 || coord2.col < 0 || coord2.row >= 3 || coord2.col >= 3 ||
            coord3.row < 0 || coord3.col < 0 || coord3.row >= 3 || coord3.col >= 3) {
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
    if (deck == null) {
      this.gameStarted = false;
      this.gameOver = true;
      this.row = height;
      this.col = width;
      throw new IllegalArgumentException("The deck is null");
    }

    if (height != 3 || width != 3) {
      this.startingDeck = deck;
      this.gameStarted = false;
      this.gameOver = true;
      throw new IllegalArgumentException("Either height or width is not 3");
    }

    if (deck.size() < height * width) {
      this.gameStarted = false;
      this.gameOver = true;
      this.row = height;
      this.col = width;
      throw new IllegalArgumentException("Starting deck should have minimum of 9 cards");
    }
    List<Card> coppiedDeck = copyOfDeck(deck);
    this.startingDeck = coppiedDeck;
    this.gameStarted = true;
    this.gameOver = false;
    this.row = 3;
    this.col = 3;
    this.board = this.makeBoard(coppiedDeck);
    this.claimedCards = new ArrayList<>(new ArrayList<>());
    // should have other fields defined here too?
  }
}