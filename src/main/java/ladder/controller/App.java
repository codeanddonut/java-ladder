package ladder.controller;

import ladder.model.game.Game;
import ladder.model.game.Players;
import ladder.view.InputView;
import ladder.view.OutputView;

public class App {
    public static void main(String[] argc) {
        final Players players = InputView.inputNames();
        final Game game = new Game(players, InputView.inputRewards(players), InputView.inputHeight());
        OutputView.printGame(game);
        while(OutputView.printResult(game, InputView.inputQuery()));
    }
}