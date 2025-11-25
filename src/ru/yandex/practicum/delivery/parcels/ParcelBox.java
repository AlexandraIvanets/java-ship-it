package ru.yandex.practicum.delivery.parcels;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();
    private int maxWeight;
    private int totalWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (totalWeight + parcel.weight > maxWeight) {
            System.out.println("Лимит веса превышен, добавление посылки в коробку невозможно");
            return;
        }

        parcels.add(parcel);
        System.out.println("Посылка успешно добавлена в коробку");
        totalWeight += parcel.weight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}
