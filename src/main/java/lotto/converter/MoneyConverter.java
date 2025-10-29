package lotto.converter;

public class MoneyConverter {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private static final String INVALID_PURCHASE_AMOUNT_NOT_NUMBER =
            ERROR_PREFIX + "금액은 숫자(양수)만 입력 가능합니다.";

    private static final String INVALID_PURCHASE_AMOUNT_RANGE =
            ERROR_PREFIX + "금액이 너무 큽니다.";

    public long convertStringToLong(String rawMoney) {

        if (!isNumeric(rawMoney)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_NOT_NUMBER);
        }

        return parseToLongSafely(rawMoney);
    }

    private boolean isNumeric(String lottoMoney) {

        if (lottoMoney == null || lottoMoney.trim().isEmpty()) {
            return false;
        }

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
