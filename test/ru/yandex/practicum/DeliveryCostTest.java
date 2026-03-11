package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;
import org.junit.jupiter.api.Assertions;

public class DeliveryCostTest {
    private final StandardParcel standardParcel = new StandardParcel("description", 20, "deliveryAddress", 1);
    private final PerishableParcel perishableParcel = new PerishableParcel("description", 20, "deliveryAddress", 1, 1);
    private final FragileParcel fragileParcel = new FragileParcel("description", 10, "deliveryAddress", 1);

    @Test
    public void shouldBePositiveStandardParcelDeliveryCost() {
        Assertions.assertTrue(standardParcel.calculateDeliveryCost() > 0);
    }

    @Test
    public void shouldBePerishableStandardParcelDeliveryCost() {
        Assertions.assertTrue(perishableParcel.calculateDeliveryCost() > 0);
    }

    @Test
    public void shouldBeFragileParcelStandardParcelDeliveryCost() {
        Assertions.assertTrue(fragileParcel.calculateDeliveryCost() > 0);
    }

    @Test
    public void shouldBeIsExpiredFalseForExpiredPerishableParcelDeliveryCost() {
        Assertions.assertFalse(perishableParcel.isExpired(1));
    }

    @Test
    public void shouldBeIsExpiredFalseForBorderExpiredPerishableParcelDeliveryCost() {
        Assertions.assertFalse(perishableParcel.isExpired(2));
    }

    @Test
    public void shouldBeIsExpiredTrueForNotExpiredPerishableParcelDeliveryCost() {
        Assertions.assertTrue(perishableParcel.isExpired(3));
    }

    @Test
    public void shouldBeAddParcelInBoxForNotExceedingWeightLimit() {
        ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(1000);
        fragileParcelBox.addParcel(fragileParcel);
        Assertions.assertEquals(1, fragileParcelBox.getParcelsCount());
        fragileParcelBox.addParcel(fragileParcel);
        Assertions.assertEquals(2, fragileParcelBox.getParcelsCount());
    }

    @Test
    public void shouldBeAddParcelInBoxForBorderNotExceedingWeightLimit() {
        ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(10);
        fragileParcelBox.addParcel(fragileParcel);
        Assertions.assertEquals(1, fragileParcelBox.getParcelsCount());
    }

    @Test
    public void shouldBeNotAddParcelInBoxForExceedingWeightLimit() {
        ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(1.0);
        fragileParcelBox.addParcel(fragileParcel);
        Assertions.assertEquals(0, fragileParcelBox.getParcelsCount());
    }
}
