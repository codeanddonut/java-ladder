package laddergame.model.result;

import laddergame.model.game.Game;
import laddergame.model.game.Players;
import laddergame.model.game.Rewards;
import laddergame.model.ladder.Height;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    private final Players players = new Players(Arrays.asList("a", "b", "c", "d", "e"));
    private final Game game = new Game(
            players,
            new Rewards(Arrays.asList("1", "2", "3", "4", "5"), players),
            new Height(8)
    );

    @Test
    void getAllTest() {
        final Result result = new Result(game, new Query(Arrays.asList("all")));
        int size = 0;
        while (result.hasNext()) {
            result.next();
            size++;
        }
        assertThat(size).isEqualTo(5);
    }

    @Test
    void getSomeTestA() {
        final Result result = new Result(game, new Query(Arrays.asList("a")));
        int size = 0;
        while (result.hasNext()) {
            result.next();
            size++;
        }
        assertThat(size).isEqualTo(1);
    }

    @Test
    void getSomeTestB() {
        final Result result = new Result(game, new Query(Arrays.asList("a", "b")));
        int size = 0;
        while (result.hasNext()) {
            result.next();
            size++;
        }
        assertThat(size).isEqualTo(2);
    }

    @Test
    void getNoneTest() {
        final Result result = new Result(game, new Query(Arrays.asList("f")));
        int size = 0;
        while (result.hasNext()) {
            result.next();
            size++;
        }
        assertThat(size).isEqualTo(0);
    }
}