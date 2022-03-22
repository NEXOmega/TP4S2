package model;

import java.util.Random;

public class Die {

  // To generate a random integer uniformly between 0 and max-1,
  // use: generator.nextInt(max);
  private static final Random generator = new Random();
  private int faceValue;
  private boolean isBlocked;

  public Die() {
    this.faceValue = value();
    this.isBlocked = true;
  }

  public void roll() {
    this.faceValue = generator.nextInt(6)+1;
  }

  public int value() {
    return faceValue;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void block() {
    this.isBlocked = true;
  }

  public void unblock() {
    this.isBlocked = false;
  }

}
