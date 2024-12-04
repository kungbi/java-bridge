package bridge.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameStatesTest {

    @Test
    void 플레이어_이동() {
        // given
        GameStates gameStates = new GameStates(3);

        // when
        gameStates.movePlayer();

        // then
        Assertions.assertThat(gameStates.getPlayerPosition()).isEqualTo(1);
    }

    @Test
    void 플레이어가_목적지에_도착했는지_확인_도착() {
        // given
        GameStates gameStates = new GameStates(3);
        gameStates.movePlayer();
        gameStates.movePlayer();
        gameStates.movePlayer();

        // when then
        Assertions.assertThat(gameStates.isComplete()).isTrue();
    }

    @Test
    void 플레이어가_목적지에_도착했는지_확인_미도착() {
        // given
        GameStates gameStates = new GameStates(3);
        gameStates.movePlayer();
        gameStates.movePlayer();

        // when then
        Assertions.assertThat(gameStates.isComplete()).isFalse();
    }

    @Test
    void 게임이_끝났는지_확인_종료_상태() {
        GameStates gameStates = new GameStates(3);
        gameStates.setGameOver();

        Assertions.assertThat(gameStates.isGameOver()).isTrue();
    }

    @Test
    void 게임이_끝났는지_확인_게임중_상태() {
        GameStates gameStates = new GameStates(3);

        Assertions.assertThat(gameStates.isGameOver()).isFalse();
    }

    @Test
    void 게임_초기화() {
        // given
        GameStates gameStates = new GameStates(3);
        gameStates.movePlayer();
        gameStates.movePlayer();
        gameStates.setGameOver();

        // when
        gameStates.reset();

        // then
        Assertions.assertThat(gameStates.getPlayerPosition()).isEqualTo(0);
        Assertions.assertThat(gameStates.isComplete()).isFalse();
        Assertions.assertThat(gameStates.isGameOver()).isFalse();
    }
}