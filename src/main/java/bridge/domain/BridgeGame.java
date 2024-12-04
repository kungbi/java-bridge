package bridge.domain;

import bridge.dtos.GameResultDto;
import bridge.dtos.MapStateDto;
import bridge.dtos.MapStateDto.SingleMapState;

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

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
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

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.gameStates.reset();
        this.mapState.reset();
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
