package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    public static final double BASE_PRICE = 4;

    public FragileParcel(String description, double weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public double calculateDeliveryCost() {
        return (BASE_PRICE * weight);
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + description + ">> изменила местоположение на " + newLocation);
    }

}
