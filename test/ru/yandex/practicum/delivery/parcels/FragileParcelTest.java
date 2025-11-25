package ru.yandex.practicum.delivery.parcels;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FragileParcelTest {

    @Test
    void shouldReturnFalseWhenNotExpired() {
        PerishableParcel parcel = new PerishableParcel(
                "a",
                1,
                "a",
                5,
                13);

        boolean resultIsExpired = parcel.isExpired(1);
        boolean ResultIsExpiredMid = parcel.isExpired(14);
        boolean resultUpperBound = parcel.isExpired(18);

        Assertions.assertFalse(resultIsExpired);
        Assertions.assertFalse(ResultIsExpiredMid);
        Assertions.assertFalse(resultUpperBound);

    }
}