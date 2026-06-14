package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("MTS.by")
@Feature("Онлайн пополнение без комиссии")
public class MtsPaymentTest extends BaseTest {

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Description("На странице должно отображаться не менее 3 логотипов платёжных систем")
    @Severity(SeverityLevel.NORMAL)
    public void testLogosDisplayed() {
        HomePage homePage = new HomePage(driver);
        int count = homePage.getPaymentLogosCount();
        Assertions.assertTrue(count >= 3, "Отображается менее 3 логотипов");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    @Description("При клике на ссылку должен происходить переход на страницу с информацией")
    @Severity(SeverityLevel.CRITICAL)
    public void testMoreDetailsLink() {
        HomePage homePage = new HomePage(driver);
        String urlBefore = driver.getCurrentUrl();
        homePage.clickMoreDetailsLink();
        String urlAfter = driver.getCurrentUrl();
        Assertions.assertNotEquals(urlBefore, urlAfter, "Переход по ссылке не произошёл");
    }

    @Test
    @DisplayName("Проверка placeholder'ов для всех видов услуг")
    @Description("Для услуг: Услуги связи, Домашний интернет, Рассрочка, Задолженность - placeholder должен существовать")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersForAllServices() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToPaymentForm();

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            try { Thread.sleep(2000); } catch (Exception e) {}
            homePage.selectService(service);
            try { Thread.sleep(1000); } catch (Exception e) {}

            String placeholder = homePage.getPlaceholderForService(service);
            Assertions.assertNotNull(placeholder, "Placeholder для " + service + " не найден");
            Assertions.assertFalse(placeholder.isEmpty(), "Placeholder для " + service + " пустой");
        }
    }

    @Test
    @DisplayName("Проверка модального окна после нажатия 'Продолжить'")
    @Description("Проверяются: сумма, номер телефона, иконки платёжных систем, placeholder'ы полей карты")
    @Severity(SeverityLevel.CRITICAL)
    public void testModalWindowForPhonePayment() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToPaymentForm();

        try { Thread.sleep(2000); } catch (Exception e) {}
        homePage.selectService("Услуги связи");
        homePage.enterPhoneNumber("297777777");

        PaymentModalPage modal = homePage.clickContinueButton();

        String sum = modal.getTotalSum();
        Assertions.assertNotNull(sum, "Сумма не отображается");
        Assertions.assertTrue(sum.contains("руб") || sum.contains("BYN"), "Сумма отображается некорректно");

        String sumOnButton = modal.getPaymentButtonSum();
        Assertions.assertNotNull(sumOnButton, "Сумма на кнопке не отображается");

        String phone = modal.getPhoneNumber();
        Assertions.assertTrue(phone.contains("297777777"), "Номер телефона не совпадает");

        int logosCount = modal.getModalLogosCount();
        Assertions.assertTrue(logosCount >= 2, "Иконки платёжных систем отсутствуют");

        String[] cardFields = {"Номер карты", "Срок", "CVC"};
        for (String field : cardFields) {
            String placeholder = modal.getCardFieldPlaceholder(field);
            Assertions.assertNotNull(placeholder, "Placeholder для поля " + field + " не найден");
            Assertions.assertFalse(placeholder.isEmpty(), "Placeholder для поля " + field + " пустой");
        }
    }
}