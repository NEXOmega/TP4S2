package model.scoring;

import model.Combination;
import model.DiceResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BigSuit implements Combination {
    @Override
    public int score(DiceResult result) {
        Collections.sort(result.getFaces());
        List<Integer> dices = result.getFaces().stream().distinct().collect(Collectors.toList());
        return (dices.size() == 5 ? 40 : 0);
    }

    @Override
    public String description() {
        return null;
    }
}
