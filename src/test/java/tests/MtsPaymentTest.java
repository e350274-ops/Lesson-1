package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MtsPaymentTest extends BaseTest {

    @Test
    public void testLogosDisplayed() {
        HomePage homePage = new HomePage(driver);
        int count = homePage.getPaymentLogosCount();
        Assert.assertTrue(count >= 3, "Отображается менее 3 логотипов");
    }

    @Test
    public void testMoreDetailsLink() {
        HomePage homePage = new HomePage(driver);
        String urlBefore = driver.getCurrentUrl();
        homePage.clickMoreDetailsLink();
        String urlAfter = driver.getCurrentUrl();
        Assert.assertNotEquals(urlBefore, urlAfter, "Переход по ссылке не произошёл");
    }

    @Test
    public void testPlaceholdersForAllServices() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToPaymentForm();

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            try { Thread.sleep(2000); } catch (Exception e) {}
            homePage.selectService(service);
            try { Thread.sleep(1000); } catch (Exception e) {}

            String placeholder = homePage.getPlaceholderForService(service);
            Assert.assertNotNull(placeholder, "Placeholder для " + service + " не найден");
            Assert.assertFalse(placeholder.isEmpty(), "Placeholder для " + service + " пустой");
        }
    }

    @Test
    public void testModalWindowForPhonePayment() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToPaymentForm();

        try { Thread.sleep(2000); } catch (Exception e) {}
        homePage.selectService("Услуги связи");
        homePage.enterPhoneNumber("297777777");

        PaymentModalPage modal = homePage.clickContinueButton();

        String sum = modal.getTotalSum();
        Assert.assertNotNull(sum, "Сумма не отображается");
        Assert.assertTrue(sum.contains("руб") || sum.contains("BYN"), "Сумма отображается некорректно");

        String sumOnButton = modal.getPaymentButtonSum();
        Assert.assertNotNull(sumOnButton, "Сумма на кнопке не отображается");

        String phone = modal.getPhoneNumber();
        Assert.assertTrue(phone.contains("297777777"), "Номер телефона не совпадает");

        int logosCount = modal.getModalLogosCount();
        Assert.assertTrue(logosCount >= 2, "Иконки платёжных систем отсутствуют");

        String[] cardFields = {"Номер карты", "Срок", "CVC"};
        for (String field : cardFields) {
            String placeholder = modal.getCardFieldPlaceholder(field);
            Assert.assertNotNull(placeholder, "Placeholder для поля " + field + " не найден");
            Assert.assertFalse(placeholder.isEmpty(), "Placeholder для поля " + field + " пустой");
        }
    }
}