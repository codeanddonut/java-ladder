package laddergame.model.game;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
    @Test
    void initTestA() {
        assertThat(new Players(Arrays.asList("ABC")).toString()).isEqualTo("ABC");
    }

    @Test
    void initTestB() {
        assertThat(new Players(Arrays.asList("ABC ", "123")).toString()).isEqualTo("ABC, 123");
    }

    @Test
    void initTestC() {
        assertThat(new Players(Arrays.asList("ABC", "12 ")).toString()).isEqualTo("ABC, 12");
    }

    @Test
    void initTestD() {
        assertThat(new Players(Arrays.asList(" ABC      ", "123 ")).toString()).isEqualTo("ABC, 123");
    }

    @Test
    void initTestE() {
        assertThatThrownBy(() -> new Players(Arrays.asList("", " ")));
    }

    @Test
    void initTestF() {
        assertThatThrownBy(() -> new Players(Arrays.asList()));
    }

    @Test
    void longestLengthTest() {
        assertThat(new Players(Arrays.asList("abc", "bcd", "cdef", "g")).longestNameLength()).isEqualTo(4);
    }
}