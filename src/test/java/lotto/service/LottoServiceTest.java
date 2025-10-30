package lotto.service;

import lotto.domain.LottoGenerator;
import lotto.domain.LottoMoney;
import lotto.domain.PurchasedLottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private final LottoGenerator generator = new LottoGenerator();
    private final LottoService lottoService = new LottoService(generator);

    @Test
    @DisplayName("로또 구매시 금액에 맞게 구매되어야 한다.")
    void test() {
        //given
        int count = 8;
        LottoMoney money = new LottoMoney(8000L);

        //when
        PurchasedLottos purchasedLottos = lottoService.buyLottos(money);

        //then
        assertThat(purchasedLottos.size()).isEqualTo(count);
    }
}