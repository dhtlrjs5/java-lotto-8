package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMoney;
import lotto.domain.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    private final LottoGenerator generator;

    public LottoService(LottoGenerator generator) {
        this.generator = generator;
    }

    public PurchasedLottos buyLottos(LottoMoney money) {

        int count = calculateCount(money);

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generator.generateLotto());
        }

        return new PurchasedLottos(lottos);
    }

    private int calculateCount(LottoMoney money) {

        return (int) (money.getMoney() / 1000);
    }
}
