package ladder.model.result;

import ladder.model.game.Player;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Query {
    private static final int COMMAND_MIN_LENGTH = 1;
    private static final int COMMAND_MAX_LENGTH = 5;
    private static final String SHOW_ALL_PLAYERS = "all";

    private final List<String> commands;

    public Query(List<String> commands) {
        this.commands = Optional.ofNullable(commands).map(List::stream)
                                                    .map(x -> x.map(String::trim))
                                                    .map(x -> x.filter(y -> y.length() >= COMMAND_MIN_LENGTH))
                                                    .map(x -> x.filter(y -> y.length() <= COMMAND_MAX_LENGTH))
                                                    .map(x -> x.collect(
                                                            Collectors.collectingAndThen(
                                                                    Collectors.toList(),
                                                                    Collections::unmodifiableList
                                                            )
                                                    ))
                                                    .orElseThrow(IllegalArgumentException::new);
        if (this.commands.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public boolean showAll() {
        return commands.contains(SHOW_ALL_PLAYERS);
    }

    public boolean contains(Player player) {
        return commands.contains(player.toString());
    }
}