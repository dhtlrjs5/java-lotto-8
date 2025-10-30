package lotto.utils;

import java.util.Arrays;

public enum Rank {

    FIRST(6L, false, 2_000_000_000L),
    SECOND(5L, true, 30_000_000L),
    THIRD(5L, false, 1_500_000L),
    FOURTH(4L, false, 50_000L),
    FIFTH(3L, false, 5_000L),
    MISS(0L, false, 0L);

    private final long count;
    private final boolean matchBonus;
    private final long prizeAmount;

    Rank(long count, boolean matchBonus, long prizeAmount) {
        this.count = count;
        this.matchBonus = matchBonus;
        this.prizeAmount = prizeAmount;
    }

    public static Rank getRank(long count, boolean matchBonus) {

        if (count < 3) {
            return MISS;
        }

        if (count == 5 && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.count == count)
                .findFirst()
                .orElse(MISS);
    }
}
