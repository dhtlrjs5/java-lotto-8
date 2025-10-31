package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH",      // 보너스는 5등을 제외하면 의미 없음을 증명
            "4, false, FOURTH",
            "3, true, FIFTH",
            "3, false, FIFTH",
            "2, false, MISS",
            "1, false, MISS",
            "0, false, MISS"
    })
    @DisplayName("일치하는 번호 개수와 보너스볼 일치 여부에 따라 올바른 등수를 반환해야 한다.")
    void getRankTest(long matchCount, boolean bonus, Rank expectedRank) {
        //when
        Rank actualRank = Rank.getRank(matchCount, bonus);

        //then
        assertThat(actualRank).isEqualTo(expectedRank);
    }
}