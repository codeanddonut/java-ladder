package laddergame.model.game;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RewardsTest {
    private final Players players = new Players(Arrays.asList("abc", "def", "ghi"));

    @Test
    void initTestA() {
        assertThat(new Rewards(Arrays.asList("ABC"), players).toString()).isEqualTo("ABC, 꽝, 꽝");
    }

    @Test
    void initTestB() {
        assertThat(new Rewards(Arrays.asList(), players).toString()).isEqualTo("꽝, 꽝, 꽝");
    }

    @Test
    void initTestC() {
        assertThat(new Rewards(Arrays.asList("ABC", "12 ", "34", "56"), players).toString()).isEqualTo("ABC, 12, 34");
    }

    @Test
    void initTestD() {
        assertThat(new Rewards(Arrays.asList(" ABC      ", "123 "), players).toString()).isEqualTo("ABC, 123, 꽝");
    }

    @Test
    void longestLengthTest() {
        assertThat(new Rewards(Arrays.asList("abc", "bcd", "cdef", "g"), players).longestNameLength()).isEqualTo(4);
    }
}