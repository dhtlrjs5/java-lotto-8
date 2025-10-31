package lotto.domain;

import lotto.utils.Rank;

import java.util.List;

public class WinningLotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_DUPLICATE_BONUS =
            ERROR_PREFIX + "로또 번호와 중복된 보너스 번호입니다.";
    private static final String ERROR_BONUS_OUT_OF_RANGE =
            ERROR_PREFIX + "보너스 번호는 " + LOTTO_MIN_NUMBER + "~" + LOTTO_MAX_NUMBER + " 입니다.";

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validateDuplicateNumber();
        validateOutOfRangeValue();
    }

    public Rank match(Lotto purchasedLotto) {

        long count = purchasedLotto.countMatchingNumbers(lotto);
        boolean containsBonusNumber = purchasedLotto.contains(bonusNumber);

        return Rank.getRank(count, containsBonusNumber);
    }

    private void validateDuplicateNumber() {

        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
    }

    private void validateOutOfRangeValue() {

        if (bonusNumber < LOTTO_MIN_NUMBER || bonusNumber > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_BONUS_OUT_OF_RANGE);
        }
    }
}
