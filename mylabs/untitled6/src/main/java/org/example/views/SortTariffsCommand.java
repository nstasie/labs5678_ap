package org.example.views;

import org.example.MobileCompany;

public class SortTariffsCommand implements Command {
    private MobileCompany company;

    public SortTariffsCommand(MobileCompany company) {
        this.company = company;
    }

    @Override
    public void execute() {
        company.sortTariffsBySubscriptionFee();
        System.out.println("Tariffs sorted by subscription fee.");
    }
}
