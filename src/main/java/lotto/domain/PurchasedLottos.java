package lotto.domain;

import java.util.Collections;
import java.util.List;

public class PurchasedLottos {

    private final List<Lotto> lotteries;

    public PurchasedLottos(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lotteries);
    }

    public int size() {
        return lotteries.size();
    }
}
