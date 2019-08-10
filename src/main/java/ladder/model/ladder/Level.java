package ladder.model.ladder;

import ladder.model.coin.Coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {
    private static int BUILD = 2;
    private static int PASS = 1;

    private final List<Integer> lines;

    public Level(int width, Coin possibility) {
        this.lines = build(0, width, possibility, new ArrayList<>());
    }

    private List<Integer> build(int target, int left, Coin possibility, List<Integer> acc) {
        if (left <= 0) {
            return Collections.unmodifiableList(acc);
        }
        if (possibility.toss()) {
            acc.add(target);
            return build(target + BUILD, left - BUILD, possibility, acc);
        }
        return build(target + PASS, left - PASS, possibility, acc);
    }

    public List<Integer> lines() {
        return this.lines;
    }
}