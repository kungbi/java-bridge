package bridge.domain;

public class GameStates {
    public static final int DEFAULT_PLAYER_POSITION = 0;
    public static final boolean BEING_PLAYED = false;
    public static final int DEFAULT_TRY_COUNT = 1;

    private final int bridgeSize;
    private int playerPosition = DEFAULT_PLAYER_POSITION;
    private boolean gameOver = BEING_PLAYED;
    private int tryCount = DEFAULT_TRY_COUNT;

    public GameStates(int bridgeSize) {
        this.bridgeSize = bridgeSize;
    }

    public void reset() {
        playerPosition = DEFAULT_PLAYER_POSITION;
        gameOver = BEING_PLAYED;
        tryCount += 1;
    }

    public void movePlayer() {
        if (this.bridgeSize <= this.playerPosition) {
            throw new IllegalStateException();
        }
        this.playerPosition += 1;
    }

    public boolean isComplete() {
        return this.playerPosition == this.bridgeSize;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public int getTryCount() {
        return tryCount;
    }
}
