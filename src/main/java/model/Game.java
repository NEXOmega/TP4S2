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
    for(int i = 1; i <= 6; i++)
      add(new SumOfK(i));
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
    int score = 0;
    if(rows.subList(0, 5).stream().mapToInt(ScoreRow::score).sum() == 63)
      score += 100;
    return rows.stream().mapToInt(ScoreRow::score).sum() + score;
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
      for(Die die : dice)
        if(!die.isBlocked())
          die.roll();
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
    for(ScoreRow row : rows)
      if(row.isAvailable())
        return false;
    return true;
  }

  private void unblockDice() {
    for(Die die : dice)
      die.unblock();
  }
}
