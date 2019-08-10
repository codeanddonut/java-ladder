package laddergame.model.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rewards {
    private final List<Reward> rewards;

    public Rewards(List<String> names, Players players) {
        final List<Reward> rewards = Optional.ofNullable(names).map(List::stream)
                                                                .map(x -> x.limit(players.number()))
                                                                .map(x -> x.map(Reward::new))
                                                                .map(x -> x.collect(Collectors.toList()))
                                                                .orElseThrow(IllegalArgumentException::new);
        rewards.addAll(
                IntStream.range(0, players.number() - rewards.size())
                        .mapToObj(i -> new Reward())
                        .collect(Collectors.toList())
        );
        this.rewards = Collections.unmodifiableList(rewards);
    }

    public int longestNameLength() {
        return this.rewards.stream().map(Reward::toString)
                                    .mapToInt(String::length)
                                    .max()
                                    .getAsInt();
    }

    public int firstRewardNameLength() {
        return this.rewards.get(0).toString().length();
    }

    public List<String> everyRewardNames() {
        return this.rewards.stream().map(Reward::toString).collect(Collectors.toList());
    }

    public Reward get(int index) {
        return this.rewards.get(index);
    }

    public int number() {
        return this.rewards.size();
    }

    @Override
    public String toString() {
        final String tmp = this.rewards.toString();
        return tmp.substring(1, tmp.length() - 1);
    }
}