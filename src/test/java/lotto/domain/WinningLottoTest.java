package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("올바른 보너스 번호를 입력하면 정상 작동")
    void inputBonusNumberTest() {
        //given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        //when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        //then
        assertThat(winningLotto.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @Test
    @DisplayName("보너스 번호가 로또 번호랑 중복인 경우 IllegalArgumentException 발생")
    void throwExceptionWhenBonusNumberIsDuplicate() {
        //given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;

        //when, then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 1000000000})
    @DisplayName("보너스 번호가 1~45가 아닌 경우 IllegalArgumentException 발생")
    void throwExceptionWhenBonusNumberNotInRange(int bonusNumber) {
        //given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when, then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}