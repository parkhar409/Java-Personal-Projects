package view;

import java.awt.event.ActionListener;

import model.Image;

/**
 * Ths represents the view interface which helps display the images.
 */
public interface IView {
  void setListener(ActionListener listener);

  String getInput();

  void displayImage(Image image);

  void showFileChooser();

  void saveFile();

  void display();
}
