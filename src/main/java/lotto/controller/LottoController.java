package lotto.controller;

import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.PurchasedLottos;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final LottoConverter lottoConverter;
    private final MoneyConverter moneyConverter;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService, LottoConverter lottoConverter, MoneyConverter moneyConverter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.lottoConverter = lottoConverter;
        this.moneyConverter = moneyConverter;
    }

    public void run() {
        LottoMoney money = getLottoMoney();
        PurchasedLottos purchasedLottos = lottoService.buyLottos(money);

        outputView.printPurchasedLottos(purchasedLottos);

        WinningLotto winningLotto = getWinningLotto();
    }

    private WinningLotto getWinningLotto() {

        Lotto winningNumber = getWinningNumber();

        while (true) {
            try {
                String bonusNumber = inputView.inputBonusNumber();
                return lottoConverter.convertToWinningLotto(winningNumber, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto getWinningNumber() {
        Lotto winningLotto;

        while (true) {
            try {
                String numbers = inputView.inputWinningNumber();
                winningLotto = lottoConverter.convertStringToLotto(numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return winningLotto;
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
