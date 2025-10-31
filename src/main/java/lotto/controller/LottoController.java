package lotto.controller;

import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.domain.*;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final LottoResultService lottoResultService;
    private final LottoConverter lottoConverter;
    private final MoneyConverter moneyConverter;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoService lottoService,
            LottoResultService lottoResultService,
            LottoConverter lottoConverter,
            MoneyConverter moneyConverter
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.lottoResultService = lottoResultService;
        this.lottoConverter = lottoConverter;
        this.moneyConverter = moneyConverter;
    }

    public void run() {
        LottoMoney money = getLottoMoney();
        PurchasedLottos purchasedLottos = lottoService.buyLottos(money);

        outputView.printPurchasedLottos(purchasedLottos);

        WinningLotto winningLotto = getWinningLotto();

        LottoResult result = lottoResultService.calculateStatistics(purchasedLottos, winningLotto, money);
        outputView.printStatistics(result);
    }

    private WinningLotto getWinningLotto() {

        Lotto winningNumber = getWinningNumber();

        while (true) {
            try {
                return tryGetBonusNumber(winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto tryGetBonusNumber(Lotto winningNumber) {

        String bonusNumber = inputView.inputBonusNumber();
        return lottoConverter.convertToWinningLotto(winningNumber, bonusNumber);
    }

    private Lotto getWinningNumber() {

        while (true) {
            try {
                return tryGetWinningNumber();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto tryGetWinningNumber() {

        String numbers = inputView.inputWinningNumber();
        return lottoConverter.convertStringToLotto(numbers);
    }

    private LottoMoney getLottoMoney() {

        while (true) {
            try {
                return tryGetLottoMoney();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LottoMoney tryGetLottoMoney() {

        String lottoMoney = inputView.inputLottoPrice();
        long money = moneyConverter.convertStringToLong(lottoMoney);
        return new LottoMoney(money);
    }
}
