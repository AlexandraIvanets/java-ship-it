package ru.yandex.practicum.delivery.parcels;

public class StandardParcel extends Parcel {
    static final int PRICE_PER_KG = 2;

    public StandardParcel(
            String description,
            int weight,
            String deliveryAddress,
            int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getPricePerKg() {
        return PRICE_PER_KG;
    }
}
