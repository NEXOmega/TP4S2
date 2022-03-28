package model.scoring;

import model.Combination;
import model.DiceResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FourOfAKind implements Combination {
    @Override
    public int score(DiceResult result) {
        if(result.getFaces().stream().distinct().count() == 2) {
            return result.getFaces().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList()).get(0)*4;
        }
        return 0;
    }

    @Override
    public String description() {
        return "4 fois le même dès";
    }
}
