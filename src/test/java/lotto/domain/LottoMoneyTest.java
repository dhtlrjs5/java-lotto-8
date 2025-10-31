package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoMoneyTest {

    @ParameterizedTest
    @ValueSource(longs = {1000, 5000, 15000, 690000})
    @DisplayName("1000원 단위의 올바른 입력 성공 테스트")
    void inputLottoMoneyTest(long validMoney) {
        //when
        LottoMoney money = new LottoMoney(validMoney);

        //then
        assertThat(money).isNotNull();
        assertThat(money.getMoney()).isEqualTo(validMoney);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 10, 100, 999, 1001, 1500, 51601, 89498100, 0, -1, -1000})
    @DisplayName("구입 금액이 1000원 단위가 아닐 경우 IllegalArgumentException 발생")
    void throwExceptionWhenMoneyIsNotInThousand(long invalidMoney) {
        //when, then
        assertThatThrownBy(() -> new LottoMoney(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}