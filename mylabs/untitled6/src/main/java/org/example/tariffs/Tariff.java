package org.example.tariffs;

public abstract class Tariff {
    private String name;
    private double subscriptionFee;
    private int clients;

    public Tariff(String name, double subscriptionFee, int clients) {
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public int getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Tariff{name='" + name + "', subscriptionFee=" + subscriptionFee + ", clients=" + clients + "}";
    }
}
