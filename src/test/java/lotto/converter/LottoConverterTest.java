package lotto.converter;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoConverterTest {

    private static final Lotto VALID_LOTTO = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

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
            "1a,2,3,4,5,6", "a,1,2,3,4,5", "1 2 3 4 5 6",
            "-1,2,3,4,5,6", "0,1,2,3,4,5", "1,2,3,4,5,46",
            "1,2,3,4,5,10000000000"
    })
    @DisplayName("로또 번호가 양수가 아니거나 구분자가 잘못 입력되면 IllegalArgumentException 발생")
    void throwExceptionWhenWrongInputLotto(String input) {
        // when, then
        assertThatThrownBy(() -> converter.convertStringToLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("유효한 보너스 번호가 입력되면 성공")
    void convertToWinningLottoTest() {
        //given
        String bonusNumber = "7";

        //when
        WinningLotto winningLotto = converter.convertToWinningLotto(VALID_LOTTO, bonusNumber);

        //then
        assertThat(winningLotto).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1", "1,5", "10000000000", " ", "0", "-1"})
    @DisplayName("보너스 번호의 입력이 잘못되면 IllegalArgumentException 발생")
    void throwExceptionWhenWrongInputBonusNumber(String input) {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when, then
        assertThatThrownBy(() -> converter.convertToWinningLotto(lotto, input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}