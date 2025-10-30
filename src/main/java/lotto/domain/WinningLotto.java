package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validateDuplicateNumber();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateDuplicateNumber() {

        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호와 중복된 보너스 번호입니다.");
        }
    }
}
