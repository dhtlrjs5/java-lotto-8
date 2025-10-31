package lotto.utils;

import lotto.domain.Lotto;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class LottoFormatter {

    private static final String PRIZE_FORMAT_PREFIX = "(";
    private static final String PRIZE_FORMAT_SUFFIX = "원)";
    private static final String LOTTO_FORMAT_PREFIX = "[";
    private static final String LOTTO_FORMAT_SUFFIX = "]";
    private static final String DELIMITER = ", ";
    private static final String PRIZE_FORMAT = "%,d";
    private static final String RATE_OF_RETURN_FORMAT = "%,.1f";

    private LottoFormatter() {
    }

    public static String formatLotto(Lotto lotto) {

        List<Integer> numbers = lotto.getLotto();

        StringBuilder sb = new StringBuilder();
        sb.append(LOTTO_FORMAT_PREFIX);

        String numbersString = numbers.stream()
                .map(Object::toString)
                .collect(joining(DELIMITER));

        sb.append(numbersString);
        sb.append(LOTTO_FORMAT_SUFFIX);

        return sb.toString();
    }

    public static String formatPrize(long prize) {

        StringBuilder sb = new StringBuilder();
        sb.append(PRIZE_FORMAT_PREFIX);

        String formatPrize = String.format(PRIZE_FORMAT, prize);
        sb.append(formatPrize);

        sb.append(PRIZE_FORMAT_SUFFIX);
        return sb.toString();
    }

    public static String formatRateOfReturn(double rateOfReturn) {
        return String.format(RATE_OF_RETURN_FORMAT, rateOfReturn);
    }
}
