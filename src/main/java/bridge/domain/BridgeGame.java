package bridge.domain;

import bridge.dtos.GameResultDto;
import bridge.dtos.MapStateDto;
import bridge.dtos.MapStateDto.SingleMapState;
import bridge.enums.BridgeCell;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final GameStates gameStates;
    private final Bridge bridge;
    private MapState mapState;

    public BridgeGame(GameStates gameStates, Bridge bridge) {
        this.gameStates = gameStates;
        this.bridge = bridge;
        this.mapState = new MapState();
    }

    public MapStateDto move(BridgeCell userAnswer) {
        validate();
        int playerPosition = gameStates.getPlayerPosition();
        BridgeCell answer = this.bridge.get(playerPosition);
        if (answer != userAnswer) {
            gameStates.setGameOver();
            addIncorrectResult(userAnswer);
            return MapStateDto.from(mapState);
        }
        addCorrectResult(userAnswer);
        this.gameStates.movePlayer();
        return MapStateDto.from(mapState);
    }

    public void retry() {
        this.gameStates.reset();
        this.mapState.reset();
    }

    public boolean isGameOver() {
        return this.gameStates.isGameOver();
    }


    public GameResultDto getGameResult() {
        return new GameResultDto(
                MapStateDto.from(this.mapState),
                this.gameStates.isComplete(),
                this.gameStates.getTryCount()
        );
    }

    private void validate() {
        if (gameStates.isGameOver()) {
            throw new IllegalStateException("Game over");
        }
    }

    private void addCorrectResult(BridgeCell userAnswer) {
        this.mapState.add(new SingleMapState(userAnswer, true));
    }

    private void addIncorrectResult(BridgeCell userAnswer) {
        this.mapState.add(new SingleMapState(userAnswer, false));
    }
}
