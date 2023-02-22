package cs3500.set.model.hw02;

/**
 * This class represents the cards to be used in the game.
 */
public class Card {
  private final Counts count;
  private final Fillings filling;
  private final Shapes shape;

  /**
   * A constructor that creates a card with different attributes assigned to it.
   * @param count represents the amount of shapes on the card.
   * @param filling represents the filling of the shapes on the card.
   * @param shape represents the shapes that are one the card.
   */
  public Card(Counts count, Fillings filling, Shapes shape) throws IllegalArgumentException {
    if ((count == null) || (filling == null) || (shape == null)) {
      throw new IllegalArgumentException("None of the attributes can be null");
    }
    this.count = count;
    this.filling = filling;
    this.shape = shape;
  }

  /**
   * Gets the Count attribute of the card.
   * @return the enum value of the cards' Count attribute
   */
  public Counts getCount() {
    return this.count;
  }

  /**
   * Gets the Filling attribute of the card.
   * @return the enum value of the cards' Filling attribute
   */
  public Fillings getFilling() {
    return this.filling;
  }

  /**
   * Gets the Shape attribute of the card.
   * @return the enum value of the cards' Shape attribute
   */
  public Shapes getShape() {
    return this.shape;
  }

  // Visualizes a card as a string
  @Override
  public String toString() {
    if (this == null) {
      throw new IllegalArgumentException("The card is null");
    }
    String visualizedCard = "";
    visualizedCard = visualizedCard + this.visualizeCount()
            + this.visualizeFilling() + this.visualizeShape();
    return visualizedCard;

  }

  // Visualizes a cards' count as a string
  private String visualizeCount() {
    String visualizedCount = "";
    if (this == null) {
      throw new IllegalArgumentException("The card is null");
    }

    if (this.getCount() == Counts.One) {
      visualizedCount = visualizedCount + "1";
      return visualizedCount;
    } else if (this.getCount() == Counts.Two) {
      visualizedCount = visualizedCount + "2";
      return visualizedCount;
    } else if (this.getCount() == Counts.Three) {
      visualizedCount = visualizedCount + "3";
      return visualizedCount;
    }
    throw new IllegalArgumentException("There is something wrong with visualizing the count");
  }

  // Visualizes a cards' filling as a string
  private String visualizeFilling() {
    String visualizedFilling = "";
    if (this == null) {
      throw new IllegalArgumentException("The card is null");
    }

    if (this.getFilling() == Fillings.Empty) {
      visualizedFilling = visualizedFilling + "E";
      return visualizedFilling;
    } else if (this.getFilling() == Fillings.Full) {
      visualizedFilling = visualizedFilling + "F";
      return visualizedFilling;
    } else if (this.getFilling() == Fillings.Stripped) {
      visualizedFilling = visualizedFilling + "S";
      return visualizedFilling;
    }
    throw new IllegalArgumentException("There is something wrong with visualizing the filling");
  }

  // Visualizes a cards' shape as a string
  private String visualizeShape() {
    String visualizedShape = "";
    if (this == null) {
      throw new IllegalArgumentException("The card is null");
    }

    if (this.getShape() == Shapes.Diamond) {
      visualizedShape = visualizedShape + "D";
      return visualizedShape;
    } else if (this.getShape() == Shapes.Oval) {
      visualizedShape = visualizedShape + "O";
      return visualizedShape;
    } else if (this.getShape() == Shapes.Squiggle) {
      visualizedShape = visualizedShape + "Q";
      return visualizedShape;
    }
    throw new IllegalArgumentException("There is something wrong with visualizing the shape");
  }

}
