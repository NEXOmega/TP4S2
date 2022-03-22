package model;

public class ScoreRow {

  private int score;
  private boolean isFilled;
  private Combination combination;

  public ScoreRow(Combination combination) {
    this.combination = combination;
    this.score = 0;
    this.isFilled = false;
  }

  public boolean isAvailable() {
    return !isFilled;
  }

  public int score() {
    return score;
  }


  public void record(DiceResult result) {
    if(!isFilled) {
      this.score = combination.score(result);
      this.isFilled = true;
    }
  }

  public String description() {
    return combination.description();
  }

  public void reset() {
    // TODO: à compléter 
  }
}
