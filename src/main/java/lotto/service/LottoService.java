package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.LottoGenerator.generateLotto;


public class LottoService {

    public PurchasedLottos buyLottos(int money) {

        int count = money / 1000;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }

        return new PurchasedLottos(lottos);
    }
}
