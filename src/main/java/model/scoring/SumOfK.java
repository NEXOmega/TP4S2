package model.scoring;

import model.Combination;
import model.DiceResult;

import java.util.List;

public class SumOfK implements Combination {
    int k;

    public SumOfK(int k) {
        this.k = k;
    }

    @Override
    public int score(DiceResult result) {
        return result.getFaces().stream().filter(e->e==k).mapToInt(Integer::intValue).sum();
    }

    @Override
    public String description() {
        return "Somme d'une valeur";
    }
}
