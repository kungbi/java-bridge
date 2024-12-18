package bridge.view;

import bridge.enums.BridgeCell;
import bridge.dtos.GameResultDto;
import bridge.dtos.MapStateDto;
import bridge.dtos.MapStateDto.SingleMapState;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printStaringMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
        printNewLine();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(MapStateDto MapState) {
        List<SingleMapState> results = MapState.results();
        printUpResult(results);
        printNewLine();
        printDownResult(results);
        printNewLine();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(GameResultDto gameResult) {
        printNewLine();
        System.out.println("최종 게임 결과");
        this.printMap(gameResult.mapState());
        printNewLine();

        System.out.printf("게임 성공 여부: %s", gameResult.getGameResult());
        printNewLine();

        System.out.printf("총 시도한 횟수: %d", gameResult.tryCount());
        printNewLine();
    }

    public void printError(String error) {
        System.out.println("[ERROR] " + error);
        System.out.println();
    }

    private void printUpResult(List<SingleMapState> results) {
        System.out.print("[ ");
        for (int i = 0; i < results.size(); i++) {
            printSingleResult(results.get(i), BridgeCell.UP);
            if (i != results.size() - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print(" ]");
    }

    private void printDownResult(List<SingleMapState> results) {
        System.out.print("[ ");
        for (int i = 0; i < results.size(); i++) {
            printSingleResult(results.get(i), BridgeCell.DOWN);
            if (i != results.size() - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print(" ]");
    }

    private void printSingleResult(SingleMapState singleResult, BridgeCell cell) {
        if (singleResult.userAnswer() == cell) {
            if (singleResult.isCorrect()) {
                System.out.print("O");
                return;
            }
            System.out.print("X");
            return;
        }
        System.out.print(" ");
    }

    private void printNewLine() {
        System.out.println();
    }
}
