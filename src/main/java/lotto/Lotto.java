package lotto;

import java.util.List;

/**
 * Lotto에 numbers 이외의 필드(인스턴스 변수)를 추가할 수 없다.
 * numbers의 접근 제어자인 private은 변경할 수 없다.
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
}
