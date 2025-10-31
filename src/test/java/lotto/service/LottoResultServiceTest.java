package lotto.service;

import lotto.domain.*;
import lotto.utils.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static lotto.utils.Rank.*;
import static org.assertj.core.api.Assertions.*;

class LottoResultServiceTest {

    private final LottoResultService lottoResultService = new LottoResultService();
    private final WinningLotto winningLotto = new WinningLotto(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7
    );
    private final LottoMoney lottoMoney = new LottoMoney(8000L);
    
    /**
     * 당첨 번호: [1, 2, 3, 4, 5, 6], 보너스 번호: 7
     */
    @Test
    @DisplayName("구매 로또와 당첨 로또를 비교하여 올바른 통계를 만들어야됨")
    void calculateStatisticsTest() {
        //given
        PurchasedLottos purchasedLottos = getPurchasedLottos();

        //when
        LottoResult result = lottoResultService.calculateStatistics(purchasedLottos, winningLotto, lottoMoney);

        //then
        Map<Rank, Integer> statistics = result.getResult();

        assertThat(statistics.getOrDefault(FIRST, 0)).isEqualTo(0);
        assertThat(statistics.get(SECOND)).isEqualTo(1);
        assertThat(statistics.get(THIRD)).isEqualTo(1);
        assertThat(statistics.get(FOURTH)).isEqualTo(1);
        assertThat(statistics.get(FIFTH)).isEqualTo(1);
        assertThat(statistics.get(MISS)).isEqualTo(4);

        assertThat(result.getRateOfReturn()).isEqualTo(394437.5);
    }

    /**
     * 구매 로또
     * 1등: 0개
     * 2등: 1개
     * 3등: 1개
     * 4등: 1개
     * 5등: 1개
     * 꽝: 4개
     */
    private static PurchasedLottos getPurchasedLottos() {
        Lotto fifthLotto = new Lotto(List.of(1, 2, 3, 43, 44, 45));
        Lotto fourthLotto = new Lotto(List.of(1, 2, 3, 4, 44, 45));
        Lotto thirdLotto = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Lotto secondLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        return new PurchasedLottos(
                List.of(secondLotto, thirdLotto, fourthLotto, fifthLotto,
                        new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                        new Lotto(List.of(10, 11, 12, 13, 14, 16)),
                        new Lotto(List.of(10, 11, 12, 13, 14, 17)),
                        new Lotto(List.of(10, 11, 12, 13, 14, 18)))
        );
    }
}