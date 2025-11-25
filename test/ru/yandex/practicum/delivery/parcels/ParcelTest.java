package ru.yandex.practicum.delivery.parcels;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParcelTest {

    @Test
    void shouldReturnCorrectDeliveryCostForStandardPerishableAndFragileParcels() {
        Parcel standardParcel = new StandardParcel("1",10,"1",1);
        Parcel perishableParcel = new PerishableParcel("1",10,"1",1, 1);
        Parcel fragileParcel = new FragileParcel("1",10,"1",1);

        int expectedStandardCost = 20;
        int expectedPerishableCost = 30;
        int expectedFragileCost = 40;

        int resultStandardCost = standardParcel.calculateDeliveryCost();
        int resultPerishableCost = perishableParcel.calculateDeliveryCost();
        int resultFragileCost = fragileParcel.calculateDeliveryCost();

        Assertions.assertEquals(expectedStandardCost, resultStandardCost);
        Assertions.assertEquals(expectedPerishableCost, resultPerishableCost);
        Assertions.assertEquals(expectedFragileCost, resultFragileCost);
    }
}