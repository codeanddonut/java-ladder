package ladder.model.game;

import ladder.model.coin.Half;
import ladder.model.ladder.Height;
import ladder.model.ladder.Ladder;
import ladder.model.result.Query;
import ladder.model.result.Result;

public class Game {
    private final Players players;
    private final Rewards rewards;
    private final Ladder ladder;

    public Game(Players players, Rewards rewards, Height height) {
        this.players = players;
        this.rewards = rewards;
        this.ladder = new Ladder(players, height, new Half());
    }

    public Rewards play() {
        return this.ladder.climb(this.rewards, this.players);
    }

    public Result result(Query query) {
        return new Result(this, query);
    }

    public Players players() {
        return this.players;
    }

    public Rewards rewards() {
        return this.rewards;
    }

    public Ladder ladder() {
        return this.ladder;
    }
}