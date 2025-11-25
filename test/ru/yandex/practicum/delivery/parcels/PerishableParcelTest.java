package ru.yandex.practicum.delivery.parcels;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PerishableParcelTest {
    static PerishableParcel parcel;

    @BeforeAll
    static void beforeAll() {
        int sentDay = 5;
        int timeToLive = 13;

        parcel = new PerishableParcel("a", 1, "a", sentDay, timeToLive);
    }

    @Test
    void shouldReturnFalseWhenCurrentDayBeforeExpiration() {
        int midDay = 12;
        boolean result = parcel.isExpired(midDay);

        Assertions.assertFalse(result);
    }
    @Test
    void shouldReturnFalseOnExpirationBoundary() {
        int dayOfExpiration = 18;
        boolean result = parcel.isExpired(dayOfExpiration);

        Assertions.assertFalse(result);
    }
    @Test
    void shouldReturnTrueWhenCurrentDayIsOneAfterExpiration() {
        int dayAfterExpiration = 19;
        boolean result = parcel.isExpired(dayAfterExpiration);

        Assertions.assertTrue(result);
    }
    @Test
    void shouldReturnTrueWhenCurrentDayIsFarAfterExpiration() {
        int expirationDay = 30;
        boolean result = parcel.isExpired(expirationDay);

        Assertions.assertTrue(result);
    }
}