package org.example.tariffs;

public class PremiumTariff extends Tariff {
    private int dataLimit; // in GB

    public PremiumTariff(String name, double subscriptionFee, int clients, int dataLimit) {
        super(name, subscriptionFee, clients);
        this.dataLimit = dataLimit;
    }

    @Override
    public String toString() {
        return "PremiumTariff{" + super.toString() + ", dataLimit=" + dataLimit + "GB}";
    }
}
