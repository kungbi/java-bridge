package bridge.domain;

import bridge.domain.generator.BridgeNumberGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BridgeMakerTest {

    static BridgeNumberGenerator getBridgeNumberGenerator() {
        return new BridgeNumberGenerator() {
            List<Integer> randomList = List.of(1, 0, 1, 0, 1);
            int index = 0;

            @Override
            public int generate() {
                return randomList.get(index++);
            }
        };
    }

    @Test
    void 다리_생성_테스트() {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(getBridgeNumberGenerator());

        // when
        List<String> bridge = bridgeMaker.makeBridge(5);

        // then
        Assertions.assertThat(bridge).isEqualTo(List.of("U", "D", "U", "D", "U"));
    }

}