package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.PurchasedLottos;
import lotto.utils.Rank;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class OutputView {

    private static final int PRIZE_AMOUNT_FIRST = 2_000_000_000;
    private static final int PRIZE_AMOUNT_SECOND = 30_000_000;
    private static final int PRIZE_AMOUNT_THIRD = 1_500_000;
    private static final int PRIZE_AMOUNT_FOURTH = 50_000;
    private static final int PRIZE_AMOUNT_FIFTH = 5_000;

    private static final String OUTPUT_PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String OUTPUT_STATISTICS_MESSAGE = "당첨 통계";
    private static final String OUTPUT_STATISTICS_SEPARATOR = "---";
    private static final String OUTPUT_STATISTICS_RESULT = "%d개 일치 %s - %d개\n";
    private static final String OUTPUT_STATISTICS_RESULT_FOR_SECOND = "%d개 일치, 보너스 볼 일치 %s - %d개\n";

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {

        int count = purchasedLottos.size();
        System.out.printf(OUTPUT_PURCHASE_COUNT_MESSAGE, count);

        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(formatLotto(lotto));
        }
    }

    public void printStatistics(LottoResult result) {

        System.out.println(OUTPUT_STATISTICS_MESSAGE);
        System.out.println(OUTPUT_STATISTICS_SEPARATOR);

        Map<Rank, Integer> statistics = result.getResult();
        double rateOfReturn = result.getRateOfReturn();

        for (Rank rank : Rank.getOutputOrder()) {

            int count = statistics.getOrDefault(rank, 0);
            String money = convertPrizeToString(rank.getPrizeAmount());

            if (rank.isMatchBonus()) {
                System.out.printf(OUTPUT_STATISTICS_RESULT_FOR_SECOND, rank.getCount(), money, count);
                continue;
            }

            System.out.printf(OUTPUT_STATISTICS_RESULT, rank.getCount(), money, count);
        }

        String formattedRateOfReturn = convertRateOfReturnToString(rateOfReturn);
        System.out.println("총 수익률은 " + formattedRateOfReturn + "%입니다.");
    }

    private String convertRateOfReturnToString(double rateOfReturn) {
        return String.format("%,.1f", rateOfReturn);
    }

    private String convertPrizeToString(long prize) {

        StringBuilder sb = new StringBuilder();
        sb.append("(");

        String formatPrize = String.format("%,d", prize);
        sb.append(formatPrize);

        sb.append("원)");
        return sb.toString();
    }

    private String formatLotto(Lotto lotto) {

        List<Integer> numbers = lotto.getLotto();

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        String numbersString = numbers.stream()
                .map(Object::toString)
                .collect(joining(", "));

        sb.append(numbersString);
        sb.append("]");

        return sb.toString();
    }
}
