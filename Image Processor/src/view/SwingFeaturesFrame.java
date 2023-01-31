package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerImpl;
import controller.IController;
import controller.CommandLoadImpl;
import controller.Utils;
import model.IModel;
import model.Image;

/**
 * To represent a Class that supports a variety of Java Swing features in our program.
 * These features will be utilized to construct a usable GUI for our Image Processor app.
 * Supported features will include the Load command, the Save command, and various other image
 * manipulation commands such as brighten and greyscale. We will have these commands in a
 * JPanel to the left side of our Frame, while we have our before/after images on the right
 * side of the frame, along with histograms and a history of saved images that will be
 * stacked top bottom.
 */
public class SwingFeaturesFrame extends JFrame implements
        ActionListener, IView {
  private final IModel model;
  private CommandLoadImpl loadCommand;
  private JPasswordField pfield;
  private JButton pButton;
  private JLabel textDisplay;
  final JPanel mainPanel;
  private final JPanel histogramPanel;
  final JScrollPane mainScrollPane;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  private final JLabel inputDisplay;
  private final JLabel optionDisplay;
  final JLabel fileBlurDisplay;
  final JLabel fileSharpenDisplay;
  final JLabel fileSepia;
  final JLabel fileLuma;
  final JLabel fileVertical;
  final JLabel fileHorizontal;
  private final JLabel imgLabel;
  final JPanel imagePanel;

  private Histogram before;
  Histogram after;

  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;
  final HashMap<String, Image> alteredImages;

  /**
   * To construct a new Java SwingFeaturesFrame object that will feature a title, size, and
   * various buttons and lists that will make up our graphical user interface.
   */
  public SwingFeaturesFrame(IModel model) {
    super();
    this.model = model;
    setTitle("Image Processing Application");
    setSize(800, 400);

    // To represent the main panel where all of our objects will be overlaid upon.
    // "The Background" of our app.
    mainPanel = new JPanel();

    // For elements to be arranged from left to right within this panel.
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    // Gives Scroll Bars to our main app panel.
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Gets the current alteredImages of our model.
    this.alteredImages = model.getAlteredImage();

    // TODO: LIST OF PANELS AND LABELS TO BE IMPLEMENTED IN ORDER OF IMPORTANCE.
    //
    // 1. Buttons JPanel (Y_AXIS LAYOUT) -- will have our commands for images.
    //    -> will have Buttons that each send command Strings to our controller to dictate what
    //       actions to take.
    //
    // 2. Images JPanel (Y_AXIS LAYOUT) -- look at ImagePanel in demo for reference.
    //    -> will have JPanel for Before Img. Left to Right with HISTOGRAM 1.
    //    -> will have JPanel for After Img. Left to Right with HISTOGRAM 2.
    //    -> will have JPanel for log of Saved Images underneath all with links to prev. images.

    // Box with our commands listed as clickable BUTTONS from top to bottom.
    JPanel commandPanel = new JPanel();
    commandPanel.setBorder(BorderFactory.createTitledBorder("Image Commands"));
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
    mainPanel.add(commandPanel);

    // To load a new image file in our program.
    JPanel loadFile = new JPanel();
    loadFile.setLayout(new FlowLayout());
    commandPanel.add(loadFile);
    JButton fileLoadButton = new JButton("Load a file");
    fileLoadButton.setActionCommand("Load file");
    fileLoadButton.addActionListener(this);
    commandPanel.add(fileLoadButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    commandPanel.add(fileOpenDisplay);

    // To save the current image file with a specified name in our program.
    JPanel saveImage = new JPanel();
    saveImage.setLayout(new FlowLayout());
    commandPanel.add(saveImage);
    JButton fileSaveButton = new JButton("Save an Image");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    commandPanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    commandPanel.add(fileSaveDisplay);

    // To BLUR our current image in our program.
    JPanel blurImage = new JPanel();
    blurImage.setLayout(new FlowLayout());
    commandPanel.add(blurImage);
    JButton blurButton = new JButton("Blur an Image");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);
    commandPanel.add(blurButton);
    fileBlurDisplay = new JLabel("Blur Image");
    commandPanel.add(fileBlurDisplay);

    // To SHARPEN our current image in our program.
    JPanel sharpenImage = new JPanel();
    sharpenImage.setLayout(new FlowLayout());
    commandPanel.add(sharpenImage);
    JButton sharpenButton = new JButton("Sharpen an Image");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);
    commandPanel.add(sharpenButton);
    fileSharpenDisplay = new JLabel("Sharpen Image");
    commandPanel.add(fileSharpenDisplay);

    // To SEPIA our current image in our program.
    JPanel sepiaImage = new JPanel();
    sepiaImage.setLayout(new FlowLayout());
    commandPanel.add(sepiaImage);
    JButton sepiaButton = new JButton("Set Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);
    commandPanel.add(sepiaButton);
    fileSepia = new JLabel("Sepia Image");
    commandPanel.add(fileSepia);

    // To LUMA our current image in our program.
    JPanel lumaImage = new JPanel();
    lumaImage.setLayout(new FlowLayout());
    commandPanel.add(lumaImage);
    JButton lumaButton = new JButton("Set Luma");
    lumaButton.setActionCommand("Luma");
    lumaButton.addActionListener(this);
    commandPanel.add(lumaButton);
    fileLuma = new JLabel("Luma Image");
    commandPanel.add(fileLuma);

    // To Vertical Flip our current image in our program.
    JPanel verticalImage = new JPanel();
    verticalImage.setLayout(new FlowLayout());
    commandPanel.add(verticalImage);
    JButton verticalButton = new JButton("Flip Vertical");
    verticalButton.setActionCommand("Vertical");
    verticalButton.addActionListener(this);
    commandPanel.add(verticalButton);
    fileVertical = new JLabel("Vertical Image");
    commandPanel.add(fileVertical);

    // To Horizontal Flip our current image in our program.
    JPanel horizontalImage = new JPanel();
    horizontalImage.setLayout(new FlowLayout());
    commandPanel.add(horizontalImage);
    JButton horizontalButton = new JButton("Flip Horizontal");
    horizontalButton.setActionCommand("Horizontal");
    horizontalButton.addActionListener(this);
    commandPanel.add(horizontalButton);
    fileHorizontal = new JLabel("Horizontal Image");
    commandPanel.add(fileHorizontal);

    // To input the brightness by which we want to modify the brightness
    JPanel brightenOrDarken = new JPanel();
    brightenOrDarken.setLayout(new FlowLayout());
    commandPanel.add(brightenOrDarken);

    JButton inputButton = new JButton("Brighten/Darken");
    inputButton.setActionCommand("Input");
    inputButton.addActionListener(this);
    commandPanel.add(inputButton);

    inputDisplay = new JLabel("Default");
    commandPanel.add(inputDisplay);

    // To input the mosaic by which we want to modify the image
    JPanel mosaic = new JPanel();
    mosaic.setLayout(new FlowLayout());
    commandPanel.add(mosaic);

    JButton input = new JButton("Mosaic");
    input.setActionCommand("Input");
    input.addActionListener(this);
    commandPanel.add(input);

    JLabel inputD = new JLabel("Default");
    commandPanel.add(inputD);

    // To indicate what color the user wants to greyscale the image with (RGB).
    JPanel greyScaleOptions = new JPanel();
    greyScaleOptions.setLayout(new FlowLayout());
    commandPanel.add(greyScaleOptions);

    JButton optionButton = new JButton("Greyscale");
    optionButton.setActionCommand("Option");
    optionButton.addActionListener(this);
    greyScaleOptions.add(optionButton);


    optionDisplay = new JLabel("Default");
    greyScaleOptions.add(optionDisplay);

    //show an image with a scrollbar
    imgLabel = new JLabel();
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.add(imgLabel);
    //imagePanel.setMaximumSize(null);

    mainPanel.add(imagePanel);

    // creating a histogram panel
    histogramPanel = new JPanel();
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.Y_AXIS));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("RGB Histogram of Image"));
    histogramPanel.setPreferredSize(new Dimension(200, 200));
    this.before = new Histogram(new ArrayList<>());
    this.after = new Histogram(new ArrayList<>());
    histogramPanel.add(this.before);
    histogramPanel.add(this.after);
    imagePanel.add(histogramPanel);
  }


  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO Auto-generated method stub
    switch (event.getActionCommand()) {
      case "Load file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "ppm", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          try {
            File f = fchooser.getSelectedFile();
            BufferedImage image = ImageIO.read(f);
            imgLabel.setIcon(new ImageIcon(image));
            fileOpenDisplay.setText(f.getAbsolutePath());

            ArrayList<String> load =
                    new ArrayList<String>(Arrays.asList(f.getPath()));
            System.out.println(load.get(0));
            //System.out.println(load.get(1));
            loadCommand = new CommandLoadImpl(load);
            this.setNewLoadCommand(loadCommand);
            loadCommand.command(model);

            IController controller = new ControllerImpl(model, this,
                    new StringReader(loadCommand.toString()));
            controller.playCommand();

            this.before = new Histogram(Utils.readFile(f.getPath()));
            histogramPanel.add(this.before);
            before.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.before.paintComponents(histogramPanel.getGraphics());

            this.after = new Histogram(Utils.readFile(f.getAbsolutePath()));
            after.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            histogramPanel.add(this.after);


          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;

      case "Input":
        inputDisplay.setText(JOptionPane.showInputDialog("Positive int = Brighten ," +
                " Negative int = Darken"));
        break;
      case "Option": {
        String[] options = {"Red", "Green", "Blue"};
        int retvalue = JOptionPane.showOptionDialog(SwingFeaturesFrame.this,
                "Please choose color to greyscale with", "Options",
                JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
        optionDisplay.setText(options[retvalue]);
      }
      break;
      default:
        try {
          this.renderMessage("Unable to perform.");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
    }
    revalidate();
  }

  public void setNewLoadCommand(CommandLoadImpl cmd) {
    this.loadCommand = cmd;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.textDisplay.setText(message);
  }
}

