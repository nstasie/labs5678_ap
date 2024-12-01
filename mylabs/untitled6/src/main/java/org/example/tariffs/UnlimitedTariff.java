package org.example.tariffs;

public class UnlimitedTariff extends Tariff {
    public UnlimitedTariff(String name, double subscriptionFee, int clients) {
        super(name, subscriptionFee, clients);
    }

    @Override
    public String toString() {
        return "UnlimitedTariff{" + super.toString() + "}";
    }
}
