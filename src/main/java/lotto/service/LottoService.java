package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.LottoGenerator.generateLotto;


public class LottoService {

    public PurchasedLottos buyLottos(LottoMoney money) {

        int count = calculateCount(money);

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }

        return new PurchasedLottos(lottos);
    }

    private int calculateCount(LottoMoney money) {

        return (int) (money.getMoney() / 1000);
    }
}
