package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.PurchasedLottos;
import lotto.utils.Rank;

import java.util.Map;

import static lotto.utils.LottoFormatter.*;

public class OutputView {

    private static final String OUTPUT_PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String OUTPUT_STATISTICS_MESSAGE = "당첨 통계";
    private static final String OUTPUT_STATISTICS_SEPARATOR = "---";
    private static final String OUTPUT_STATISTICS_RESULT = "%d개 일치 %s - %d개\n";
    private static final String OUTPUT_STATISTICS_RESULT_FOR_SECOND = "%d개 일치, 보너스 볼 일치 %s - %d개\n";
    private static final String OUTPUT_TOTAL_RATE_OF_RETURN = "총 수익률은 %s%%입니다.";

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {

        int count = purchasedLottos.size();
        System.out.printf(OUTPUT_PURCHASE_COUNT_MESSAGE, count);

        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(formatLotto(lotto));
        }
    }

    public void printStatistics(LottoResult result) {

        printHeader();
        printStatistics(result.getResult());
        printRateOfReturn(result.getRateOfReturn());
    }


    private static void printHeader() {
        System.out.println(OUTPUT_STATISTICS_MESSAGE);
        System.out.println(OUTPUT_STATISTICS_SEPARATOR);
    }

    private static void printStatistics(Map<Rank, Integer> statistics) {
        for (Rank rank : Rank.getOutputOrder()) {

            int count = statistics.getOrDefault(rank, 0);
            String money = formatPrize(rank.getPrizeAmount());

            if (rank.isMatchBonus()) {
                System.out.printf(OUTPUT_STATISTICS_RESULT_FOR_SECOND, rank.getCount(), money, count);
                continue;
            }

            System.out.printf(OUTPUT_STATISTICS_RESULT, rank.getCount(), money, count);
        }
    }

    private static void printRateOfReturn(double rateOfReturn) {
        String formattedRateOfReturn = formatRateOfReturn(rateOfReturn);
        System.out.printf(OUTPUT_TOTAL_RATE_OF_RETURN, formattedRateOfReturn);
    }
}
