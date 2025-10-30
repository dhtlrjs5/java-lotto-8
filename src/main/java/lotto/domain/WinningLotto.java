package lotto.domain;

import lotto.utils.Rank;

import java.util.List;

public class WinningLotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validateDuplicateNumber();
        validateOutOfRangeValue();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public Rank match(Lotto purchasedLotto) {

        List<Integer> winningNumbers = lotto.getLotto();
        List<Integer> purchasedNumbers = purchasedLotto.getLotto();

        long count = purchasedNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        boolean containsBonusNumber = purchasedNumbers.contains(bonusNumber);

        return Rank.getRank(count, containsBonusNumber);
    }

    private void validateDuplicateNumber() {

        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호와 중복된 보너스 번호입니다.");
        }
    }

    private void validateOutOfRangeValue() {

        if (bonusNumber < LOTTO_MIN_NUMBER || bonusNumber > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 입니다.");
        }
    }
}
