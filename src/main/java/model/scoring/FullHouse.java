package model.scoring;

import model.Combination;
import model.DiceResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FullHouse implements Combination {
    @Override
    public int score(DiceResult result) {
        HashMap<Integer, Long > map = (HashMap<Integer, Long>) result.getFaces().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if(map.values().contains(2l) && map.values().contains(3l))
            return 25;
        return 0;
    }

    @Override
    public String description() {
        return "2 + 3 fois le même dès";
    }
}
