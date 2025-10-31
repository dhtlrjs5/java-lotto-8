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

    public long getMoney() {
        return money;
    }

    private void validate(long money) {

        if (money % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_INVALID_PURCHASE_AMOUNT_UNIT);
        }
    }
}

