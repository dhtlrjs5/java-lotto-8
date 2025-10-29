package lotto.converter;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoConverter {

    public Lotto convertStringToLotto(String winningNumber) {

        List<Integer> numbers = new ArrayList<>();

        for (String num : winningNumber.split(",")) {
            numbers.add(Integer.parseInt(num));
        }

        return new Lotto(numbers);
    }

    public WinningLotto convertToWinningLotto(Lotto lotto, String bonusNumber) {

        return new WinningLotto(lotto, Integer.parseInt(bonusNumber));
    }
}
