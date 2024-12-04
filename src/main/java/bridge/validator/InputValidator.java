package bridge.validator;

public class InputValidator {
    public static void validateBridgeSize(int size) {
        if (!(3 <= size && size <= 20)) {
            throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }
}
