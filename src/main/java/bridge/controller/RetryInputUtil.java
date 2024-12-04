package bridge.controller;

import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.enums.BridgeCell;
import bridge.enums.GameCommand;
import bridge.validator.InputValidator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RetryInputUtil {
    private final InputView inputView;
    private final OutputView outputView;

    public RetryInputUtil(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int readBridgeSize() {
        return this.retryLogics(inputView::readBridgeSize, InputParser::parseInt, InputValidator::validateBridgeSize);
    }

    public BridgeCell readMoving() {
        return this.retryLogics(inputView::readMoving, BridgeCell::findByValue);
    }

    public GameCommand readGameCommand() {
        return this.retryLogics(inputView::readGameCommand, GameCommand::findByValue);
    }

    private <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser,
                              Consumer<T> validator) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                T parsedInput = parser.apply(userInput);
                validator.accept(parsedInput);
                return parsedInput;
            } catch (IllegalArgumentException error) {
                outputView.printError(error.getMessage());
            }
        }
    }

    private <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                return parser.apply(userInput);
            } catch (IllegalArgumentException error) {
                outputView.printError(error.getMessage());
            }
        }
    }
}
