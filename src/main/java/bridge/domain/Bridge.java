package bridge.domain;

import java.util.List;

public class Bridge {
    private final List<BridgeCell> bridge;

    private Bridge(List<BridgeCell> bridge) {
        if (!(3 <= bridge.size() && bridge.size() <= 20)) {
            throw new IllegalArgumentException("Bridge must contain 3 or more bridges");
        }
        this.bridge = bridge;
    }

    public int size() {
        return bridge.size();
    }

    public BridgeCell get(int index) {
        return bridge.get(index);
    }

    public static Bridge from(List<String> bridge) {
        return new Bridge(
                bridge.stream().map(BridgeCell::findByValue).toList()
        );
    }

}
