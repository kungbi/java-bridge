package bridge.domain;

import bridge.dtos.MapStateDto.SingleMapState;
import java.util.ArrayList;
import java.util.List;

public class MapState {
    private List<SingleMapState> mapStates;

    public MapState() {
        mapStates = new ArrayList<>();
    }

    public void reset() {
        mapStates.clear();
    }

    public void add(SingleMapState singleMapState) {
        this.mapStates.add(singleMapState);
    }

    public List<SingleMapState> getMapStates() {
        return List.copyOf(mapStates);
    }
}
