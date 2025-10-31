package lotto.domain;

import lotto.utils.Rank;

import java.util.Map;

/**
 * @param result value: 당첨된 개수 */
public record LottoResult(Map<Rank, Integer> result, double rateOfReturn) {

    public LottoResult(Map<Rank, Integer> result, double rateOfReturn) {
        this.result = Map.copyOf(result);
        this.rateOfReturn = rateOfReturn;
    }
}
