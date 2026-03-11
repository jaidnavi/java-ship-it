package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();

    private static final ParcelBox<StandardParcel> standartParcelBox = new ParcelBox<>(1000.0);
    private static final ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(100.0);
    private static final ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(10.0);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

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
                    reportStatusParcels();
                    break;
                case 5:
                    checkBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }

    private static void reportStatusParcels() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок, поддерживающих трекинг");
            return;
        }

        System.out.println("Введите новое местоположение для доставок, поддерживающих трекинг:");
        String newLocation = scanner.next();
        scanner.nextLine();
        for (Trackable trackableParcel : trackableParcels) {
            trackableParcel.reportStatus(newLocation);
        }
    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels

        int choice;
        while (true) {
            System.out.println("Выберите тип посылки:");
            System.out.println("1 — Стандартная посылка");
            System.out.println("2 — Хрупкая посылка");
            System.out.println("3 — Скоропортящаяся посылка");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= 3) {
                break;
            } else {
                System.out.println("Ожидается число от 1 до 3. Повторите ввод.");
            }
        }

        System.out.println("Введите описание посылки:");
        String description = scanner.next();
        scanner.nextLine();
        double weight = -1;
        while (weight < 0) {
            System.out.println("Введите вес посылки:");
            weight = scanner.nextDouble();
            scanner.nextLine();
            if (weight <= 0) {
                System.out.println("Вес посылки должен быть больше 0. Повторите ввод.");
            }
        }
        System.out.println("Введите адрес посылки:");
        String deliveryAddress = scanner.next();
        scanner.nextLine();

        int sendDay = -1;
        while (sendDay < 0) {
            System.out.println("Введите день месяца, в который посылка была отправлена:");
            sendDay = scanner.nextInt();
            scanner.nextLine();
            if (sendDay <= 0) {
                System.out.println("День месяца должен быть больше 0. Повторите ввод.");
            }
        }

        switch (choice) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standartParcelBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок в днях, за который посылка не испортится:");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный выбор.");
                break;
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Изменить  местоположение посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        double allDeliveryCost = 0;
        for (Parcel parcel : allParcels) {
            allDeliveryCost = allDeliveryCost + parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость всех посылок " + allDeliveryCost);
    }

    private static void checkBox() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 — Коробка со стандартными посылками");
        System.out.println("2 — Коробка с хрупкими посылками");
        System.out.println("3 — Коробка со скоропортящимися посылками");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                standartParcelBox.getAllParcels();
                break;
            case 2:
                fragileParcelBox.getAllParcels();
                break;
            case 3:
                perishableParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Неверный выбор.");
        }

    }
}
