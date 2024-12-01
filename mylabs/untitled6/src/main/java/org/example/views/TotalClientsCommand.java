package org.example.views;

import org.example.MobileCompany;

public class TotalClientsCommand implements Command {
    private MobileCompany company;

    public TotalClientsCommand(MobileCompany company) {
        this.company = company;
    }

    @Override
    public void execute() {
        int totalClients = company.getTotalClients();
        System.out.println("Total number of clients: " + totalClients);
    }
}
