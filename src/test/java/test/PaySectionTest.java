package tests;
1
import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.PayPage;

public class PaySectionTest extends BaseTest {

    @Test
    public void checkTitle() {
        PayPage page = new PayPage(driver);

        Assertions.assertTrue(page.isTitleDisplayed());
    }

    @Test
    public void checkLogos() {
        PayPage page = new PayPage(driver);

        Assertions.assertTrue(page.getLogosCount() > 0);
    }

    @Test
    public void checkMoreLink() {
        PayPage page = new PayPage(driver);

        Assertions.assertTrue(page.isMoreLinkDisplayed());
        page.clickMoreLink();
    }

    @Test
    public void checkForm() {
        PayPage page = new PayPage(driver);

        page.enterPhone("297777777");

        Assertions.assertTrue(page.isContinueButtonDisplayed());
    }
}