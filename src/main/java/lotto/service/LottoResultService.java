package lotto.service;

import lotto.domain.*;
import lotto.utils.Rank;

import java.util.Map;

public class LottoResultService {

    public LottoResult calculateStatistics(
            PurchasedLottos purchasedLottos,
            WinningLotto winningLotto,
            LottoMoney money
    ) {
        Map<Rank, Integer> statistics = purchasedLottos.calculateStatistics(winningLotto);

        long totalPrize = calculateTotalPrize(statistics);

        double rateOfReturn = money.calculateRateOfReturn(totalPrize);

        return new LottoResult(statistics, rateOfReturn);
    }

    private long calculateTotalPrize(Map<Rank, Integer> statistics) {

        long totalPrize = 0L;

        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();

            totalPrize += rank.getPrizeAmount() * count;
        }

        return totalPrize;
    }
}
