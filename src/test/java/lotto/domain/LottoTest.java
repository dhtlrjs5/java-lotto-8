package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 로또 번호를 입력하면 정상 작동")
    void inputLottoNumberTest() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        //when
        Lotto lotto = new Lotto(numbers);
        List<Integer> getLotto = lotto.getLotto();

        //then
        assertThat(getLotto).isEqualTo(numbers);
    }

    @Test
    @DisplayName("로또 당첨 번호에 null이 있는 경우 IllegalArgumentException 발생")
    void throwExceptionWhenLottoContainsNull() {
        //given
        List<Integer> numbersIncludeNull = new ArrayList<>(Arrays.asList(1, 2, 3, null, 5, 6));

        //when, then
        assertThatThrownBy(() -> new Lotto(numbersIncludeNull))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("로또 당첨 번호가 1~45가 아닌 경우 IllegalArgumentException 발생")
    void throwExceptionWhenDelimiterIsNotComma() {
        //given
        List<Integer> numbers = List.of(0, 2, 3, 45, 5, 46);

        //when, then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개 미만인 경우 IllegalArgumentException 발생")
    void throwExceptionWhenLessThanSix() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        //when, then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
