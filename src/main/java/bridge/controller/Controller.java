package bridge.controller;

import bridge.view.OutputView;
import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.GameStates;
import bridge.dtos.MapStateDto;
import bridge.enums.BridgeCell;
import bridge.enums.GameCommand;

public class Controller {
    private final BridgeMaker bridgeMaker;
    private final OutputView outputView;
    private final RetryInputUtil retryInputUtil;

    public Controller(BridgeMaker bridgeMaker, OutputView outputView, RetryInputUtil retryInputUtil) {
        this.bridgeMaker = bridgeMaker;
        this.outputView = outputView;
        this.retryInputUtil = retryInputUtil;
    }

    public void run() {
        outputView.printStaringMessage();
        int bridgeSize = retryInputUtil.readBridgeSize();
        Bridge bridge = Bridge.from(this.bridgeMaker.makeBridge(bridgeSize));
        GameStates gameStates = new GameStates(bridgeSize);
        BridgeGame game = new BridgeGame(gameStates, bridge);
        startGame(game);
        outputView.printResult(game.getGameResult());
    }

    private void startGame(BridgeGame game) {
        while (true) {
            startSingleGame(game);
            if (game.isComplete() || retryInputUtil.readGameCommand() == GameCommand.QUIT) {
                break;
            }
            game.retry();
        }
    }

    private void startSingleGame(BridgeGame game) {
        do {
            BridgeCell userAnswer = retryInputUtil.readMoving();
            MapStateDto mapStateDto = game.move(userAnswer);
            outputView.printMap(mapStateDto);
        } while (!game.isGameOver() && !game.isComplete());
    }

}
