package org.example;

import org.example.views.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MobileCompany company = new MobileCompany();
        Scanner scanner = new Scanner(System.in);

        Menu menu = getMenu(company, scanner);

        while (true) {
            System.out.println("\nMobile Company Menu:");
            System.out.println("1. Load tariffs from file");
            System.out.println("2. Display all tariffs");
            System.out.println("3. Sort tariffs by subscription fee");
            System.out.println("4. Display total number of clients");
            System.out.println("5. Find tariffs by fee range");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 6) {
                System.out.println("Exiting the program.");
                break;
            }

            menu.selectOption(choice);
        }

        scanner.close();
    }

    public static Menu getMenu(MobileCompany company, Scanner scanner) {
        Command loadTariffsCommand = new LoadTariffsCommand(company, scanner);
        Command displayTariffsCommand = new DisplayTariffsCommand(company);
        Command sortTariffsCommand = new SortTariffsCommand(company);
        Command totalClientsCommand = new TotalClientsCommand(company);
        Command findTariffsByRangeCommand = new FindTariffsByRangeCommand(company, scanner);

        Command[] commands = {
                loadTariffsCommand,
                displayTariffsCommand,
                sortTariffsCommand,
                totalClientsCommand,
                findTariffsByRangeCommand
        };

        return new Menu(commands);
    }
}
