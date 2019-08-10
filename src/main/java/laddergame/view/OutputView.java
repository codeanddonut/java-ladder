package laddergame.view;

import laddergame.model.game.Game;
import laddergame.model.ladder.Ladder;
import laddergame.model.result.Query;
import laddergame.model.result.Result;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final int MIN_SPACE = 2;
    private static final String BLANK = " ";
    private static final String HORIZ_LINE = "-";
    private static final String VERT_LINE = "|";

    public static void printGame(Game game) {
        final int maxLength = Math.max(game.players().longestNameLength(), game.rewards().longestNameLength());
        final int offset = maxLength - Math.max(
                        game.players().firstPlayerNameLength(),
                        game.rewards().firstRewardNameLength()
        ) / 2;
        System.out.println("\n사다리 결과\n");
        printWords(game.players().everyPlayerNames(), maxLength, offset);
        printLadder(game.ladder(), maxLength, offset);
        printWords(game.rewards().everyRewardNames(), maxLength, offset);
    }

    private static void printWords(List<String> words, int maxLength, int offset) {
        final StringBuilder result = new StringBuilder();
        words.forEach(word -> {
            final int leftPadding = (maxLength - word.length()) / 2 + MIN_SPACE;
            final int rightPadding = maxLength + 2 * MIN_SPACE - word.length() - leftPadding;
            result.append(repeatChar(BLANK, leftPadding));
            result.append(word);
            result.append(repeatChar(BLANK, rightPadding));
        });
        System.out.println(result.toString().substring(offset));
    }

    private static void printLadder(Ladder ladder, int maxLength, int offset) {
        final int initialSpace = (maxLength - VERT_LINE.length()) / 2 + MIN_SPACE;
        final int space = maxLength - VERT_LINE.length() + 2 * MIN_SPACE;
        ladder.levels().forEach(level -> {
            final String leftPadding = repeatChar(BLANK, initialSpace);
            final String result = leftPadding + VERT_LINE + printLevel(level.lines(), ladder.width(), space);
            System.out.println(result.substring(offset));
        });
    }

    private static String printLevel(List<Integer> lines, int width, int space) {
        final StringBuilder result = new StringBuilder();
        IntStream.range(0, width - 1).forEach(i -> {
            result.append(drawHorizontalLine(lines.contains(i), space));
            result.append(VERT_LINE);
        });
        return result.toString();
    }

    private static String drawHorizontalLine(boolean ifExists, int width) {
        return ifExists ? repeatChar(HORIZ_LINE, width) : repeatChar(BLANK, width);
    }

    private static String repeatChar(String text, int n) {
        final StringBuilder result = new StringBuilder();
        IntStream.range(0, n).forEach(i -> result.append(text));
        return result.toString();
    }

    public static boolean printResult(Game game, Query query) {
        final Result result = game.result(query);
        System.out.println("\n실행 결과");
        if (!result.hasNext()) {
            System.out.println("존재하지 않는 참여자입니다. 프로그램을 종료합니다.");
            return false;
        }
        while (result.hasNext()) {
            System.out.println(result.next());
        }
        return true;
    }
}