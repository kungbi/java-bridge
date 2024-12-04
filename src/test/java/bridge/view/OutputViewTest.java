package bridge.view;

import bridge.domain.BridgeCell;
import bridge.dtos.GameResult;
import bridge.dtos.MapState;
import bridge.dtos.MapState.SingleMapState;
import java.util.List;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    @Test
    void 이동한_다리_상태_출력_테스트() {
        OutputView outputView = new OutputView();
        MapState mapState = new MapState(
                List.of(
                        new SingleMapState(BridgeCell.UP, true),
                        new SingleMapState(BridgeCell.UP, true),
                        new SingleMapState(BridgeCell.DOWN, true),
                        new SingleMapState(BridgeCell.DOWN, false)
                )
        );

        outputView.printMap(mapState);
    }

    @Test
    void 최종_결과_테스트() {
        OutputView outputView = new OutputView();
        MapState mapState = new MapState(
                List.of(
                        new SingleMapState(BridgeCell.UP, true),
                        new SingleMapState(BridgeCell.UP, true),
                        new SingleMapState(BridgeCell.DOWN, true),
                        new SingleMapState(BridgeCell.DOWN, false)
                )
        );
        GameResult gameResult = new GameResult(
                mapState,
                false,
                2
        );

        outputView.printResult(gameResult);
    }

}