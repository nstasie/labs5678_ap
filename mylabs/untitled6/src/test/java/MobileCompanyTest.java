import org.example.MobileCompany;
import org.example.tariffs.BasicTariff;
import org.example.tariffs.PremiumTariff;
import org.example.tariffs.Tariff;
import org.example.tariffs.UnlimitedTariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MobileCompanyTest {
    private MobileCompany company;

    @BeforeEach
    void setUp() {
        company = new MobileCompany();
        company.addTariff(new BasicTariff("Basic", 10.0, 100, 500));
        company.addTariff(new PremiumTariff("Premium", 20.0, 200, 10));
        company.addTariff(new UnlimitedTariff("Unlimited", 30.0, 300));
    }

    @Test
    void testGetTotalClients() {
        assertEquals(600, company.getTotalClients());
    }

    @Test
    void testSortTariffsBySubscriptionFee() {
        company.sortTariffsBySubscriptionFee();
        List<Tariff> tariffs = company.findTariffsInRange(0, 100);
        assertEquals("Basic", tariffs.get(0).getName());
        assertEquals("Premium", tariffs.get(1).getName());
        assertEquals("Unlimited", tariffs.get(2).getName());
    }

    @Test
    void testFindTariffsInRange() {
        List<Tariff> tariffs = company.findTariffsInRange(15.0, 25.0);
        assertEquals(1, tariffs.size());
        assertEquals("Premium", tariffs.get(0).getName());
    }
}
