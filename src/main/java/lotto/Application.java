package lotto;

import lotto.controller.LottoController;
import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.domain.LottoGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        LottoGenerator generator = new LottoGenerator();
        LottoService lottoService = new LottoService(generator);
        LottoConverter lottoConverter = new LottoConverter();
        MoneyConverter moneyConverter = new MoneyConverter();

        LottoController controller = new LottoController(inputView, lottoService, lottoConverter, moneyConverter);
        controller.run();
    }
}
