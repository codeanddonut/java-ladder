package laddergame.model.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @Test
    void initTestA() {
        assertThat(new Player("woowa").toString()).isEqualTo("woowa");
    }

    @Test
    void initTestB() {
        assertThatThrownBy(() -> new Player(""));
    }

    @Test
    void initTestC() {
        assertThatThrownBy(() -> new Player("asdafwewf"));
    }
}