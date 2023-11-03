
public class MovingThing extends Thing implements Comparable<MovingThing>{
  protected boolean isFacingRight;

  protected int speed;
  
  public MovingThing(float x, float y, int speed, String imageFileName) {
    super(x, y, imageFileName);
    isFacingRight = true;
    this.speed = speed;
  }

  @Override
  public int compareTo(MovingThing other) {
    return this.speed - other.speed;
  }
  
  public void draw() {
 // draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
    processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }
}
