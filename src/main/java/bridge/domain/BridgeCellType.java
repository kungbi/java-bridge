package bridge.domain;

public enum BridgeCellType {
    UP("U"),
    DOWN("D");

    private final String value;

    BridgeCellType(String value) {
        this.value = value;
    }

    public static BridgeCellType findByValue(String value) {
        for (BridgeCellType bridgeCellType : BridgeCellType.values()) {
            if (bridgeCellType.value.equals(value)) {
                return bridgeCellType;
            }
        }
        throw new IllegalArgumentException();
    }
}
