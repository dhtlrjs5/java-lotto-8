package lotto;

import lotto.controller.LottoController;
import lotto.converter.LottoConverter;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        LottoConverter lottoConverter = new LottoConverter();

        LottoController controller = new LottoController(inputView, lottoConverter);
        controller.run();
    }
}
