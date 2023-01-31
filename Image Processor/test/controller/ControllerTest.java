package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import model.IModel;
import model.Image;
import model.MockModelImpl;
import model.ModelImpl;
import model.Pixel;
import view.IView;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 * To test the Controller aspect of our Image Processor Program. We utilize a mock model to
 * analyze the data that is transmitted between the controller and the program model, and
 * ensure that our data is being passed correctly with our tests.
 */
public class ControllerTest {
  private Pixel pixel1 = new Pixel(0, 0, new int[]{0, 0, 0});
  private Pixel pixel2 = new Pixel(0, 1, new int[]{10, 0, 0});
  private Pixel pixel3 = new Pixel(0, 2, new int[]{0, 10, 0});
  private Pixel pixel4 = new Pixel(0, 3, new int[]{0, 0, 10});
  private Pixel pixel5 = new Pixel(1, 0, new int[]{10, 150, 50});
  private Pixel pixel6 = new Pixel(1, 1, new int[]{150, 0, 50});
  private Pixel pixel7 = new Pixel(1, 2, new int[]{0, 150, 150});
  private Pixel pixel8 = new Pixel(1, 3, new int[]{10, 10, 10});
  private Pixel pixel9 = new Pixel(2, 0, new int[]{30, 130, 90});

  ArrayList<Pixel> firstRow;
  ArrayList<Pixel> secondRow;
  ArrayList<Pixel> thirdRow;
  ArrayList<ArrayList<Pixel>> pixelRows;
  ArrayList<ArrayList<Pixel>> secondPixelRows;

  Readable userInput;
  Appendable exAppendable = new StringBuilder();

  IController exController;
  Image exImage;
  Image exImage2;
  Image cherryImage;
  IView exView;
  HashMap<String, Image> exAltered;
  HashMap<String, Image> cherryList;
  IModel exModel;
  IModel cherryModel;

  // To initialize data for testing.
  private void initData() throws FileNotFoundException {
    this.firstRow = new ArrayList<>();
    this.secondRow = new ArrayList<>();
    this.thirdRow = new ArrayList<>();
    this.pixelRows = new ArrayList<>();
    this.secondPixelRows = new ArrayList<>();

    // initialize rows of pixels
    this.firstRow.add(pixel1);
    this.firstRow.add(pixel2);
    this.firstRow.add(pixel3);
    this.secondRow.add(pixel4);
    this.secondRow.add(pixel5);
    this.secondRow.add(pixel6);
    this.thirdRow.add(pixel7);
    this.thirdRow.add(pixel8);
    this.thirdRow.add(pixel9);

    // list of rows
    this.pixelRows.add(this.firstRow);
    this.pixelRows.add(this.secondRow);
    this.pixelRows.add(this.thirdRow);
    this.secondPixelRows.add(this.secondRow);
    this.secondPixelRows.add(this.thirdRow);
    this.secondPixelRows.add(this.firstRow);

    this.exImage = new Image(this.pixelRows, "Example Image");
    this.exImage2 = new Image(this.secondPixelRows, "Second Example Image");
    this.cherryImage = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");

    this.exAltered = new HashMap<>();
    this.exAltered.put(this.exImage.getTitle(), this.exImage);
    this.exAltered.put(this.exImage2.getTitle(), this.exImage2);

    this.cherryList = new HashMap<>();
    this.cherryList.put("cherry", this.cherryImage);

    this.exModel = new ModelImpl(this.exAltered);
    this.cherryModel = new ModelImpl(this.cherryList);
  }

  // test incorrect command
  @org.junit.Test
  public void testCommandException() throws IOException {
    this.initData();

    this.userInput = new StringReader("incorrectInput exampleImage.ppm mockTestFile Q");
    this.exView = new View(this.exModel, exAppendable);
    this.exController = new ControllerImpl(this.exModel, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("Welcome to the Image Processor program!\r\n" +
                    "Supported user instructions are: \r\n" +
                    "load image-path image-name: Load an image" +
                    " from the specified path and refer it to henceforth " +
                    "in the program by the given image name.\r\n" +
                    "save image-path image-name: Save the image with the " +
                    "given name to the specified path which should include" +
                    " the name of the file.\r\n" +
                    "horizontal-flip image-name dest-image-name: Flip " +
                    "an image horizontally to create a new image, referred " +
                    "to henceforth by the given destination name.\r\n" +
                    "vertical-flip image-name dest-image-name: Flip an" +
                    " image vertically to create a new image, referred " +
                    "to henceforth by the given destination name.\r\n" +
                    "brighten increment image-name dest-image-name:" +
                    " brighten the image by the given increment to" +
                    " create a new image, referred to henceforth by" +
                    " the given destination name. The increment may be positive " +
                    "(brightening) or negative (darkening)\r\n" +
                    "greyscale image-name component-type dest-image-name:" +
                    " Create a greyscale image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include red, green, blue, value, luma, " +
                    "intensity components in component field.\r\n" +
                    "filter image-name filter-type dest-image-name: " +
                    "Create a filter image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include sharpen or blur components in component field.\r\n" +
                    "colorTransformation image-name colorTransformation-type dest-image-name:" +
                    " Create a greyscale image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include luma or sepia components in component field.\r\n" +
                    "q or quit (quit the program) \r\n" +
                    "Not enough inputs. ",
            this.exAppendable.toString());
  }

  // test running the script commands
  @org.junit.Test
  public void testRunScript() throws IOException {
    this.initData();

    this.userInput = new StringReader("flip cherry vertical vertical-flip-cherry\n" +
            "save ./res/cherry/vertical-flip-cherry vertical-flip-cherry\n" +
            "\n" +
            "flip cherry horizontal horizontal-flip-cherry\n" +
            "save ./res/cherry/horizontal-flip-cherry horizontal-flip-cherry\n" +
            "\n" +
            "brighten cherry 20 brighten-20-cherry\n" +
            "save ./res/cherry/brighten-20-cherry brighten-20-cherry\n" +
            "\n" +
            "darken cherry -20 darken-(20)-cherry\n" +
            "save ./res/cherry/darken-(20)-cherry darken-(20)-cherry\n" +
            "\n" +
            "greyscale cherry red greyscale-red-cherry\n" +
            "save ./res/cherry/greyscale-red-cherry greyscale-red-cherry\n" +
            "\n" +
            "greyscale cherry green greyscale-green-cherry\n" +
            "save ./res/cherry/greyscale-green-cherry greyscale-green-cherry\n" +
            "\n" +
            "greyscale cherry blue greyscale-blue-cherry\n" +
            "save ./res/cherry/greyscale-blue-cherry greyscale-blue-cherry\n" +
            "\n" +
            "greyscale cherry value greyscale-value-cherry\n" +
            "save ./res/cherry/greyscale-value-cherry greyscale-value-cherry\n" +
            "\n" +
            "greyscale cherry intensity greyscale-intensity-cherry\n" +
            "save ./res/cherry/greyscale-intensity-cherry greyscale-intensity-cherry\n" +
            "\n" +
            "greyscale cherry luma greyscale-luma-cherry\n" +
            "save ./res/cherry/greyscale-luma-cherry greyscale-luma-cherry\n" +
            "\n" +
            "filter cherry sharpen sharpen-cherry" +
            "save ./res/cherry/sharpen-cherry sharpen-cherry" +
            "\n" +
            "filter cherry blur blur-cherry" +
            "save ./res/cherry/blur-cherry blur-cherry" +
            "\n" +
            "colorTransformation cherry sepia sepia-cherry" +
            "save ./res/cherry/sepia-cherry sepia-cherry" +
            "\n" +
            "colorTransformation cherry luma luma-cherry" +
            "save ./res/cherry/luma-cherry luma-cherry" +
            "\n" +
            "load ./res/cherry/cherry.ppm cherry\n" +
            "\n" +
            "save ./red/cherry/cherryCopy.ppm cherryCopy");
    this.exView = new View(this.cherryModel, exAppendable);
    this.exController = new ControllerImpl(this.cherryModel, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("Welcome to the Image Processor program!\r\n" +
                    "Supported user instructions are: \r\n" +
                    "load image-path image-name: Load an image" +
                    " from the specified path and refer it to henceforth " +
                    "in the program by the given image name.\r\n" +
                    "save image-path image-name: Save the image with the " +
                    "given name to the specified path which should include" +
                    " the name of the file.\r\n" +
                    "horizontal-flip image-name dest-image-name: Flip " +
                    "an image horizontally to create a new image, referred " +
                    "to henceforth by the given destination name.\r\n" +
                    "vertical-flip image-name dest-image-name: Flip an" +
                    " image vertically to create a new image, referred " +
                    "to henceforth by the given destination name.\r\n" +
                    "brighten increment image-name dest-image-name:" +
                    " brighten the image by the given increment to" +
                    " create a new image, referred to henceforth by" +
                    " the given destination name. The increment may be positive " +
                    "(brightening) or negative (darkening)\r\n" +
                    "greyscale image-name component-type dest-image-name:" +
                    " Create a greyscale image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include red, green, blue, value, luma, " +
                    "intensity components in component field.\r\n" +
                    "filter image-name filter-type dest-image-name: " +
                    "Create a filter image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include sharpen or blur components in component field.\r\n" +
                    "colorTransformation image-name colorTransformation-type dest-image-name:" +
                    " Create a greyscale image with a given component of the image, " +
                    " with the given name, and the given destination name. " +
                    "Can include luma or sepia components in component field.\r\n" +
                    "q or quit (quit the program) \r\n" +
                    "Not enough inputs. ",
            this.exAppendable.toString());
  }

  // Test to see if the controller is properly passing inputs from the brighten command.
  @org.junit.Test
  public void mockTest3() throws IOException {
    this.initData();
    StringBuilder log = new StringBuilder();
    IModel exampleMock = new MockModelImpl(log);
    this.userInput = new StringReader("brighten savedImage.ppm -10 newSavedImage.ppm Q");
    this.exView = new View(exampleMock, exAppendable);
    this.exController = new ControllerImpl(exampleMock, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("ImageName: savedImage.ppm AmountBrightened: -10 " +
                    "AlteredImageName: newSavedImage.ppm",
            log.toString());
  }

  // Test to see if the controller is properly passing inputs from the brighten command.
  @org.junit.Test
  public void mockTest4() throws IOException {
    this.initData();
    StringBuilder log = new StringBuilder();
    IModel exampleMock = new MockModelImpl(log);
    this.userInput = new StringReader("brighten savedImage.ppm 10 newSavedImage.ppm Q");
    this.exView = new View(exampleMock, exAppendable);
    this.exController = new ControllerImpl(exampleMock, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("ImageName: savedImage.ppm AmountBrightened: 10 " +
                    "AlteredImageName: newSavedImage.ppm",
            log.toString());
  }

  // Test to see if the controller is properly passing inputs from the mosaic command
  @org.junit.Test
  public void mockMosaicTest() throws IOException {
    this.initData();
    StringBuilder log = new StringBuilder();
    IModel exampleMock = new MockModelImpl(log);
    this.userInput = new StringReader("mosaic savedImage.ppm 10 newSavedImage.ppm Q");
    this.exView = new View(exampleMock, exAppendable);
    this.exController = new ControllerImpl(exampleMock, this.exView, this.userInput);
    this.exController.playCommand();
    assertEquals("ImageName: savedImage.ppm AmountMosaicked: 10 " +
                    "AlteredImageName: newSavedImage.ppm",
            log.toString());
  }

  // Test Script for Assignment 5
  @org.junit.Test
  public void mockTest5() throws IOException {
    this.initData();
    StringBuilder log = new StringBuilder();
    IModel exampleMock = new MockModelImpl(log);
    this.userInput = new StringReader("filter cherry.jpg sharpen sharpen-cherry Q");
    this.exView = new View(exampleMock, exAppendable);
    this.exController = new ControllerImpl(exampleMock, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("ImageName: cherry.jpg Filtering Type: sharpen" +
                    " AlteredImageName: sharpen-cherry",
            log.toString());
  }

  // Test Script for Assignment 5
  @org.junit.Test
  public void mockTest6() throws IOException {
    this.initData();
    StringBuilder log = new StringBuilder();
    IModel exampleMock = new MockModelImpl(log);
    this.userInput = new StringReader("filter cherry.jpg blur blur-cherry Q");
    this.exView = new View(exampleMock, exAppendable);
    this.exController = new ControllerImpl(exampleMock, this.exView, this.userInput);
    this.exController.playCommand();

    assertEquals("ImageName: cherry.jpg Filtering Type: blur" +
                    " AlteredImageName: blur-cherry",
            log.toString());
  }

}