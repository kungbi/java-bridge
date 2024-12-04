package bridge.domain;

public class BridgeCell {
    private final String cellType;

    public BridgeCell(String cellType) {
        if (!cellType.equals("D") && !cellType.equals("U")) {
            throw new IllegalArgumentException("Invalid cell type: " + cellType);
        }
        this.cellType = cellType;
    }

    public String getCellType() {
        return cellType;
    }
}
