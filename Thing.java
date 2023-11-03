import java.io.File;

public class Thing {
  
  //image of this graphic thing of type PImage
  private processing.core.PImage image; 
  
  // PApplet object that represents the display window of this graphic application
  protected static processing.core.PApplet processing;
  
  // x-position of this thing in the display window
  protected float x;
  
  // y-position of this thing in the display window
  protected float y;
  
  public static void setProcessing(processing.core.PApplet processing) {
    Thing.processing = processing;
  }
  
  public Thing(float x, float y, String imageFilename) {
    
    
    // set Thing draw parameters
    this.image = processing.loadImage("images" + File.separator + imageFilename);

    // sets the position of the Thing object
    this.x = x;
    this.y = y;
  }
  
  public void draw() {
    processing.image(this.image, x, y);
  }
  
  public processing.core.PImage image() {
    
  return image;
  }
  
  public boolean isMouseOver() {
    int thingWidth = this.image().width;
    int thingHeight = this.image().height;

    // checks if the mouse is over the thing
    return processing.mouseX >= this.x - thingWidth / 2
        && processing.mouseX <= this.x + thingWidth / 2
        && processing.mouseY >= this.y - thingHeight / 2
        && processing.mouseY <= this.y + thingHeight / 2;
  }
  
  
}
