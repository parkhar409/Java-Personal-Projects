package cs3500.marblesolitaire.model.hw02;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * Provided static void method.
   * @param args .
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new EnglishSolitaireModel(), rd, ap);
    helper(new EnglishSolitaireModel(3, 3), rd, ap);
  }

  /**
   * Provided static method.
   * @param model board.
   * @param rd readable.
   * @param ap appendable.
   */
  private static void helper(MarbleSolitaireModel model, Readable rd, Appendable ap) {
    new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
            new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model,ap),rd);
  }

}
