package bridge.domain;

import bridge.dtos.GameResultDto;
import bridge.dtos.MapStateDto;
import bridge.dtos.MapStateDto.SingleMapState;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    @Test
    void 게임_진행_테스트() {
        // given
        Bridge bridge = Bridge.from(List.of("U", "D", "U", "D"));
        GameStates gameStates = new GameStates(bridge.size());
        BridgeGame bridgeGame = new BridgeGame(gameStates, bridge);

        // when
        bridgeGame.move(BridgeCell.UP);
        bridgeGame.move(BridgeCell.DOWN);
        bridgeGame.move(BridgeCell.UP);
        MapStateDto mapStateDto = bridgeGame.move(BridgeCell.UP);

        // then
        Assertions.assertThat(mapStateDto.results()).isEqualTo(List.of(
                new SingleMapState(BridgeCell.UP, true),
                new SingleMapState(BridgeCell.DOWN, true),
                new SingleMapState(BridgeCell.UP, true),
                new SingleMapState(BridgeCell.UP, false)
        ));
    }

    @Test
    void 게임_재도전_테스트() {
        // given
        Bridge bridge = Bridge.from(List.of("U", "D", "U", "D"));
        GameStates gameStates = new GameStates(bridge.size());
        BridgeGame bridgeGame = new BridgeGame(gameStates, bridge);

        // when
        bridgeGame.move(BridgeCell.UP);
        bridgeGame.move(BridgeCell.DOWN);
        bridgeGame.move(BridgeCell.DOWN); // game over !
        Assertions.assertThat(gameStates.isGameOver()).isTrue();
        bridgeGame.retry();

        // then
        Assertions.assertThat(gameStates.getPlayerPosition()).isEqualTo(0);
        Assertions.assertThat(gameStates.getTryCount()).isEqualTo(2);
        Assertions.assertThat(gameStates.isGameOver()).isFalse();
    }

    @Test
    void 게임_결과_테스트() {
        // given
        Bridge bridge = Bridge.from(List.of("U", "D", "U", "D"));
        GameStates gameStates = new GameStates(bridge.size());
        BridgeGame bridgeGame = new BridgeGame(gameStates, bridge);
        bridgeGame.move(BridgeCell.UP);
        bridgeGame.move(BridgeCell.DOWN);
        bridgeGame.move(BridgeCell.DOWN); // game over !

        // when
        GameResultDto gameResult = bridgeGame.getGameResult();

        // then
        Assertions.assertThat(gameResult.isSuccess()).isFalse();
        Assertions.assertThat(gameResult.tryCount()).isEqualTo(1);

    }

}