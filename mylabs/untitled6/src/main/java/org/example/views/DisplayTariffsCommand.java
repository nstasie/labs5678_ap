package org.example.views;

import org.example.MobileCompany;

public class DisplayTariffsCommand implements Command {
    private MobileCompany company;

    public DisplayTariffsCommand(MobileCompany company) {
        this.company = company;
    }

    @Override
    public void execute() {
        System.out.println("All tariffs:");
        company.displayTariffs();
    }
}
