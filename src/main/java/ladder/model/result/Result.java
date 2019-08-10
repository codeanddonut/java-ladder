package ladder.model.result;

import ladder.model.game.Game;
import ladder.model.game.Players;
import ladder.model.game.Rewards;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Result implements Iterator<String> {
    private final Players players;
    private final Rewards rewards;
    private final List<Integer> result;
    private int index = 0;

    public Result(Game game, Query query) {
        this.players = game.players();
        this.rewards = game.play();
        if (query.showAll()) {
            this.result = IntStream.range(0, this.players.number())
                                    .boxed()
                                    .collect(Collectors.toList());
        } else {
            this.result = IntStream.range(0, this.players.number())
                                    .filter(i -> query.contains(players.get(i)))
                                    .boxed()
                                    .collect(Collectors.toList());
        }
    }
    public boolean hasNext() {
        return index < result.size();
    }

    public String next() {
        return this.players.get(result.get(index)) + " : " + this.rewards.get(result.get(index++));
    }
}