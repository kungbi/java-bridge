package bridge.dtos;

import bridge.enums.BridgeCell;
import bridge.domain.MapState;
import java.util.List;

public record MapStateDto(List<SingleMapState> results) {
    public record SingleMapState(BridgeCell userAnswer, boolean isCorrect) {
    }

    public static MapStateDto from(MapState mapState) {
        return new MapStateDto(mapState.getMapStates());
    }
}
