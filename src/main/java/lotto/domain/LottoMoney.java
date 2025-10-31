package lotto.domain;

public class LottoMoney {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private static final String ERROR_INVALID_PURCHASE_AMOUNT_UNIT =
            ERROR_PREFIX + "금액은 1,000원 단위로 입력해야 합니다.";

    private final long money;

    public LottoMoney(long money) {
        validate(money);
        this.money = money;
    }

    public int calculateCount() {
        return (int) (money / 1000);
    }

    public double calculateRateOfReturn(long totalPrize) {

        double rateOfReturn = ((double) totalPrize / money) * 100.0;

        return Math.round(rateOfReturn * 10.0) / 10.0;
    }

    private void validate(long money) {

        if (money % 1000 != 0 || money <= 0) {
            throw new IllegalArgumentException(ERROR_INVALID_PURCHASE_AMOUNT_UNIT);
        }
    }
}

