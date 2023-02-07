import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

//to represent a Cube
class Cube {
  int x;
  int y;
  ArrayList<Edge> pathway;
  static final int SIZE = 10;
  
  Cube(int x, int y) {
    this.x = x;
    this.y = y;
    this.pathway = new ArrayList<Edge>();
  }
  
  // to check if Cube is equal to another Object
  public boolean equals(Object other) {
    if (!(other instanceof Cube)) {
      return false;
    } else {
      Cube c = (Cube) other;
      return (this.x == c.x && this.y == c.y);
    }
  }
  
  public int hashCode() {
    return this.x + this.y;
  }
  
  // to see if Cube is in given hashmap
  Cube locate(HashMap<Cube, Cube> h) {
    if (this.equals(h.get(this))) {
      return this;
    } else {
      return h.get(this).locate(h);
    }
  }
  
  // makes the pathway
  public void makeConnect(ArrayList<Edge> edges) {
    for (Edge e: edges) {
      if (e.c1.equals(this)) {
        this.pathway.add(e);
      }
      if (e.c2.equals(this)) {
        Edge temp = new Edge(e.weight, e.c2, e.c1);
        this.pathway.add(temp);
      }
    }
  }
}

//to represent an option a player has
class Option {
  Cube cube;
  
  Option(Cube cube) {
    this.cube = cube;
  }
}

//to represent the Stack
class Stack<T> {
  ArrayList<T> list;
  
  Stack(ArrayList<T> list) {
    this.list = list;
  }

  public void add(T t) {
    list.add(0, t);
  }

  public T remove() {
    T result = list.get(0);
    list.remove(0);
    return result;
  }
}

class Deque<T> {
  ArrayList<T> list;
  
  Deque(ArrayList<T> list) {
    this.list = list;
  }

  public void add(T t) {
    list.add(list.size(), t);
  }

  public T remove() {
    T result = list.get(0);
    list.remove(0);
    return result;
  }
}

class Edge implements Comparator<Edge> , Comparable<Edge> {
  Integer weight;
  Cube c1;
  Cube c2;
  
  Edge(Cube c1, Cube c2) {
    this.c1 = c1;
    this.c2 = c2;
    Random r = new Random();
    weight = r.nextInt(100);
  }

  Edge(Integer weight, Cube c1, Cube c2) {
    this.weight = weight;
    this.c1 = c1;
    this.c2 = c2;
  }

  public int compare(Edge e1, Edge e2) {
    return e1.weight - e2.weight;
  }

  public int compareTo(Edge e) {
    return this.weight.compareTo(e.weight);
  }

  public boolean equals(Object that) {
    if (!(that instanceof Edge)) {
      return false;
    } else {
      Edge e = (Edge) that;
      return ((this.c1.equals(e.c1) && this.c2.equals(e.c2)) ||
        this.c1.equals(e.c2) && this.c2.equals(e.c1));
    }
  }
  
  // to draw the edge
  WorldImage drawEdge() {
    if (this.c1.x == this.c2.x) {
      return new RectangleImage(10, 1, OutlineMode.SOLID, Color.BLACK);
    } else {
      return new RectangleImage(1, 10, OutlineMode.SOLID, Color.BLACK);
    }
  }
}

// to represent the World
class MazeWorld extends World {
  static final int width = 64;
  static final int height = 60;
  boolean depth;
  boolean breadth;
  Option option = new Option(new Cube(0, 0));
  ArrayList<ArrayList<Cube>> grid = new ArrayList<ArrayList<Cube>>();
  ArrayList<Cube> next = new ArrayList<Cube>();
  ArrayList<Edge> edges = new ArrayList<Edge>();
  ArrayList<Edge> walls = new ArrayList<Edge>();
  HashMap<Cube, Cube> repre = new HashMap<Cube, Cube>();
  Stack<Edge> dList = new Stack<Edge>(new ArrayList<Edge>());
  Deque<Edge> bList = new Deque<Edge>(new ArrayList<Edge>());
  
  MazeWorld(int width, int height) {
    this.reset(this.width * Cube.SIZE, this.height * Cube.SIZE);
  }
  
  public boolean addHelp(int a, int b, int pred) {
    return grid.get(a).get(b).x != pred;
  }

  public boolean addHelp2(int a, int b, int pred) {
    return grid.get(a).get(b).y != pred;
  }
  
  void reset(int width, int height) {
    option = new Option(new Cube(0, 0));
    grid = new ArrayList<ArrayList<Cube>>();
    repre = new HashMap<Cube, Cube>();
    next = new ArrayList<Cube>();

    edges = new ArrayList<Edge>();
    walls = new ArrayList<Edge>();

    breadth = false;
    depth = false;
    dList = new Stack<Edge>(new ArrayList<Edge>());
    bList = new Deque<Edge>(new ArrayList<Edge>());

    for (int a = 0; a <= this.width; a += 1) {
      ArrayList<Cube> row = new ArrayList<Cube>();
      for (int b = 0; b <= this.height; b += 1) {
        row.add(new Cube(a, b));
      }
      grid.add(row);
    }
    for (int a = 0; a <= this.width; a += 1) {
      for (int b = 0; b <= this.height; b += 1) {
        if (addHelp(a, b, this.width)) {
          edges.add(new Edge(grid.get(a).get(b), grid.get(a + 1).get(b)));
        }
        if (addHelp2(a, b, this.height)) {
          edges.add(new Edge(grid.get(a).get(b), grid.get(a).get((b + 1))));
        }
      }

    }
    Collections.sort(edges);
    for (int a = 0; a <= this.width; a += 1) {
      for (int b = 0; b <= this.height; b += 1) {
        repre.put(grid.get(a).get(b), grid.get(a).get(b));
      }
    }
    this.kruk();
    for (ArrayList<Cube> row: grid) {
      for (Cube c: row) {
        c.makeConnect(this.edges);
      }
    }
    option.cube = grid.get(0).get(0);
  }

  void kruk() {

    ArrayList<Edge> workList = new ArrayList<Edge>();
    workList.addAll(edges);
    edges.clear();
    while (workList.size() > 1) {
      Edge e1 = workList.get(0);
      if (e1.c1.locate(repre).equals(e1.c2.locate(repre))) {
        workList.remove(0);
        this.walls.add(e1);
      } else {
        this.edges.add(e1);
        union(repre, e1.c1.locate(repre), e1.c2.locate(repre));
        workList.remove(0);
      }
    }
  }

  void union(HashMap<Cube, Cube> h, Cube c1, Cube c2) {
    h.put(c1, c2);
  }

  public void updateOption(String ke) {
    if (ke.equals("r")) {
      this.reset(this.width * Cube.SIZE, this.height * Cube.SIZE);
    }
  }

  public void onKeyEvent(String ke) {
    updateOption(ke);
  }

  public WorldScene makeScene() {
    WorldScene game = new WorldScene(Cube.SIZE * (this.width + 1),
        Cube.SIZE * (this.height + 1));

    for (Edge e: walls) {
      game.placeImageXY(e.drawEdge(), ((e.c1.x + e.c2.x) / 2 + 1 / 2) * Cube.SIZE,
          ((e.c1.y + e.c2.y) / 2 + 1 / 2) * Cube.SIZE);
    }
    return game;
  }
}

// Examples Class
class ExamplesWorld {
  Cube c1 = new Cube(1, 1);
  Cube c2 = new Cube(1, 1);
  Cube c3 = new Cube(1, 2);
  Cube c4 = new Cube(1000, 1000);

  Edge e1 = new Edge(1, c1, c3);
  Edge e2 = new Edge(2, c3, c1);
  Edge e3 = new Edge(3, c2, c1);
  MazeWorld m1 = new MazeWorld(50, 50);

  boolean testSameCell(Tester t) {
    return t.checkExpect(c1.equals(c2), true) &&
      t.checkExpect(c1.equals(c4), false);
  }
  
  boolean testSameEdge(Tester t) {
    return t.checkExpect(e1.equals(e1), true) &&
      t.checkExpect(e1.equals(e2), false);
  }
  
  boolean testCompare(Tester t) {
    return t.checkExpect(e1.compare(e2, e1), 1);
  }
  
  boolean testCompareto(Tester t) {
    return t.checkExpect(e1.compareTo(e2), -1);
  }
  
  boolean testdrawEdge(Tester t) {
    return t.checkExpect(e2.drawEdge(),
      new RectangleImage(10, 1, OutlineMode.SOLID, Color.BLACK));
  }
  
  boolean testHashCode(Tester t) {
    return t.checkExpect(c1.hashCode(), 2);
  }
  
  void testGmae(Tester t) {
    this.m1.bigBang(Cube.SIZE * m1.width + 10, Cube.SIZE * m1.height + 10, 0.01);
  }
}