
public class StarshipRobot extends MovingThing{
  
  private Thing destination;
  private Thing source;
  
  
  public StarshipRobot(Thing source, Thing destination, int speed){
    super(source.x, source.y, speed, "starshipRobot.png");
    
    this.source = source;
    this.destination = destination;
    
    if (source.x < destination.x) {
      isFacingRight = true;
    }
    else {
      isFacingRight = false;
    }
  }
  @Override
  public void draw() {
    go();
    super.draw();
  }
  
  public void go() {
    // checks if starship robot is over destination
    if (isOver(destination)) {
      Thing tempVal = source;
      source = destination;
      destination = tempVal;
      isFacingRight = !isFacingRight;
    }

    // moves robot towards destination IF NOT over destination already
    else {
      moveTowardsDestination();
    }
  }
  
  public boolean isOver(Thing thing) {
    int thingWidth = thing.image().width;
    int thingHeight = thing.image().height;

    int robotWidth = this.image().width;
    int robotHeight = this.image().height;

    // thing bottom left and top right coordinates
    float thingX1 = thing.x - thingWidth / 2;
    float thingY1 = thing.y - thingHeight / 2;
    float thingX2 = thing.x + thingWidth / 2;
    float thingY2 = thing.y + thingHeight / 2;

    // starship bottom left and top right coordinates
    float robotX3 = this.x - robotWidth / 2;
    float robotY3 = this.y - robotHeight / 2;
    float robotX4 = this.x + robotWidth / 2;
    float robotY4 = this.y + robotHeight / 2;

    return !(robotX3 > thingX2 || robotY3 > thingY2 || thingX1 > robotX4 || 
        thingY1 > robotY4);
  }
  
  private void moveTowardsDestination() {
    float currentX = this.x;
    float currentY = this.y;

    float destinationX = destination.x;
    float destinationY = destination.y;

    float dx = destinationX - currentX;
    float dy = destinationY - currentY;

    int d = (int) Math.sqrt((dx * dx) + (dy * dy));

    int moveDistance = speed;

    float newX = currentX + ((moveDistance * dx) / d);
    float newY = currentY + ((moveDistance * dy) / d);

    this.x = newX;
    this.y = newY;
  }
}
