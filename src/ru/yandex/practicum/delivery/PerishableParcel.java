package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    public static final double BASE_PRICE = 3;
    private final int timeToLive;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public Boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }

    @Override
    public double calculateDeliveryCost() {
        return (BASE_PRICE * weight);
    }

}