package ladder.model.ladder;

import ladder.model.coin.Coin;
import ladder.model.game.Players;
import ladder.model.game.Rewards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {
    private final List<Level> levels;
    private final List<Integer> mappingTable;

    public Ladder(Players players, Height height, Coin possibility) {
        this.levels = IntStream.range(0, height.get())
                                .mapToObj(i -> new Level(players.number() - 1, possibility))
                                .collect(
                                        Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                Collections::unmodifiableList
                                        )
                                );
        this.mappingTable = compressLevels(players.number());
    }

    private List<Integer> compressLevels(int width) {
        final List<Integer> table = IntStream.range(0, width).boxed().collect(Collectors.toList());
        this.levels.forEach(level ->
                level.lines().forEach(line ->
                        Collections.swap(table, line, line + 1)
                )
        );
        return Collections.unmodifiableList(table);
    }

    public Rewards climb(Rewards rewards, Players players) {
        if (rewards.number() != players.number() || players.number() != this.width()) {
            throw new IllegalArgumentException();
        }
        return new Rewards(
                IntStream.range(0, rewards.number())
                        .mapToObj(i -> rewards.get(this.mappingTable.indexOf(i)).toString())
                        .collect(Collectors.toList()),
                players
        );
    }

    public List<Level> levels() {
        return this.levels;
    }

    public int width() {
        return this.mappingTable.size();
    }
}