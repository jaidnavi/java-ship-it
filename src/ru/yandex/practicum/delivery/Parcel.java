package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected final String description;

    public double getWeight() {
        return weight;
    }

    protected final double weight;
    private final String deliveryAddress;
    protected final int sendDay;

    Parcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    public abstract double calculateDeliveryCost();

    @Override
    public String toString() {
        return "Посылка " + description + ", вес " + weight + ", адрес " + deliveryAddress;
    }


}