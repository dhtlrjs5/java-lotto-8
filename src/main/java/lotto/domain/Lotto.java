package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_SIZE =
            ERROR_PREFIX + "로또 번호는 " + LOTTO_NUMBER_SIZE + "개여야 합니다.";
    private static final String ERROR_DUPLICATE = ERROR_PREFIX + "로또 당첨번호에 중복이 있으면 안됩니다.";
    private static final String ERROR_NOT_NUMBER = ERROR_PREFIX + "로또 당첨번호는 숫자여야 합니다.";
    private static final String ERROR_OUT_OF_RANGE =
            ERROR_PREFIX + "로또 당첨번호는 " + LOTTO_MIN_NUMBER + "~" + LOTTO_MAX_NUMBER + " 입니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }

        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }

        if (hasNull(numbers)) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }

        if (hasOutOfRangeValue(numbers)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    private boolean hasOutOfRangeValue(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(num -> num < LOTTO_MIN_NUMBER || num > LOTTO_MAX_NUMBER);
    }

    private boolean hasNull(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(Objects::isNull);
    }

    private static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }
}
