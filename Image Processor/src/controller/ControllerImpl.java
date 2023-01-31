package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import model.IModel;
import view.IView;

/**
 * To represent the Controller Class of our Image Controller program. Takes in the user's inputs and
 * transmits them to other parts of the program to execute particular functions.
 */
public class ControllerImpl implements IController {
  private IModel model;
  private IView view;
  private Readable input;
  private HashMap<String, Function<ArrayList<String>, ICommand>> validCommands = new HashMap<>();

  /**
   * The constructor of the controller class, to create a new ControllerImpl for our program.
   *
   * @param model the Model of our Image.
   * @param view  the view to be displayed to the user.
   * @param input the input that the user passes into the program.
   * @throws IllegalArgumentException if any of the fields are null.
   */
  public ControllerImpl(IModel model, IView view, Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Argument(s) is null.");
    } else {
      this.model = model;
      this.view = view;
      this.input = input;

      // utilizes lambda expressions to bind an initialization to each String.
      // This is used because we cannot call a Command when we don't know the user's input yet.
      // So instead we create a Function that catches the user input and passes it to command.
      this.validCommands.put("load", userArgument -> new CommandLoadImpl(userArgument));
      this.validCommands.put("save", userArgument -> new CommandSaveImpl(userArgument));
      this.validCommands.put("flip", userArgument -> new CommandFlipImpl(userArgument));
      this.validCommands.put("brighten", userArgument ->
              new CommandBrightenOrDarkenImpl(userArgument));
      this.validCommands.put("greyscale", userArgument -> new CommandGreyscaleImpl(userArgument));
      this.validCommands.put("filter", userArgument -> new CommandFilterImpl(userArgument));
      this.validCommands.put("transform", userArgument ->
              new CommandColorTransformationImpl(userArgument));
      this.validCommands.put("mosaic", userArgument -> new CommandMosaic(userArgument));
    }
  }


  @Override
  public void playCommand() throws IllegalStateException, IOException {

    this.welcomeMessage();

    // continuously run our game with a loop, based on the user's input we call a
    // different implementation of the ICommand interface.
    boolean play = true;

    // stores the player input in a scanner.
    Scanner sc = new Scanner(this.input);

    // continuously runs the program.
    while (play) {
      ArrayList<String> arguments = new ArrayList<String>();
      // first argument. Should be command.

      // if user's input matches a Command, apply specified Command function to the rest of args.
      try {
        for (String s : this.validCommands.keySet()) {

          String firstArg = sc.next();

          if (firstArg.equals(s)) {
            if (firstArg.equals("load") || firstArg.equals("save")) {
              while (arguments.size() < 2) {
                arguments.add(sc.next());
              }
            } else {
              while (arguments.size() < 3) {
                arguments.add(sc.next());
              }
            }

            // get Lambda from the hashmap
            Function<ArrayList<String>, ICommand> func = this.validCommands.get(firstArg);

            // isolate ICommand and execute command on this.model.
            ICommand tempCommand = func.apply(arguments);
            tempCommand.command(this.model);

          } else if (firstArg.equals("Q") || firstArg.equals("q")
                  || firstArg.equals("quit")) {
            renderMessageHelper("Program Terminated.");
            play = false;
            break;
          } else { // if they don't input a valid command or quit message, then tell them re-enter.
            throw new NoSuchElementException("Invalid command: " + firstArg +
                    ". Please use only the " +
                    "commands that are provided above. ");
            //  break;
          }
        }
      } catch (NoSuchElementException e) {
        renderMessageHelper("Not enough inputs. ");
        break;
      }
    }
  }


  protected void printMenu() throws IllegalStateException {
    this.renderMessageHelper("Supported user instructions are: " + System.lineSeparator());
    this.renderMessageHelper("load image-path image-name: Load an image from" +
            " the specified path and refer it to henceforth" +
            " in the program by the given image name."
            + System.lineSeparator());
    this.renderMessageHelper("save image-path image-name:" +
            " Save the image with the given name to the specified path" +
            " which should include the name of the file."
            + System.lineSeparator());
    this.renderMessageHelper("horizontal-flip image-name dest-image-name: " +
            "Flip an image horizontally to create a new image," +
            " referred to henceforth by the given destination name." + System.lineSeparator());
    this.renderMessageHelper("vertical-flip image-name dest-image-name: " +
            "Flip an image vertically to create a new image, " +
            "referred to henceforth by the given destination name." + System.lineSeparator());
    this.renderMessageHelper("brighten increment image-name dest-image-name: " +
            "brighten the image by the given increment to create a new image, " +
            "referred to henceforth by the given destination name. " +
            "The increment may be positive (brightening)" +
            " or negative (darkening)" + System.lineSeparator());
    this.renderMessageHelper("greyscale image-name component-type dest-image-name: " +
            "Create a greyscale image with a given component of the image, " +
            " with the given name, and the given destination name." +
            " Can include red, green, blue, value," +
            " luma, intensity components in component field." + System.lineSeparator());
    this.renderMessageHelper("filter image-name filter-type dest-image-name: " +
            "Create a filter image with a given component of the image, " +
            " with the given name, and the given destination name." +
            " Can include sharpen or blur components in component field." + System.lineSeparator());
    this.renderMessageHelper("colorTransformation image-name colorTransformation-type " +
            "dest-image-name: " +
            "Create a greyscale image with a given component of the image, " +
            " with the given name, and the given destination name." +
            " Can include luma or sepia components in component field." + System.lineSeparator());
    this.renderMessageHelper("mosaic seed-num image-name" +
            "dest-image-name: " +
            "Create a mosaicked image with a given seed number of the image, " +
            " with the given name, and the given destination name." + System.lineSeparator());
    this.renderMessageHelper("q or quit (quit the program) " + System.lineSeparator());

  }

  /**
   * To delegate the operation of rendering a message outside the main playCommand method,
   * protecting code against IOException thrown by the message.
   *
   * @param message The message to be printed out and displayed to the user.
   */
  private void renderMessageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering the message.");
    }
  }

  protected void welcomeMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Welcome to the Image Processor program!" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Error transmitting message to Menu.");
    }
    printMenu();
  }
}
