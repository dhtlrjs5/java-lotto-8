package lotto;

import lotto.controller.LottoController;
import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        LottoConverter lottoConverter = new LottoConverter();
        MoneyConverter moneyConverter = new MoneyConverter();

        LottoController controller = new LottoController(inputView, lottoConverter, moneyConverter);
        controller.run();
    }
}
