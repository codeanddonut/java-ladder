package laddergame.model.ladder;

import laddergame.model.coin.Always;
import laddergame.model.coin.EvenOnly;
import laddergame.model.coin.Never;
import laddergame.model.coin.OddOnly;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LevelTest {
    @Test
    void levelCreateTestNeverDraw() {
        assertThat(new Level(4, new Never()).lines()).isEqualTo(Arrays.asList());
    }

    @Test
    void levelCreateTestAlwaysDraw() {
        assertThat(new Level(11, new Always()).lines()).isEqualTo(Arrays.asList(0, 2, 4, 6, 8, 10));
    }

    @Test
    void levelCreateTestDrawOdd() {
        assertThat(new Level(13, new OddOnly()).lines()).isEqualTo(Arrays.asList(0, 3, 6, 9, 12));
    }

    @Test
    void levelCreateTestDrawEven() {
        assertThat(new Level(23, new EvenOnly()).lines()).isEqualTo(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22));
    }
}