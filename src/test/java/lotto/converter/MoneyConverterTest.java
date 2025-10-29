package lotto.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyConverterTest {

    private final MoneyConverter converter = new MoneyConverter();

    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "15000", "987654321000"})
    @DisplayName("유효한 금액의 문자열이 long타입으로 변환")
    void convertStringToLongTest(String rawMoney) {
        //when
        long money = converter.convertStringToLong(rawMoney);

        //then
        assertThat(money).isEqualTo(Long.parseLong(rawMoney));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "1,000", "1 000", "1000L", "1000a"})
    @DisplayName("입력값이 숫자가 아닐 경우 IllegalArgumentException 발생")
    void throwExceptionWhenInputIsNotNumber(String invalidInput) {
        //when, then
        assertThatThrownBy(() -> converter.convertStringToLong(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("입력값이 공백이거나 null일 경우 IllegalArgumentException 발생")
    void throwExceptionWhenInputIsEmptyOrNull(String invalidInput) {
        //when, then
        assertThatThrownBy(() -> converter.convertStringToLong(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("입력값이 Long 타입 범위를 초과할 경우 IllegalArgumentException 발생")
    void throw_exception_when_input_exceeds_long_range() {
        //given
        String veryLargeNumber = "100000000000000000000000000";

        //when, then
        assertThatThrownBy(() -> converter.convertStringToLong(veryLargeNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}