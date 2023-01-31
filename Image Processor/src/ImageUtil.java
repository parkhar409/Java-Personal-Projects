import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.Image;
import model.ModelImpl;
import view.IView;
import view.SwingFeaturesFrame;
import view.View;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * This represents the main method.
   * @param args the arguments that will be taken in
   * @throws IOException when arguments are not correct
   */
  public static void main(String[] args) throws IOException {
    HashMap<String, Image> starterImages = new HashMap<>();

    IModel gameModel = new ModelImpl(starterImages);
    IView gameView = new View(gameModel, System.out);

    Scanner sc = null;

    // utilizes the GUI implementation in our SwingFeaturesFrame class.
    if (args.length == 0) {
      // TODO: CODE BELOW WAS REFERENCED FROM PROVIDED CODE FOR ASSIGNMENT
      SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
      SwingFeaturesFrame frame = new SwingFeaturesFrame(gameModel);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
               | IllegalAccessException ignored) {

      }
      { // supports the utilization of our programming with command line and scripts.

        IController initGameController = new ControllerImpl(
                gameModel, gameView, new StringReader(""));
        initGameController.playCommand();

        while (sc.hasNext()) {
          Appendable fullStr = new StringBuilder();
          for (String s : args) {
            try {
              fullStr.append(s);
            } catch (IOException e) {
              break;
            }
          }

          Readable r = new StringReader(fullStr.toString());
          IController gameController = new ControllerImpl(
                  gameModel, gameView, r);
          gameController.playCommand();
        }
      }
    }
  }

}

