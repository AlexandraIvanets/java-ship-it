package ru.yandex.practicum.delivery.parcels;

public class FragileParcel extends Parcel implements Trackable {
    static final int PRICE_PER_KG = 4;
    String location;

    public FragileParcel(
            String description,
            int weight,
            String deliveryAddress,
            int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
        location = "склад пункта отправления";
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public int getPricePerKg() {
        return PRICE_PER_KG;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + description + ">> изменила местоположение c \"" +
                getLocation() + "\" на \"" + newLocation + '\"');
        location = newLocation;
    }

    public String getLocation() {
        return location;
    }
}
