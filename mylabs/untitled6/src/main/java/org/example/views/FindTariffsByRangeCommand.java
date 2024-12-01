package org.example.views;

import org.example.MobileCompany;
import org.example.tariffs.Tariff;

import java.util.List;
import java.util.Scanner;

public class FindTariffsByRangeCommand implements Command {
    private MobileCompany company;
    private Scanner scanner;

    public FindTariffsByRangeCommand(MobileCompany company, Scanner scanner) {
        this.company = company;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter minimum fee: ");
        double minFee = scanner.nextDouble();
        System.out.print("Enter maximum fee: ");
        double maxFee = scanner.nextDouble();
        List<Tariff> tariffsInRange = company.findTariffsInRange(minFee, maxFee);
        System.out.println("Tariffs in the fee range:");
        for (Tariff tariff : tariffsInRange) {
            System.out.println(tariff);
        }
    }
}
