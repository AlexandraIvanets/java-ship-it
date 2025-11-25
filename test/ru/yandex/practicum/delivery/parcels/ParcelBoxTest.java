package ru.yandex.practicum.delivery.parcels;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParcelBoxTest {
    ParcelBox<StandardParcel> parcelBox;
    StandardParcel standardParcel;

    @BeforeEach
    void beforeEach() {
        parcelBox = new ParcelBox<>(10);
        standardParcel = new StandardParcel("Гирлянда", 5, "Невский", 31);
    }

    @Test
    void shouldAddParcelWhenWeightWithinLimit() {
        parcelBox.addParcel(standardParcel);

        int expectedWeight = 5;
        int expectedParcelsAmount = 1;

        int resultWeight = parcelBox.getTotalWeight();
        int resultParcelsAmount = parcelBox.getAllParcels().size();

        Assertions.assertEquals(expectedWeight, resultWeight);
        Assertions.assertEquals(expectedParcelsAmount, resultParcelsAmount);
    }
    @Test
    void shouldAddParcelWhenWeightEqualsLimit() {
        parcelBox.addParcel(standardParcel);
        parcelBox.addParcel(standardParcel);

        int expectedWeight = 10;
        int expectedParcelsAmount = 2;

        int resultWeight = parcelBox.getTotalWeight();
        int resultParcelsAmount = parcelBox.getAllParcels().size();

        Assertions.assertEquals(expectedWeight, resultWeight);
        Assertions.assertEquals(expectedParcelsAmount, resultParcelsAmount);
    }

    @Test
    void shouldNotAddParcelWhenWeightExceedsLimit() {
        parcelBox.addParcel(standardParcel);
        parcelBox.addParcel(standardParcel);
        parcelBox.addParcel(standardParcel);

        int expectedWeight = 10;
        int expectedParcelsAmount = 2;

        int resultWeight = parcelBox.getTotalWeight();
        int resultParcelsAmount = parcelBox.getAllParcels().size();

        Assertions.assertEquals(expectedWeight, resultWeight);
        Assertions.assertEquals(expectedParcelsAmount, resultParcelsAmount);
    }
}