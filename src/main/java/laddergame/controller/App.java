package laddergame.controller;

import laddergame.model.game.Game;
import laddergame.model.game.Players;
import laddergame.view.InputView;
import laddergame.view.OutputView;

public class App {
    public static void main(String[] argc) {
        final Players players = InputView.inputNames();
        final Game game = new Game(players, InputView.inputRewards(players), InputView.inputHeight());
        OutputView.printGame(game);
        while(OutputView.printResult(game, InputView.inputQuery()));
    }
}