package model;

import model.scoring.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

  private List<Die> dice = new LinkedList<>();
  private List<ScoreRow> rows;
  private int nbRollsLeft = 5;

  public Game() {
    this.rows = new LinkedList<ScoreRow>();
    add(new SumOfK(1));
    add(new Brelan());
    add(new FourOfAKind());
    add(new Yahtzee());
    add(new SmallSuit());
    add(new BigSuit());

    for(int i = 0; i < 5; i++)
      dice.add(new Die());
    initialRoll();
  }

  public List<Die> getDice() {
    return dice;
  }

  public List<ScoreRow> getRows() {
    return rows;
  }

  private DiceResult getDiceResult() {
    return DiceResult.fromDice(dice);
  }

  private int add(Combination row){
    if(!((LinkedList)rows).contains(row)) {
      rows.add(new ScoreRow(row));
      return 1;
    }
    return 0;
  }

  public int getTotalScore() {
    // TODO: à modifier
    return rows.stream().mapToInt(ScoreRow::score).sum();
  }



  public int getNbRollsLeft() {
    return nbRollsLeft;
  }


  public void initialRoll() {
    this.nbRollsLeft = 3;
    unblockDice();
    roll();
  }

  public void roll() {
    if(this.nbRollsLeft >= 1) {
      for (int i = 0; i < dice.size(); i++) {
        dice.get(i).setFaceValue(i+1);
      }
      this.nbRollsLeft-=1;
    }
  }

  public void choose(ScoreRow row) {
    for(ScoreRow row1 : rows)
      if(row1.equals(row))
        if(row1.isAvailable())
          row1.record(getDiceResult());
  }

  public boolean isOver() {
    // TODO: à modifier
    return false;
  }

  private void unblockDice() {
    for(Die die : dice)
      die.unblock();
  }
}
