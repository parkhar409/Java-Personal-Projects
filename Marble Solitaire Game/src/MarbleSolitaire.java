import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class representing the main class for the entire MarbleSolitaire project,
 * which has the main method that can run the program.
 */
public final class MarbleSolitaire {

  /**
   * Main method to be used to run the game for the MarbleSolitaire game.
   * @param args are command-line arguments each being,
   *             (english, european, or triangle), N size of the board, and
   *             the coordinate of a given hole.
   */
  public static void main(String[] args) {
    Appendable output = System.out;
    Readable input = new InputStreamReader(System.in);
    int size = 0;
    int row = 0;
    int col = 0;
    boolean givenSize = false;
    boolean givenHole = false;

    int i = 1;
    while (i < args.length) {
      if (args[i].equalsIgnoreCase("-size")) {
        size = Integer.parseInt(args[i + 1]);
        i += 2;
        givenSize = true;
      } else if (args[i].equalsIgnoreCase("-hole")) {
        row = Integer.parseInt(args[i + 1]);
        col = Integer.parseInt(args[i + 2]);
        i += 3;
        givenHole = true;
      }
      else {
        i++;
      }
    }

    switch (args[0]) {
      case "english" :
        MarbleSolitaireModel gameEN = null;
        if (!givenSize && !givenHole) {
          gameEN = new EnglishSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          gameEN = new EnglishSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          gameEN = new EnglishSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          gameEN = new EnglishSolitaireModel(size, row, col);
        }
        MarbleSolitaireTextView viewEN = new MarbleSolitaireTextView(gameEN);
        MarbleSolitaireControllerImpl controllerEN =
                new MarbleSolitaireControllerImpl(gameEN, viewEN, input);
        controllerEN.playGame();
        break;
      case "european" :
        MarbleSolitaireModel gameEU = null;
        if (!givenSize && !givenHole) {
          gameEU = new EuropeanSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          gameEU = new EuropeanSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          gameEU = new EuropeanSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          gameEU = new EuropeanSolitaireModel(size, row, col);
        }
        MarbleSolitaireTextView viewEU = new MarbleSolitaireTextView(gameEU);
        MarbleSolitaireControllerImpl controllerEU =
                new MarbleSolitaireControllerImpl(gameEU, viewEU, input);
        controllerEU.playGame();
        break;
      case "triangle" :
        MarbleSolitaireModel gameTr = null;
        if (!givenSize && !givenHole) {
          gameTr = new TriangleSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          gameTr = new TriangleSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          gameTr = new TriangleSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          gameTr = new TriangleSolitaireModel(size, row, col);
        }
        MarbleSolitaireView viewTri = new TriangleSolitaireTextView(gameTr, output);
        MarbleSolitaireControllerImpl controllerTri =
                new MarbleSolitaireControllerImpl(gameTr, viewTri, input);
        controllerTri.playGame();
        break;
      default:
        throw new RuntimeException("Error in starting the game");
    }
  }
}

