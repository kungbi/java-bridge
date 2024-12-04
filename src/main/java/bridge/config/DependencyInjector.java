package bridge.config;


import bridge.InputView;
import bridge.OutputView;
import bridge.controller.Controller;
import bridge.controller.RetryInputUtil;
import bridge.domain.BridgeMaker;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.domain.generator.BridgeRandomNumberGenerator;

public class DependencyInjector {
    public static InputView inputView;
    public static OutputView outputView;
    public static RetryInputUtil retryInputUtil;
    public static BridgeMaker bridgeMaker;
    public static BridgeNumberGenerator bridgeNumberGenerator;
    private static Controller controller;

    public static BridgeNumberGenerator getBridgeNumberGenerator() {
        if (bridgeNumberGenerator == null) {
            bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        }
        return bridgeNumberGenerator;
    }

    public Controller createController() {
        if (controller == null) {
            controller = new Controller(
                    getBridgeMaker(),
                    getOutputView(),
                    getRetryInputUtil()
            );
        }
        return controller;
    }

    private static InputView getInputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private static OutputView getOutputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private static RetryInputUtil getRetryInputUtil() {
        if (retryInputUtil == null) {
            retryInputUtil = new RetryInputUtil(getInputView(), getOutputView());
        }
        return retryInputUtil;
    }

    private static BridgeMaker getBridgeMaker() {
        if (bridgeMaker == null) {
            bridgeMaker = new BridgeMaker(getBridgeNumberGenerator());
        }
        return bridgeMaker;
    }
}
