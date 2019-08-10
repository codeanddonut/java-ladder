package laddergame.view;

import laddergame.model.ladder.Height;
import laddergame.model.game.Players;
import laddergame.model.game.Rewards;
import laddergame.model.result.Query;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final String DELIMITER = ",+";
    private static final Scanner input = new Scanner(System.in);

    public static Players inputNames() {
        return TryUntilSuccess.run(() -> {
            System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
            return new Players(tokenize(input.nextLine()));
        });
    }

    public static Rewards inputRewards(Players players) {
        return TryUntilSuccess.run(() -> {
            System.out.println("\n실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
            return new Rewards(tokenize(input.nextLine()), players);
        });
    }

    public static Height inputHeight() {
        return TryUntilSuccess.run(() -> {
            System.out.println("\n최대 사다리 높이는 몇 개인가요?");
            return new Height(Integer.parseInt(input.nextLine().trim()));
        });
    }

    public static Query inputQuery() {
        return TryUntilSuccess.run(() -> {
            System.out.println("\n결과를 보고 싶은 사람은?");
            return new Query(tokenize(input.nextLine()));
        });
    }

    private static List<String> tokenize(String input) {
        return Stream.of(input.split(DELIMITER)).map(String::trim).collect(Collectors.toList());
    }
}