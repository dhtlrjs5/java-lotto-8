package lotto.controller;

import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.WinningLotto;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;
    private final LottoConverter lottoConverter;
    private final MoneyConverter moneyConverter;

    public LottoController(InputView inputView, LottoConverter lottoConverter, MoneyConverter moneyConverter) {
        this.inputView = inputView;
        this.lottoConverter = lottoConverter;
        this.moneyConverter = moneyConverter;
    }

    public void run() {
        LottoMoney money = getLottoMoney();
        WinningLotto winningLotto = getWinningLotto();
    }

    private WinningLotto getWinningLotto() {
        Lotto winningNumber = getWinningNumber();
        String bonusNumber = inputView.inputBonusNumber();

        return lottoConverter.convertToWinningLotto(winningNumber, bonusNumber);
    }

    private Lotto getWinningNumber() {
        String numbers = inputView.inputWinningNumber();

        return lottoConverter.convertStringToLotto(numbers);
    }

    private LottoMoney getLottoMoney() {
        while (true) {
            String lottoMoney = inputView.inputLottoPrice();

            try {
                long money = moneyConverter.convertStringToLong(lottoMoney);
                return new LottoMoney(money);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
