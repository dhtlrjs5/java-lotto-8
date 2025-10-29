package lotto.converter;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoConverterTest {

    private final LottoConverter converter = new LottoConverter();

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "45,40,35,30,25,20"})
    @DisplayName("유효한 형식의 문자열이 Lotto 객체로 성공적으로 변환")
    void convertStringToLottoTest(String rawNumbers) {
        // when
        Lotto lotto = converter.convertStringToLotto(rawNumbers);

        // then
        assertThat(lotto).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,6,", ",1,2,3,4,5,6", "1,2,,3,4,5",
            "1a,2,3,4,5,6", "a,1,2,3,4,5", "1 2 3 4 5 6"
    })
    @DisplayName("로또 번호가 숫자가 아니거나 구분자가 잘못 입력되면 IllegalArgumentException 발생")
    void throwExceptionWhenWrongInput(String input) {
        // when, then
        assertThatThrownBy(() -> converter.convertStringToLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}