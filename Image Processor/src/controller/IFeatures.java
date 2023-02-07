package controller;

import java.awt.event.ActionEvent;

/**
 * this is the interface which tells the view which actions to
 * perform when the action listener is called. it deals with
 * the new methods we implement in our controller for the gui.
 */
public interface IFeatures {
  void actionPerformed(ActionEvent e);

  void start2();
}
