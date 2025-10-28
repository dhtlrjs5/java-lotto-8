package lotto.controller;

import lotto.view.InputView;

public class LottoController {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private static final String INVALID_PURCHASE_AMOUNT_NOT_NUMBER =
            ERROR_PREFIX + "금액은 숫자(양수)만 입력 가능합니다.";

    private static final String INVALID_PURCHASE_AMOUNT_UNIT =
            ERROR_PREFIX + "금액은 1,000원 단위로 입력해야 합니다.";

    private static final String INVALID_PURCHASE_AMOUNT_RANGE =
            ERROR_PREFIX + "금액이 너무 큽니다.";

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Long lottoMoney = getLottoMoney();
    }

    private Long getLottoMoney() {
        while (true) {
            String lottoMoney = inputView.inputLottoPrice();

            try {
                return validate(lottoMoney);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private long validate(String lottoMoney) {

        if (!isNumeric(lottoMoney)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_NOT_NUMBER);
        }

        long parsedMoney = parseToLongSafely(lottoMoney);

        if (parsedMoney % 1000 != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_UNIT);
        }

        return parsedMoney;
    }

    private boolean isNumeric(String lottoMoney) {
        return lottoMoney.matches("^[0-9]+$");
    }

    private long parseToLongSafely(String lottoMoney) {

        try {
            return Long.parseLong(lottoMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_RANGE);
        }
    }
}
