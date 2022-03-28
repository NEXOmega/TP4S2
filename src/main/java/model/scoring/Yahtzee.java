package model.scoring;

import model.Combination;
import model.DiceResult;

public class Yahtzee implements Combination {
    @Override
    public int score(DiceResult result) {
        return (result.getFaces().stream().distinct().count() == 1 ? 50 : 0);
    }

    @Override
    public String description() {
        return "5 fois le même dès";
    }
}
