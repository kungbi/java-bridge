package bridge.enums;

public enum GameCommand {
    QUIT("Q"),
    RETRY("R");

    private final String value;

    GameCommand(String command) {
        this.value = command;
    }

    public static GameCommand findByValue(String value) {
        for (GameCommand gameCommand : values()) {
            if (gameCommand.value.equals(value.trim())) {
                return gameCommand;
            }
        }
        throw new IllegalArgumentException();
    }
}
