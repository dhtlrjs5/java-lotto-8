package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.PurchasedLottos;
import lotto.utils.Rank;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class OutputView {

    private static final String OUTPUT_PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String OUTPUT_STATISTICS_MESSAGE = "당첨 통계";
    private static final String OUTPUT_STATISTICS_SEPARATOR = "---";
    private static final String OUTPUT_STATISTICS_RESULT = "%d개 일치 %s - %d개\n";
    private static final String OUTPUT_STATISTICS_RESULT_FOR_SECOND = "%d개 일치, 보너스 볼 일치 %s - %d개\n";
    private static final String OUTPUT_TOTAL_RATE_OF_RETURN = "총 수익률은 %s%%입니다.";

    private static final String PRIZE_FORMAT_PREFIX = "(";
    private static final String PRIZE_FORMAT_SUFFIX = "원)";
    private static final String LOTTO_FORMAT_PREFIX = "[";
    private static final String LOTTO_FORMAT_SUFFIX = "]";

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
        System.out.printf(OUTPUT_TOTAL_RATE_OF_RETURN, formattedRateOfReturn);
    }

    private String convertRateOfReturnToString(double rateOfReturn) {
        return String.format("%,.1f", rateOfReturn);
    }

    private String convertPrizeToString(long prize) {

        StringBuilder sb = new StringBuilder();
        sb.append(PRIZE_FORMAT_PREFIX);

        String formatPrize = String.format("%,d", prize);
        sb.append(formatPrize);

        sb.append(PRIZE_FORMAT_SUFFIX);
        return sb.toString();
    }

    private String formatLotto(Lotto lotto) {

        List<Integer> numbers = lotto.getLotto();

        StringBuilder sb = new StringBuilder();
        sb.append(LOTTO_FORMAT_PREFIX);

        String numbersString = numbers.stream()
                .map(Object::toString)
                .collect(joining(", "));

        sb.append(numbersString);
        sb.append(LOTTO_FORMAT_SUFFIX);

        return sb.toString();
    }
}
