package ru.yandex.practicum.delivery.parcels;

public enum ParcelType {
    STANDARD_PARCEL(1, "Cтандартная посылка"),
    PERISHABLE_PARCEL(2, "Cкоропортящаяся посылка"),
    FRAGILE_PARCEL(3, "Xрупкая посылка");

    private final int code;
    private final String name;

    ParcelType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ParcelType valueOf(int inputIndex) {
        for (ParcelType parcelType : ParcelType.values()) {
            if (parcelType.code == inputIndex) {
                return parcelType;
            }
        }
        return null;
    }
}
