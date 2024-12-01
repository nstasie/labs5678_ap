package org.example.views;

import org.example.MobileCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LoadTariffsCommand implements Command {
    private MobileCompany company;
    private Scanner scanner;

    public LoadTariffsCommand(MobileCompany company, Scanner scanner) {
        this.company = company;
        this.scanner = scanner;
    }

    private static final Logger logger = LoggerFactory.getLogger(LoadTariffsCommand.class);

    @Override
    public void execute() {
        logger.info("Виконання команди завантаження тарифів");
        try {
            System.out.print("Enter file path: ");
            String filePath = scanner.next();
            company.loadTariffsFromFile(filePath);
            System.out.println("Tariffs loaded from file.");
            logger.info("Тарифи завантажено з файлу: {}", filePath);
        } catch (Exception e) {
            logger.error("Помилка при завантаженні тарифів", e);
        }
    }
}
