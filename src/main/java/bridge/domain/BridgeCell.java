package bridge.domain;

public enum BridgeCell {
    UP("U"),
    DOWN("D");

    private final String value;

    BridgeCell(String value) {
        this.value = value;
    }

    public static BridgeCell findByValue(String value) {
        for (BridgeCell bridgeCellType : BridgeCell.values()) {
            if (bridgeCellType.value.equals(value)) {
                return bridgeCellType;
            }
        }
        throw new IllegalArgumentException();
    }
}
