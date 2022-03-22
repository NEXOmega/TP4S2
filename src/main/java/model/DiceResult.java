package model;

import java.util.*;
import java.util.stream.Collectors;

public class DiceResult {
    private List<Integer> faces;

    private DiceResult(List<Integer> faces) {
        this.faces = faces;
    }

    public int get(int die) {
        return faces.get(die);
    }

    public int sum() {
        return faces.stream().mapToInt(Integer::intValue).sum();
    }

    public int faceValue(int face) {
        return (int) faces.stream().filter(e -> e.equals(face)).count();
    }

    public static DiceResult fromIntegers(List<Integer> faces) {
        return new DiceResult(faces);
    }

    public static DiceResult fromDice(List<Die> dices) {
        List<Integer> faces = new LinkedList<>();
        for(Die dice : dices)
            faces.add(dice.value());
        return new DiceResult(faces);
    }
}
