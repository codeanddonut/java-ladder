package laddergame.model.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RewardTest {
    @Test
    void initTestA() {
        assertThat(new Reward("woowa").toString()).isEqualTo("woowa");
    }

    @Test
    void initTestB() {
        assertThat(new Reward().toString()).isEqualTo("꽝");
    }

    @Test
    void initTestC() {
        assertThatThrownBy(() -> new Reward(""));
    }
}