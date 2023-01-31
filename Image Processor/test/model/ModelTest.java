package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.Utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * To represent a class of tests for our model.
 */
public class ModelTest {
  private Pixel pixel1;
  private Pixel pixel2;
  private Pixel pixel3;
  Pixel invalidPixel1;
  Pixel invalidPixel2;
  Pixel invalidPixel3;
  Pixel invalidPixel4;
  Pixel invalidPixel5;
  Pixel invalidPixel6;

  private Image image1;
  private Image image2;
  private Image image3;
  Image invalidImage1;
  Image invalidImage2;
  Image invalidImage3;

  private ModelImpl model1;
  private ModelImpl model2;
  private ModelImpl model3;

  ArrayList<ArrayList<Pixel>> arrayListCopy1;
  ArrayList<ArrayList<Pixel>> arrayList1;
  ArrayList<Pixel> array1;
  ArrayList<Pixel> array2;

  @Before
  public void initData() {
    pixel1 = new Pixel(0, 0, new int[]{0, 0, 0});
    pixel2 = new Pixel(20, 20, new int[]{255, 255, 255});
    pixel3 = new Pixel(60, 20, new int[]{150, 50, 100});

    ArrayList<Pixel> array3 = new ArrayList<Pixel>();
    ArrayList<Pixel> array4 = new ArrayList<Pixel>();
    ArrayList<Pixel> array5 = new ArrayList<Pixel>();
    array3.add(new Pixel(0, 0, new int[]{1, 1, 1}));
    array3.add(new Pixel(1, 0, new int[]{2, 2, 2}));
    array3.add(new Pixel(2, 0, new int[]{3, 3, 3}));
    array4.add(new Pixel(0, 1, new int[]{4, 4, 4}));
    array4.add(new Pixel(1, 1, new int[]{5, 5, 5}));
    array4.add(new Pixel(2, 1, new int[]{6, 6, 6}));
    array5.add(new Pixel(0, 2, new int[]{7, 7, 7}));
    array5.add(new Pixel(1, 2, new int[]{8, 8, 8}));
    array5.add(new Pixel(2, 2, new int[]{9, 9, 9}));

    ArrayList<ArrayList<Pixel>> arrayList3 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<ArrayList<Pixel>> arrayList4 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<ArrayList<Pixel>> arrayList5 = new ArrayList<ArrayList<Pixel>>();
    arrayList3.add(array3);
    arrayList3.add(array4);
    arrayList3.add(array5);
    arrayList4.add(array3);
    arrayList4.add(array4);

    image1 = new Image(arrayList3, "test");
    image2 = new Image(arrayList4, "anothertest");

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPixelConstructor() {
    this.initData();
    invalidPixel1 = new Pixel(1, -1, new int[]{20, 20, 20});
    invalidPixel2 = new Pixel(-1, 1, new int[]{30, 30, 30});
    invalidPixel3 = new Pixel(-1, -1, new int[]{40, 40, 40});
    invalidPixel4 = new Pixel(1, 1, new int[]{-1, 20, 20});
    invalidPixel5 = new Pixel(1, 1, new int[]{256, 20, 20});
    invalidPixel6 = new Pixel(-1, 1, new int[]{-1, 20, 20});
  }

  @Test
  public void testSetXPosition() {
    this.initData();
    pixel1.setXPosition(50);
    assertEquals(pixel1.getXPosition(), 50);

    pixel2.setXPosition(5);
    assertEquals(pixel2.getXPosition(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetXPosition() {
    this.initData();
    pixel1.setXPosition(-50);
    pixel1.getXPosition();
  }

  @Test
  public void testSetYPosition() {
    this.initData();
    pixel1.setYPosition(50);
    assertEquals(pixel1.getYPosition(), 50);

    pixel2.setYPosition(5);
    assertEquals(pixel2.getYPosition(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetYPosition() {
    this.initData();
    pixel1.setYPosition(-50);
    pixel1.getYPosition();
  }

  @Test
  public void testGetColor() {
    this.initData();
    assertArrayEquals(pixel1.getColor(), new int[]{0, 0, 0});
    assertArrayEquals(pixel2.getColor(), new int[]{255, 255, 255});
  }

  @Test
  public void testSetColor() {
    this.initData();
    int[] color1 = new int[]{10, 10, 10};
    pixel1.setColor(color1);
    assertArrayEquals(pixel1.getColor(), color1);

    int[] color2 = new int[]{50, 20, 60};
    pixel2.setColor(color2);
    assertArrayEquals(pixel2.getColor(), color2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColor() {
    this.initData();
    int[] color = new int[]{-1, 20, 20};
    pixel1.setColor(color);
  }

  @Test
  public void testValue() {
    this.initData();
    assertEquals(pixel1.value(), 0);
    assertEquals(pixel2.value(), 255);
    assertEquals(pixel3.value(), 150);
  }

  @Test
  public void testIntensity() {
    this.initData();
    assertEquals(pixel1.intensity(), 0);
    assertEquals(pixel2.intensity(), 255);
    assertEquals(pixel3.intensity(), 100);
  }

  @Test
  public void testLuma() {
    this.initData();
    assertEquals(pixel1.luma(), 0);
    assertEquals(pixel2.luma(), 254);
    assertEquals(pixel3.luma(), 74);
  }

  @Test
  public void testImageConstructor() {
    this.initData();
    arrayList1 = new ArrayList<ArrayList<Pixel>>();
    array1 = new ArrayList<Pixel>();
    array2 = new ArrayList<Pixel>();

    array1.add(pixel1);
    array1.add(pixel2);

    array2.add(pixel2);
    array2.add(pixel3);

    arrayList1.add(array1);
    arrayList1.add(array2);

    assertEquals(arrayList1.size(), 2);
    assertEquals(arrayList1.get(0).get(0), pixel1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImageConstructor() {
    this.initData();
    arrayList1 = new ArrayList<ArrayList<Pixel>>();
    array1 = new ArrayList<Pixel>();
    array2 = new ArrayList<Pixel>();

    array1.add(pixel1);
    array1.add(pixel2);

    array2.add(pixel2);
    array2.add(pixel3);

    arrayList1.add(array1);
    arrayList1.add(array2);

    invalidImage1 = new Image(new ArrayList<ArrayList<Pixel>>(), "test");
    invalidImage2 = new Image(arrayList1, null);
    invalidImage3 = new Image(new ArrayList<ArrayList<Pixel>>(), null);
  }

  @Test
  public void testImageCopy() {
    this.initData();
    arrayList1 = new ArrayList<ArrayList<Pixel>>();
    array1 = new ArrayList<Pixel>();
    array2 = new ArrayList<Pixel>();

    array1.add(pixel1);
    array1.add(pixel2);

    array2.add(pixel2);
    array2.add(pixel3);

    arrayList1.add(array1);
    arrayList1.add(array2);

    arrayListCopy1 = new ArrayList<ArrayList<Pixel>>();
    arrayListCopy1 = image1.imageCopy();

    assertEquals(arrayListCopy1.get(0).get(0).getXPosition(), 0);
    assertEquals(arrayListCopy1.get(0).get(0).getYPosition(), 0);
    assertArrayEquals(arrayListCopy1.get(0).get(0).getColor(), new int[]{1, 1, 1});
  }

  @Test
  public void testGetPixel() {
    this.initData();
    assertEquals(image1.getPixel(0, 0).getXPosition(), 0);
    assertEquals(image1.getPixel(0, 0).getYPosition(), 0);
    assertArrayEquals(image1.getPixel(0, 0).getColor(), new int[]{1, 1, 1});
  }

  @Test
  public void testGetTitle() {
    this.initData();
    assertEquals(image1.getTitle(), "test");
    assertEquals(image2.getTitle(), "anothertest");
  }

  @Test
  public void testGetLength() {
    this.initData();
    assertEquals(image1.getLength(), 3);
    assertEquals(image2.getLength(), 2);
  }

  @Test
  public void testGetWidth() {
    this.initData();
    assertEquals(image1.getWidth(), 3);
    assertEquals(image2.getWidth(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelImpl() {
    this.initData();
    HashMap<String, Image> hashMapImages = null;
    model1 = new ModelImpl(hashMapImages);
  }

  @Test
  public void testFlipImage() throws IOException {
    HashMap<String, Image>  cherry = new HashMap<>();
    Image imageCherry = new Image(Utils.readPPM("11.1Homework5/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);
    model1.flipImage("cherry", "vertical", "vertical-flip-cherry");
    model1.saveImage("cherry", "11.1Homework5/res/cherry/vertical-cherry1.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);

    model1.flipImage("vertical-flip-cherry", "vertical",
            "vertical-cherry2");
    model1.saveImage("cherry", "11.1Homework5/res/cherry/vertical-cherry2.ppm");
    assertEquals(model1.getAlteredImage().size(), 3);

    model1.flipImage("vertical-cherry2", "horizontal",
            "horizontal-vertical-cherry2");
    model1.saveImage("cherry",
            "11.1Homework5/res/cherry/horizontal-vertical-cherry2.ppm");
    assertEquals(model1.getAlteredImage().size(), 4);

    model1.flipImage("horizontal-vertical-cherry2", "horizontal",
            "horizontal-vertical-cherry3");
    model1.saveImage("cherry",
            "11.1Homework5/res/cherry/horizontal-vertical-cherry3.ppm");
    assertEquals(model1.getAlteredImage().size(), 5);
  }

  @Test
  public void testBrightenOrDarkenImage() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);

    model1.brightenOrDarkenImage("cherry", 20, "brighten-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/brighten-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);

    model1.brightenOrDarkenImage("cherry", -20, "darken-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/darken-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 3);
  }

  @Test
  public void testGreyscaleImage() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);

    model1.greyscaleImage("cherry", "red", "red-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/red-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);
    model1.greyscaleImage("cherry", "green", "green-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/green-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 3);
    model1.greyscaleImage("cherry", "blue", "blue-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/blue-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 4);
    model1.greyscaleImage("cherry", "value", "value-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/value-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 5);
    model1.greyscaleImage("cherry", "intensity", "intensity-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/intensity-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 6);
  }

  @Test
  public void testFilterImage() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);

    model1.filter("cherry", "sharpen", "sharpen-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/sharpen-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);
    model1.filter("cherry", "blur", "blur-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/blur-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 3);
  }

  @Test
  public void testColorTransformationImage() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);

    model1.colorTransformation("cherry", "luma", "luma-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/luma-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);
    model1.colorTransformation("cherry", "sepia", "sepia-cherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/sepia-cherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 3);
  }

  @Test
  public void testMosaic() throws FileNotFoundException {
    HashMap<String, Image> cherry = new HashMap();
    Image imageCherry = new Image(Utils.readPPM("hw04v4/res/cherry/cherry.ppm"), "cherry");
    cherry.put("cherry", imageCherry);
    model1 = new ModelImpl(cherry);

    model1.mosaic(30, "cherry", "newCherry");
    model1.saveImage("cherry", "hw04v4/res/cherry/newCherry.ppm");
    assertEquals(model1.getAlteredImage().size(), 2);
  }

  @Test
  public void testLoadImage() throws IOException {
    HashMap<String, Image> koala = new HashMap<>();
    Image imageKoala = new Image(Utils.readPPM("hw04v4/res/koala/koala.ppm"), "koala");
    koala.put("koala", imageKoala);

    model1 = new ModelImpl(koala);
    model1.loadImage("koala", "hw04v4/res/koala/koala.ppm");
    model1.saveImage("koala", "hw04v4/res/koala/koalaTest.ppm");
    assertEquals(model1.getAlteredImage().size(), 1);

  }

  @Test
  public void testSaveImage() throws IOException {
    HashMap<String, Image> koala = new HashMap<>();
    Image imageKoala = new Image(Utils.readFile("hw04v4/res/koala/koala.ppm"), "koala");
    koala.put("koala", imageKoala);

    model1 = new ModelImpl(koala);
    model1.saveImage("koala", "hw04v4/res/koala/koala-test-save.ppm");
    assertEquals(model1.getAlteredImage().size(), 1);
  }

  // ASSIGNMENT 5:
  // TEST SAVING AND LOADING JPG FORMAT
  @Test
  public void testLoadJPG() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(
            Utils.readFile("hw04v4/res/cherry.jpg"), "cherry");
    cherry.put(imageCherry.getTitle(), imageCherry);

    model1 = new ModelImpl(cherry);
    model1.loadImage("cherry", "hw04v4/res/cherry.jpg");
    model1.saveImage("cherry", "hw04v4/res/cherry.jpg");
    assertEquals(model1.getAlteredImage().size(), 1);

  }

  @Test
  public void testSaveJPG() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(
            Utils.readFile("hw04v4/res/cherry.jpg"), "cherry");
    cherry.put(imageCherry.getTitle(), imageCherry);

    model1 = new ModelImpl(cherry);
    model1.saveImage("cherry", "hw04v4/res/cherry.jpg");
    assertEquals(model1.getAlteredImage().size(), 1);
  }

  @Test
  public void testLoadBMP() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(
            Utils.readFile("hw04v4/res/cherry.bmp"), "cherry");
    cherry.put(imageCherry.getTitle(), imageCherry);

    model1 = new ModelImpl(cherry);
    model1.loadImage("cherry", "hw04v4/res/cherry.bmp");
    model1.saveImage("cherry", "hw04v4/res/cherry.bmp");
    assertEquals(model1.getAlteredImage().size(), 1);

  }

  @Test
  public void testSaveBMP() throws IOException {
    HashMap<String, Image> cherry = new HashMap<>();
    Image imageCherry = new Image(
            Utils.readFile("hw04v4/res/cherry.bmp"), "cherry");
    cherry.put(imageCherry.getTitle(), imageCherry);

    model1 = new ModelImpl(cherry);
    model1.saveImage("cherry", "hw04v4/res/cherry.bmp");
    assertEquals(model1.getAlteredImage().size(), 1);

  }



}