package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcels.*;

import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.delivery.utils.ConsoleUtils.*;

public class DeliveryApp {
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableShipments = new ArrayList<>();
    private static ParcelBox<StandardParcel> boxOfStandardParcels = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> boxOfPerishableParcels = new ParcelBox<>(10);
    private static ParcelBox<FragileParcel> boxOfFragileParcels = new ParcelBox<>(10);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Введите номер действия:");

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Сообщить новую локацию посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println(SEPARATOR_LINE);

        ParcelType parcelType;

        while (true) {
            System.out.println("Доступные виды посылок");
            for (ParcelType type : ParcelType.values()) {
                System.out.println(type.getCode() + ". " + type.getName());
            }
            int inputIndex = readInt("Выберите номер типа посылки:");
            parcelType = ParcelType.valueOf(inputIndex);
            if (parcelType != null) {
                break;
            }
            System.out.println("Нет типа посылки с номером " + inputIndex + ". Попробуйсте снова.");
        }
        System.out.println("Вы выбрали \"" + parcelType.getName() + "\". Введите дополнительную информацию:");

        String description = readString("Введите описание:");
        int weight = readInt("Введите вес:");
        String deliveryAddress = readString("Введите адрес доставки:");
        int sendDay = readInt("Введите день отправки:");


        switch (parcelType) {
            case STANDARD_PARCEL -> {
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                boxOfStandardParcels.addParcel(standardParcel);
            }
            case PERISHABLE_PARCEL -> {
                int timeToLive = readInt("Срок годности посылки:");
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                boxOfPerishableParcels.addParcel(perishableParcel);
            }
            case FRAGILE_PARCEL -> {
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                trackableShipments.add(fragileParcel);
                allParcels.add(fragileParcel);
                boxOfFragileParcels.addParcel(fragileParcel);
            }
        }
        System.out.println(allParcels.getLast().getDescription() + " успешно добавлен(а)!");
        System.out.println(SEPARATOR_LINE);
    }

    private static void sendParcels() {
        System.out.println(SEPARATOR_LINE);

        if (allParcels.isEmpty()) {
            System.out.println("Посылок для отпраки нет");
            return;
        }
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        System.out.println(SEPARATOR_LINE);
    }

    private static void calculateCosts() {
        System.out.println(SEPARATOR_LINE);

        if (allParcels.isEmpty()) {
            System.out.println("Нет отслеживаемых посылок");
        }

        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок составила " + totalCost + " руб.");
        System.out.println(SEPARATOR_LINE);
    }

    private static void reportStatus() {
        System.out.println(SEPARATOR_LINE);

        if (trackableShipments.isEmpty()) {
            System.out.println("Нет отслеживаемых отправлений");
            return;
        }

        System.out.println("Отслеживаемые отправления:");
        int index = 1;
        for (Trackable trackableShipment : trackableShipments) {
            if (trackableShipment instanceof Parcel parcel) {
                System.out.print(index + ". ");
                System.out.println(parcel.getDescription());
                index++;
            }
        }

        int ordinal;
        while (true) {
            ordinal = readInt("Введите номер отправления, статус которого хотите изменить:");
            if (ordinal >= 1 && ordinal <= trackableShipments.size()) {
                break;
            }
            System.out.println("Такого номера нет");
        }

        String newLocation = readString("Введите новое местоположение отправления:");
        trackableShipments.get(ordinal - 1).reportStatus(newLocation);
        for (Trackable trackableShipment : trackableShipments) {
            if (trackableShipment instanceof Parcel parcel) {
                System.out.print(index + ". ");
                System.out.println(parcel.getDescription());
                index++;
            }
        }
        System.out.println(SEPARATOR_LINE);
    }

    private static void showBoxContents() {
        System.out.println(SEPARATOR_LINE);

        System.out.println("Выберите коробку для просмотра содержимого: ");
        System.out.println("1. Коробка со стандартными посылками");
        System.out.println("2. Коробка с скоропортящимися посылками");
        System.out.println("3. Коробка с хрупкими посылками");

        int number = readInt("Введите номер коробки:");

        switch (number) {
            case 1 -> {
                List<StandardParcel> standardParcels = boxOfStandardParcels.getAllParcels();
                if (standardParcels.isEmpty()) {
                    System.out.println("Коробка пуста");
                    break;
                }
                for (Parcel parcel : standardParcels) {
                    System.out.println(parcel.getDescription());
                }
            }
            case 2 -> {
                List<PerishableParcel> perishableParcels = boxOfPerishableParcels.getAllParcels();
                if (perishableParcels.isEmpty()) {
                    System.out.println("Коробка пуста");
                    break;
                }
                for (Parcel parcel : perishableParcels) {
                    System.out.println(parcel.getDescription());
                }
            }
            case 3 -> {
                List<FragileParcel> fragileParcels = boxOfFragileParcels.getAllParcels();
                if (fragileParcels.isEmpty()) {
                    System.out.println("Коробка пуста");
                    break;
                }
                for (Parcel parcel : fragileParcels) {
                    System.out.println(parcel.getDescription());
                }
            }
            default -> System.out.println("Неверный номер");
        }
        System.out.println(SEPARATOR_LINE);
    }
}

