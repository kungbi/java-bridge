package bridge.domain;

import java.util.List;

public class Bridge {
    private final List<BridgeCell> bridge;

    public Bridge(List<BridgeCell> bridge) {
        if (!(3 <= bridge.size() && bridge.size() <= 20)) {
            throw new IllegalArgumentException("Bridge must contain 3 or more bridges");
        }
        this.bridge = bridge;
    }


}
