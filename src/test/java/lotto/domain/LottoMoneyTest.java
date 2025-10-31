package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
        assertThat(money.calculateCount()).isEqualTo(validMoney / 1000);
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

    @ParameterizedTest
    @CsvSource(value = {
            "8000, 31555000, 394437.5", // 전체 실행 예시 (높은 값, 소수점 첫째 자리까지 정확)
            "10000, 1555, 15.6",       // 반올림 올림 (15.55 -> 15.6)
            "10000, 1554, 15.5",       // 반올림 버림 (15.54 -> 15.5)
            "10000, 1500, 15.0",       // 정수 비율 (15.0)
            "5000, 0, 0.0"             // 수익률 0%
    })
    @DisplayName("총 상금과 구입 금액을 통해 수익률이 올바르게 계산 및 반올림되는지 테스트")
    void calculateRateOfReturnTest(long purchaseMoney, long totalPrize, double expectedRate) {
        //given
        LottoMoney lottoMoney = new LottoMoney(purchaseMoney);

        //when
        double actualRate = lottoMoney.calculateRateOfReturn(totalPrize);

        //then
        assertThat(actualRate).isEqualTo(expectedRate);
    }
}