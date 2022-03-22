package model.scoring;

import model.Combination;
import model.DiceResult;

public class Chance implements Combination {
    @Override
    public int score(DiceResult result) {
        return result.sum();
    }

    @Override
    public String description() {
        return "Renvoie la somme des dès";
    }
}
