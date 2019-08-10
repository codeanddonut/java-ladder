package laddergame.model.game;

import java.util.Optional;

public class Player {
    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 5;

    private final String name;

    public Player(String name) {
        this.name = Optional.ofNullable(name).map(String::trim)
                                            .filter(x -> x.length() >= NAME_MIN_LENGTH)
                                            .filter(x -> x.length() <= NAME_MAX_LENGTH)
                                            .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return this.name;
    }
}