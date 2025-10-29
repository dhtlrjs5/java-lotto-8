package lotto.converter;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoConverter {

    private static final String DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String INVALID_DELIMITER_OR_NOT_NUMBER = "로또 번호는 숫자, 구분자는 쉼표(,)만 사용 가능합니다.";


    public Lotto convertStringToLotto(String winningNumber) {

        if (!winningNumber.matches("\\d+(,\\d+)*")) {
            throw new IllegalArgumentException(ERROR_PREFIX + INVALID_DELIMITER_OR_NOT_NUMBER);
        }

        List<Integer> numbers = new ArrayList<>();

        for (String num : winningNumber.split(DELIMITER)) {
            numbers.add(Integer.parseInt(num));
        }

        return new Lotto(numbers);
    }

    public WinningLotto convertToWinningLotto(Lotto lotto, String bonusNumber) {

        return new WinningLotto(lotto, Integer.parseInt(bonusNumber));
    }
}
