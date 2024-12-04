package bridge.dtos;

import bridge.domain.BridgeCell;
import java.util.List;

public record MapState(List<SingleMapState> results) {
    public record SingleMapState(BridgeCell userAnswer, boolean isCorrect) {

    }

}
