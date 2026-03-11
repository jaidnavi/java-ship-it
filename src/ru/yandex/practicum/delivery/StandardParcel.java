package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {
    private static final double BASE_PRICE = 2;

    public StandardParcel(String description, double weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public double calculateDeliveryCost() {
        return (BASE_PRICE * weight);
    }

}