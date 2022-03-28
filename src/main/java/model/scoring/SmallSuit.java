package model.scoring;

import model.Combination;
import model.DiceResult;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SmallSuit implements Combination {
    @Override
    public int score(DiceResult result) {
        Collections.sort(result.getFaces());
        List<Integer> dices = result.getFaces().stream().distinct().collect(Collectors.toList());
        return (dices.size() == 4 ? 25 : 0);
    }

    @Override
    public String description() {
        return "Suite des dès";
    }
}
