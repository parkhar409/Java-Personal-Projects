import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw03.AbstractSetGameModel;
import cs3500.set.view.SetGameTextView;

/**
 * Main class that allows one to actually play the game using terminal.
 */
public final class SetGame {

  /**
   * Constructor for the main which utilizes all three classes
   * Controller, Model, View.
   * @param args inputs typed in by the users.
   */
  public static void main(String[] args) {
    SetGameModel model = new AbstractSetGameModel() {
    };
    SetGameController controller = new SetGameControllerImpl(model,
            new SetGameTextView(model, System.out),
            new InputStreamReader(System.in));
    controller.playGame();
  }
}
