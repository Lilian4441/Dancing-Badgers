import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;

public class DancingBadgers extends PApplet {
  
  private static processing.core.PImage backgroundImage; // background image
  
  private static int badgersCountMax; // max num of badger objects allowed in the basketball court
  
  private boolean danceShowOn; // tells whether the dance show is on
  
  private static Random randGen; // generator of random numbers
  
  private static ArrayList<Thing> things; // arraylist storing Thing objects
  
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
   }

  // array storing badgers dance show steps
  private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  
  // array storing the positions of the dancing badgers at the start of the dance show
  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};

  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Defines initial environment properties of this graphic application. This method initializes all
   * the data fields defined in this class.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
    this.textAlign(3, 3); // sets the alignment of the text
    this.imageMode(3); // interprets the x and y position of an image to its center
    this.focused = true; // confirms that this screen is "focused", meaning it is active and will 
    // accept mouse and keyboard input.
    
    // initializes the random number generator
    randGen = new Random(); 
    
    // load in background image
    backgroundImage = loadImage("images" + File.separator + "background.png"); 
    
    // sets max number of badgers to 5
    badgersCountMax = 5; 
    
    // initializes the things arraylist
    things = new ArrayList<>();
    
    Thing.setProcessing(this);
    
    // creates four Thing objects
    Thing object1 = new Thing(50, 50, "target.png");
    Thing object2 = new Thing(750, 550, "target.png");
    Thing object3 = new Thing(750, 50, "shoppingCounter.png");
    Thing object4 = new Thing(50, 550, "shoppingCounter.png");

    // adds new Thing objects to things ArrayList
    things.add(object1);
    things.add(object2);
    things.add(object3);
    things.add(object4);
    
    // creates two new StarshipRobot objects
    StarshipRobot robot1 = new StarshipRobot(object1, object3, 3);
    StarshipRobot robot2 = new StarshipRobot(object2, object4, 5);

    // adds new StarshipRobot objects to robots ArrayList
    things.add(robot1);
    things.add(robot2);
    
    for (int i = 0; i < things.size(); i++) {
      
      // checks if mouse is clicked and over a Clickable object
      if ((this.mousePressed == true) && (things.get(i).isMouseOver() == true) 
          && (things.get(i) instanceof Clickable)){
        mousePressed();
      }
      
      // checks if mouse is released and over a Clickable object
      else if ((this.mousePressed == false) && (things.get(i) instanceof Clickable)) {
        mouseReleased();
      }
    }

    
  }
  
  @Override
  public void draw() {
    
    // sets background to same color as previous version
    background(color(255, 218, 185));
    
    // draws background image to the center of the screen
    image(backgroundImage, width / 2, height / 2);
    
    // draws the four Thing objects
    for (Thing thing : things) {
      thing.draw();
    }
  }
  
  
  
 public int badgersCount() {
    int count = 0;
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Badger) {
        count++;
      }
    }
    return count;
  }
  
 @Override
  public void keyPressed() {
     // checks if b-key is pressed and danceShow is NOT on
    if (Character.toUpperCase(this.key) == 'B' && danceShowOn == false) {
      if (badgersCount() < badgersCountMax) {
      things.add(new Badger(randGen.nextInt(this.width), randGen.nextInt(this.height),
          badgersDanceSteps));
      }
    }
     
     // checks if c-key is pressed
     if ((Character.toUpperCase(this.key) == 'C')) {
       
       // stops the badgers dancing
       danceShowOn = false;
       
       for (int i = things.size() - 1; i >= 0; i--) {
         if (things.get(i) instanceof MovingThing) {
           things.remove(i);
         }
       }
     }
     
     // checks if d-key is pressed
     if ((Character.toUpperCase(this.key) == 'D') && (danceShowOn == false) && 
         (badgersCount() >= 1)) {
       
       danceShowOn = true;
       
       for (int i = 0; i < things.size(); i++) {
         if (things.get(i) instanceof Badger b) {
           setStartDancePositions();
           b.startDancing();
         }
       }
       
     }
  }
  
  public void mousePressed() {
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Clickable c) {
        c.mousePressed();
        
      }
    }
  }
  
  public void mouseReleased() {
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Clickable c) {
        c.mouseReleased();
        
      }
    }
  }
 
  private void setStartDancePositions() {
    int counter = 0;
    
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Badger b) {
        b.x = startDancePositions[counter][0];
        b.y = startDancePositions[counter][1];
        
        counter++;
      }
    }
  }
}
