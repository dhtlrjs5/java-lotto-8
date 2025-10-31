package lotto.domain;

import lotto.utils.Rank;

import java.util.*;

public class PurchasedLottos implements Iterable<Lotto> {

    private final List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lotteries) {
        this.lottos = lotteries;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return Collections.unmodifiableList(lottos).iterator();
    }

    public int size() {
        return lottos.size();
    }

    public Map<Rank, Integer> calculateStatistics(WinningLotto winningLotto) {

        Map<Rank, Integer> statistics = new HashMap<>();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }

        return statistics;
    }
}
