package ru.yandex.practicum.delivery.parcels;

public interface Trackable {
    void reportStatus(String newLocation);
}
