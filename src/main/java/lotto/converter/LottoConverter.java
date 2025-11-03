package lotto.converter;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoConverter {

    private static final String DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String REGEX_VALIDATE_FORMAT = "\\d+(,\\d+)*";

    private static final String ERROR_INVALID_FORMAT =
            ERROR_PREFIX + "로또 번호는 숫자, 구분자는 쉼표(,)만 사용 가능합니다.";
    private static final String ERROR_LOTTO_NOT_VALID_NUMBER =
            ERROR_PREFIX + "로또 번호는 유효한 숫자여야 합니다.";
    private static final String ERROR_BONUS_NOT_VALID_NUMBER =
            ERROR_PREFIX + "보너스 번호는 유효한 숫자여야 합니다.";


    public Lotto convertStringToLotto(String winningNumber) {

        if (!winningNumber.matches(REGEX_VALIDATE_FORMAT)) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }

        List<Integer> numbers = new ArrayList<>();

        for (String num : winningNumber.split(DELIMITER)) {
            numbers.add(parseNumberSafely(num));
        }

        return new Lotto(numbers);
    }

    public WinningLotto convertToWinningLotto(Lotto lotto, String rawBonusNumber) {

        int bonusNumber;

        try {
            bonusNumber = Integer.parseInt(rawBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BONUS_NOT_VALID_NUMBER);
        }

        return new WinningLotto(lotto, bonusNumber);
    }

    private int parseNumberSafely(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_LOTTO_NOT_VALID_NUMBER);
        }
    }
}
