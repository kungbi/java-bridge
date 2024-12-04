package bridge.domain;

public class BridgeCell {
    private final BridgeCellType cellType;

    public BridgeCell(BridgeCellType cellType) {
        this.cellType = cellType;
    }

    public BridgeCellType getCellType() {
        return cellType;
    }
}
