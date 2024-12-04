package bridge;

import bridge.controller.Controller;
import bridge.controller.RetryInputUtil;
import bridge.domain.BridgeMaker;
import bridge.domain.generator.BridgeRandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RetryInputUtil retryInputUtil = new RetryInputUtil(inputView, outputView);
        Controller controller = new Controller(bridgeMaker, outputView, retryInputUtil);

        controller.run();
    }
}
