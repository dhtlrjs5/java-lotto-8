package lotto.domain;

import java.util.Collections;
import java.util.List;

public class PurchasedLotteries {

    private final List<Lotto> lotteries;

    public PurchasedLotteries(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public List<Lotto> getLotteries() {
        return Collections.unmodifiableList(lotteries);
    }
}
