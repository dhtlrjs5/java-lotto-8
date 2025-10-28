package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * Lotto에 numbers 이외의 필드(인스턴스 변수)를 추가할 수 없다.
 * numbers의 접근 제어자인 private은 변경할 수 없다.
 */
public class Lotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨번호에 중복이 있으면 안됩니다.");
        }

        if (hasNull(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨번호는 숫자여야 합니다.");
        }

        if (hasOutOfRangeValue(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨번호는 1~45 입니다.");
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
