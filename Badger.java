
public class Badger extends MovingThing implements Clickable{
  
  private DanceStep[] danceSteps; // array storing this Badger's dance show steps
  
  private boolean isDancing; // indicates whether this badger is dancing or not
  private boolean isDragging; // indicates whether this badger is being dragged or not
  
  //stores the next dance (x, y) position of this Badger.
  private float[] nextDancePosition; 
  
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse
  
  private int stepIndex; // index position of the current dance step of this badger
  
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    
    this.x = x;
    this.y = y;
    
    this.danceSteps = danceSteps;
    stepIndex = 1;
  }
  
  private void dance() {
    
    if (makeMoveDance() == true) {
      // updates next dance position
      nextDancePosition = this.danceSteps[stepIndex].getPositionAfter(x, y);

      // increments the stepIndex
      stepIndex++;
      stepIndex %= danceSteps.length;
    }
  }
  
  private void drag() {
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x += dx;
    y += dy;

    if (x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if (y > 0)
      y = Math.min(y, processing.height);
    else
      y = 0;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }
  
  public void draw() {
    // checks if Badger is dragging
    if (isDragging == true) {
      drag();
    }
    
    // checks if Badger is dancing 
    if (isDancing == true) {
      dance();
    }
    
    // draws the Badger to the screen
    super.draw();
  }
  
  public boolean isDragging() {
    return isDragging;
  }
  
  private boolean makeMoveDance() {
    // moves this badger one speed towards its nextDancePosition
    x += speed;
    y += speed;
    
    // checks which way the Badger is facing
    if (x > 0) {
      isFacingRight = true;
    }
    else {
      isFacingRight = false;
    }
    
    return false;
  }
  
  @Override
  public void mousePressed() {
    if ((isMouseOver() == true) && (isDancing = false)) {
      startDragging();
    }
  }
  
  @Override
  public void mouseReleased() {
    stopDragging();
  }
  
  public void startDancing() {
    
    // updates isDancing data field
    isDancing = true;
    
    // stops dragging this badger
    stopDragging();
    
    // sets stepIndex to 0
    stepIndex = 0;
    
    // resets the nextDancePosition
    nextDancePosition[0] = 0;
    nextDancePosition[1] = 0;
  }
  public void startDragging() {
    isDragging = true;
    drag();
  }
  
  public void stopDancing() {
 // updates isDancing data field
    isDancing = false;
  }
  
  public void stopDragging() {
    this.isDragging = false;
  }
}
