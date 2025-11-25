package ru.yandex.practicum.delivery.parcels;

public class PerishableParcel extends Parcel {
    static final int PRICE_PER_KG = 3;
    private int timeToLive;

    public PerishableParcel(
            String description,
            int weight,
            String deliveryAddress,
            int sendDay,
            int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getPricePerKg() {
        return PRICE_PER_KG;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }
}
