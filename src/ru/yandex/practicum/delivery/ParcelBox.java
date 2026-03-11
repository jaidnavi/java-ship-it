package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> listParcels = new ArrayList<>();

    private final double maxWeight;

    public ParcelBox(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        double allWeight = 0.0;
        for (T forParcel : listParcels) {
            allWeight = allWeight + forParcel.getWeight();
        }
        if (allWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Максимальный вес коробки превышен");
        } else {
            listParcels.add(parcel);
        }
    }

    private double restBox() {
        double allWeight = 0.0;
        for (T forParcel : listParcels) {
            allWeight = allWeight + forParcel.getWeight();
        }
        return (maxWeight - allWeight);
    }

    public void getAllParcels() {
        if (listParcels.isEmpty()) {
            System.out.println("Коробка пуста");
            return;
        }
        System.out.println("Содержимое коробки:");
        for (Parcel parcel : listParcels) {
            System.out.println(parcel);
        }
        System.out.println("Оставшийся вес коробки равен " + restBox());
    }

    public int getParcelsCount() {
        return listParcels.size();
    }
}