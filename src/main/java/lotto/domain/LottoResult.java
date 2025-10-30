package lotto.domain;

import lotto.utils.Rank;

import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> result;
    private final double rateOfReturn;

    public LottoResult(Map<Rank, Integer> result, double rateOfReturn) {
        this.result = Map.copyOf(result);
        this.rateOfReturn = rateOfReturn;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
