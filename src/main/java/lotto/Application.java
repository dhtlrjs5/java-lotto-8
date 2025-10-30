package lotto;

import lotto.controller.LottoController;
import lotto.converter.LottoConverter;
import lotto.converter.MoneyConverter;
import lotto.domain.LottoGenerator;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator generator = new LottoGenerator();
        LottoService lottoService = new LottoService(generator);
        LottoResultService lottoResultService = new LottoResultService();
        LottoConverter lottoConverter = new LottoConverter();
        MoneyConverter moneyConverter = new MoneyConverter();

        LottoController controller = new LottoController(
                inputView, outputView, lottoService, lottoResultService, lottoConverter, moneyConverter);

        controller.run();
    }
}
