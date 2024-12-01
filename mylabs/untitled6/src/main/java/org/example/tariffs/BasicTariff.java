package org.example.tariffs;

public class BasicTariff extends Tariff {
    private int minutesLimit;

    public BasicTariff(String name, double subscriptionFee, int clients, int minutesLimit) {
        super(name, subscriptionFee, clients);
        this.minutesLimit = minutesLimit;
    }

    @Override
    public String toString() {
        return "BasicTariff{" + super.toString() + ", minutesLimit=" + minutesLimit + "}";
    }
}
