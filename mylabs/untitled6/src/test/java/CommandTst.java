import org.example.EmailUtil;
import org.example.MobileCompany;
import org.example.tariffs.BasicTariff;
import org.example.tariffs.PremiumTariff;
import org.example.tariffs.Tariff;
import org.example.tariffs.UnlimitedTariff;
import org.example.views.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CommandTest {
    private MobileCompany company;

    @BeforeEach
    void setUp() {
        company = new MobileCompany();
    }
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void testSortTariffsCommand() {
        SortTariffsCommand sortCommand = new SortTariffsCommand(company);
        company.addTariff(new BasicTariff("Basic", 10.0, 100, 500));
        company.addTariff(new PremiumTariff("Premium", 20.0, 200, 10));
        sortCommand.execute();
        List<Tariff> sortedTariffs = company.findTariffsInRange(0, 30);
        assertEquals("Basic", sortedTariffs.get(0).getName());
        assertEquals("Premium", sortedTariffs.get(1).getName());
    }

    @Test
    void testTotalClientsCommand() {
        TotalClientsCommand totalClientsCommand = new TotalClientsCommand(company);
        totalClientsCommand.execute();
        assertEquals(0, company.getTotalClients());
    }

    @Test
    void testUnlimitedTariffCreation() {
        UnlimitedTariff tariff = new UnlimitedTariff("Unlimited", 50.0, 100);
        assertEquals("Unlimited", tariff.getName());
        assertEquals(50.0, tariff.getSubscriptionFee());
        assertEquals(100, tariff.getClients());
    }

    @Test
    void testFindTariffsByRangeCommandNoMatches() {
        String simulatedUserInput = "10.0\n20.0\n";
        Scanner realScanner = new Scanner(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        realScanner.useLocale(Locale.ENGLISH);
        FindTariffsByRangeCommand findTariffsCommand = new FindTariffsByRangeCommand(company, realScanner);
        findTariffsCommand.execute();
        List<Tariff> tariffsInRange = company.findTariffsInRange(10.0, 20.0);
        assertEquals(0, tariffsInRange.size());
    }

    @Test
    void testFindTariffsByRangeCommandWithMatches() {
        company.addTariff(new BasicTariff("Basic", 15.0, 100, 500));
        company.addTariff(new PremiumTariff("Premium", 25.0, 200, 10));

        String simulatedUserInput = "10.0\n20.0\n";
        Scanner realScanner = new Scanner(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        realScanner.useLocale(Locale.ENGLISH);

        FindTariffsByRangeCommand findTariffsCommand = new FindTariffsByRangeCommand(company, realScanner);
        findTariffsCommand.execute();

        List<Tariff> tariffsInRange = company.findTariffsInRange(10.0, 20.0);
        assertEquals(1, tariffsInRange.size());
        assertEquals("Basic", tariffsInRange.get(0).getName());
    }

    @Test
    void testLoadTariffsFromFile() {
        String simulatedInput = "src/test/input";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        LoadTariffsCommand loadTariffsCommand = new LoadTariffsCommand(company, scanner);
        loadTariffsCommand.execute();
        List<Tariff> tariffs = company.getTariffs();
        assertEquals(3, tariffs.size());
    }

    @Test
    void testDisplayTariffsCommand() {
        try{
            Logger logger = LoggerFactory.getLogger(MobileCompany.class);
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) logger;
            rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);
            System.setOut(new PrintStream(outputStreamCaptor));

            company.addTariff(new BasicTariff("Basic", 10.0, 100, 500));
            company.addTariff(new PremiumTariff("Premium", 20.0, 200, 10));

            DisplayTariffsCommand displayCommand = new DisplayTariffsCommand(company);
            displayCommand.execute();

            String actualOutput = outputStreamCaptor.toString().replace(System.lineSeparator(), "\n");
            String expectedOutput = "All tariffs:\nBasicTariff{Tariff{name='Basic', subscriptionFee=10.0, clients=100}, minutesLimit=500}\nPremiumTariff{Tariff{name='Premium', subscriptionFee=20.0, clients=200}, dataLimit=10GB}\n";
            assertEquals(expectedOutput, actualOutput);
        } catch (AssertionError e) {
            try {
                EmailUtil.sendErrorEmail("Test Failed: testDisplayTariffsCommand", e.getMessage());
            } catch (MessagingException mailException) {
                mailException.printStackTrace();
            }
            throw e;
        }

    }

    @Test
    void testDisplayTariffsCommandForMail() {
        try {
            Logger logger = LoggerFactory.getLogger(MobileCompany.class);
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) logger;
            rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);
            System.setOut(new PrintStream(outputStreamCaptor));

            company.addTariff(new BasicTariff("Basic", 10.0, 100, 500));
            company.addTariff(new PremiumTariff("Premium", 20.0, 200, 10));

            DisplayTariffsCommand displayCommand = new DisplayTariffsCommand(company);
            displayCommand.execute();

            String actualOutput = outputStreamCaptor.toString().replace(System.lineSeparator(), "\n");
            String expectedOutput = "All tariffs:\nBasicTariff{Tariff{name='Basic', subscriptionFee=10.0, clients=100}, minutesLimit=500}\nPremiumTariff{Tariff{name='Premium', subscriptionFee=20.0, clients=200}, dataLimit=10GB}";
            assertEquals(expectedOutput, actualOutput);

        } catch (AssertionError e) {
            try {
                EmailUtil.sendErrorEmail("Test Failed: testDisplayTariffsCommand", e.getMessage());
            } catch (MessagingException mailException) {
                mailException.printStackTrace();
            }
            throw e;
        }
    }
}
