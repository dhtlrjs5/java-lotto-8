package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.PurchasedLottos;

import java.util.List;

import static java.util.stream.Collectors.*;

public class OutputView {

    private static final int PRIZE_AMOUNT_FIRST = 2_000_000_000;
    private static final int PRIZE_AMOUNT_SECOND = 30_000_000;
    private static final int PRIZE_AMOUNT_THIRD = 1_500_000;
    private static final int PRIZE_AMOUNT_FOURTH = 50_000;
    private static final int PRIZE_AMOUNT_FIFTH = 5_000;

    private static final String OUTPUT_PURCHASE_COUNT = "%d개를 구매했습니다.\n";

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {

        int count = purchasedLottos.size();
        System.out.printf(OUTPUT_PURCHASE_COUNT, count);

        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(formatLotto(lotto));
        }
    }

    public String formatLotto(Lotto lotto) {

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
