package laddergame.model.game;

import java.util.Optional;

public class Reward {
    public static final String DEFAULT = "꽝";

    private final String name;

    public Reward(String name) {
        this.name = Optional.ofNullable(name).map(String::trim)
                                            .filter(x -> x.length() > 0)
                                            .orElseThrow(IllegalArgumentException::new);
    }

    public Reward() {
        this.name = DEFAULT;
    }

    @Override
    public String toString() {
        return this.name;
    }
}