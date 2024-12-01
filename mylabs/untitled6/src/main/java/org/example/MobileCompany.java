package org.example;

import org.example.tariffs.BasicTariff;
import org.example.tariffs.PremiumTariff;
import org.example.tariffs.Tariff;
import org.example.tariffs.UnlimitedTariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MobileCompany {
    private List<Tariff> tariffs;
    private static final Logger logger = LoggerFactory.getLogger(MobileCompany.class);

    public MobileCompany() {
        logger.info("Створено нову компанію MobileCompany");
        tariffs = new ArrayList<>();
    }

    public void addTariff(Tariff tariff) {
        try {
            // Додаємо тариф до списку тарифів компанії
            logger.info("Додаємо тариф: {}", tariff.getName());
            tariffs.add(tariff);
        } catch (Exception e) {
            logger.error("Не вдалося додати тариф: {}", tariff.getName(), e);
        }
    }

    public int getTotalClients() {
        int totalClients = 0;
        try {
            for (Tariff tariff : tariffs) {
                totalClients += tariff.getClients();
            }
            logger.info("Обчислення загальної кількості клієнтів");
        } catch (Exception e) {
            logger.error("Помилка при підрахунку клієнтів", e);
        }
        return totalClients;
    }

    public void sortTariffsBySubscriptionFee() {
        tariffs.sort(Comparator.comparingDouble(Tariff::getSubscriptionFee));
    }

    public List<Tariff> findTariffsInRange(double min, double max) {
        List<Tariff> result = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            if (tariff.getSubscriptionFee() >= min && tariff.getSubscriptionFee() <= max) {
                result.add(tariff);
            }
        }
        return result;
    }

    public void loadTariffsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                double fee = Double.parseDouble(parts[2]);
                int clients = Integer.parseInt(parts[3]);

                switch (type) {
                    case "Basic":
                        int minutesLimit = Integer.parseInt(parts[4]);
                        addTariff(new BasicTariff(name, fee, clients, minutesLimit));
                        break;
                    case "Premium":
                        int dataLimit = Integer.parseInt(parts[4]);
                        addTariff(new PremiumTariff(name, fee, clients, dataLimit));
                        break;
                    case "Unlimited":
                        addTariff(new UnlimitedTariff(name, fee, clients));
                        break;
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error loading tariffs from file: " + e.getMessage());
        }
    }

    public void displayTariffs() {
        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }

    public List<Tariff> getTariffs() {
        return  tariffs;
    }
}
