package lotto.domain;

import lotto.utils.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

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
        assertThat(winningLotto).isNotNull();
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

    @ParameterizedTest
    // 당첨 번호: [1, 2, 3, 4, 5, 6], 보너스 번호: 7
    @CsvSource(value = {
            "1,2,3,4,5,6, FIRST",    // 6개 일치
            "1,2,3,4,5,7, SECOND",   // 5개 일치, 보너스 일치
            "1,2,3,4,5,10, THIRD",   // 5개 일치, 보너스 불일치
            "1,2,3,4,8,9, FOURTH",   // 4개 일치
            "1,2,3,8,9,10, FIFTH",   // 3개 일치
            "1,2,8,9,10,11, MISS"    // 2개 일치
    })
    @DisplayName("구매 로또와 당첨 로또를 비교하여 올바른 Rank 반환")
    void matchLottoTest(int n1, int n2, int n3, int n4, int n5, int n6, Rank expectedRank) {
        //given (당첨 번호: [1, 2, 3, 4, 5, 6] / 보너스 번호: 7)
        Lotto winningLottoNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, 7);

        List<Integer> purchasedNumbers = List.of(n1, n2, n3, n4, n5, n6);
        Lotto purchasedLotto = new Lotto(purchasedNumbers);

        //when
        Rank actualRank = winningLotto.match(purchasedLotto);

        //then
        assertThat(actualRank).isEqualTo(expectedRank);
    }
}