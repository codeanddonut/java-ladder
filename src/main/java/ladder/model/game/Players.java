package ladder.model.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> names) {
        this.players = Optional.ofNullable(names).map(List::stream)
                                                .map(x -> x.map(Player::new))
                                                .map(x -> x.collect(
                                                        Collectors.collectingAndThen(
                                                                Collectors.toList(),
                                                                Collections::unmodifiableList
                                                        )
                                                ))
                                                .orElseThrow(IllegalArgumentException::new);
        if (this.players.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public int longestNameLength() {
        return this.players.stream().map(Player::toString)
                                    .mapToInt(String::length)
                                    .max()
                                    .getAsInt();
    }

    public int firstPlayerNameLength() {
        return this.players.get(0).toString().length();
    }

    public List<String> everyPlayerNames() {
        return this.players.stream().map(Player::toString).collect(Collectors.toList());
    }

    public Player get(int index) {
        return this.players.get(index);
    }

    public int number() {
        return this.players.size();
    }

    @Override
    public String toString() {
        final String tmp = this.players.toString();
        return tmp.substring(1, tmp.length() - 1);
    }
}