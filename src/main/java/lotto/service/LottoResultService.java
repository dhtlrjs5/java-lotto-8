package lotto.service;

import lotto.domain.*;
import lotto.utils.Rank;

import java.util.HashMap;
import java.util.Map;

public class LottoResultService {

    public LottoResult calculateStatistics(
            PurchasedLottos purchasedLottos,
            WinningLotto winningLotto,
            LottoMoney money
    ) {
        Map<Rank, Integer> statistics = new HashMap<>();

        for (Lotto lotto : purchasedLottos.getLottos()) {
            Rank rank = winningLotto.match(lotto);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }

        double rateOfReturn = calculateRateOfReturn(statistics, money);

        return new LottoResult(statistics, rateOfReturn);
    }

    private double calculateRateOfReturn(Map<Rank, Integer> statistics, LottoMoney lottoMoney) {

        long totalPrize = calculateTotalPrize(statistics);
        long money = lottoMoney.getMoney();

        double rateOfReturn = ((double) totalPrize / money) * 100.0;

        return Math.round(rateOfReturn * 10.0) / 10.0;
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
