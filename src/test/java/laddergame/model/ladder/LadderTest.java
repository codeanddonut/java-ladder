package laddergame.model.ladder;

import laddergame.model.coin.Always;
import laddergame.model.coin.EvenOnly;
import laddergame.model.coin.Never;
import laddergame.model.coin.OddOnly;
import laddergame.model.game.Players;
import laddergame.model.game.Rewards;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    private final Players players = new Players(Arrays.asList("a", "b", "c", "d", "e"));

    @Test
    void initTestAlwaysBuild() {
        final Players players = new Players(Arrays.asList("a", "b", "c", "d", "e"));
        final Ladder ladder = new Ladder(players, new Height(6), new Always());
        ladder.levels().forEach(l ->
            assertThat(new Level(ladder.width() - 1, new Always()).lines()).isEqualTo(l.lines())
        );
    }

    @Test
    void initTestNeverBuild() {
        final Players players = new Players(
                Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
        );
        final Ladder ladder = new Ladder(players, new Height(7), new Never());
        ladder.levels().forEach(l ->
            assertThat(new Level(ladder.width() - 1, new Never()).lines()).isEqualTo(l.lines())
        );
    }

    @Test
    void initTestOddOnlyBuild() {
        final OddOnly oddOnly = new OddOnly();
        final Players players = new Players(Arrays.asList("a", "b", "c", "d", "e", "f", "g"));
        final Ladder ladder = new Ladder(players, new Height(123), new OddOnly());
        ladder.levels().forEach(l ->
            assertThat(new Level(ladder.width() - 1, oddOnly).lines()).isEqualTo(l.lines())
        );
    }

    @Test
    void initTestEvenOnlyBuild() {
        final EvenOnly evenOnly = new EvenOnly();
        final Players players = new Players(
                Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n")
        );
        final Ladder ladder = new Ladder(players, new Height(2), new EvenOnly());
        ladder.levels().forEach(l ->
            assertThat(new Level(ladder.width() - 1, evenOnly).lines()).isEqualTo(l.lines())
        );
    }

    @Test
    void climbTestAlways() {
        final Rewards rewards = new Rewards(Arrays.asList("1", "2", "3", "4", "5"), players);
        final Ladder ladder = new Ladder(players, new Height(11), new Always());
        final List<String> expected = Arrays.asList("2", "1", "4", "3", "5");
        final Rewards actual = ladder.climb(rewards, players);
        IntStream.range(0, players.number()).forEach(i ->
            assertThat(actual.get(i).toString()).isEqualTo(expected.get(i))
        );
    }

    @Test
    void climbTestNever() {
        final Rewards rewards = new Rewards(Arrays.asList("1", "2", "3", "4", "5"), players);
        final Ladder ladder = new Ladder(players, new Height(9), new Never());
        final List<String> expected = Arrays.asList("1", "2", "3", "4", "5");
        final Rewards actual = ladder.climb(rewards, players);
        IntStream.range(0, players.number()).forEach(i ->
            assertThat(actual.get(i).toString()).isEqualTo(expected.get(i))
        );
    }

    @Test
    void climbTestOddOnly() {
        final OddOnly oddOnly = new OddOnly();
        final Rewards rewards = new Rewards(Arrays.asList("1", "2", "3", "4", "5"), players);
        final Ladder ladder = new Ladder(players, new Height(8), oddOnly);
        final List<String> expected = Arrays.asList("3", "1", "2", "4", "5");
        final Rewards actual = ladder.climb(rewards, players);
        IntStream.range(0, players.number()).forEach(i ->
                assertThat(actual.get(i).toString()).isEqualTo(expected.get(i))
        );
    }

    @Test
    void climbTestEvenOnly() {
        final EvenOnly evenOnly = new EvenOnly();
        final Rewards rewards = new Rewards(Arrays.asList("1", "2", "3", "4", "5"), players);
        final Ladder ladder = new Ladder(players, new Height(10), evenOnly);
        final List<String> expected = Arrays.asList("3", "1", "2", "5", "4");
        final Rewards actual = ladder.climb(rewards, players);
        IntStream.range(0, players.number()).forEach(i ->
                assertThat(actual.get(i).toString()).isEqualTo(expected.get(i))
        );
    }
}