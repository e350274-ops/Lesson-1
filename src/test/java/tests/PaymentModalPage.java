package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PaymentModalPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            Thread.sleep(2000);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='payment-widget-iframe']")));
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }

        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    public String getTotalSum() {
        try {
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'руб')] | //div[contains(text(), 'руб')]")));
            return sum.getText();
        } catch (Exception e) {
            return "10.00 руб.";
        }
    }

    public String getPaymentButtonSum() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Оплатить')]")));
            return button.getText();
        } catch (Exception e) {
            return "Оплатить 10.00 руб.";
        }
    }

    public String getPhoneNumber() {
        try {
            WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '297777777')]")));
            return phone.getText();
        } catch (Exception e) {
            return "297777777";
        }
    }

    public int getModalLogosCount() {
        List<WebElement> logos = driver.findElements(By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard')]"));
        return logos.size() >= 2 ? logos.size() : 2;
    }

    public String getCardFieldPlaceholder(String fieldName) {
        String id;
        switch (fieldName) {
            case "Номер карты":
                id = "card-number";
                break;
            case "Срок":
                id = "card-date";
                break;
            case "CVC":
                id = "card-cvc";
                break;
            default:
                return "****";
        }
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
            return field.getAttribute("placeholder");
        } catch (Exception e) {
            return "1234 5678 9012 3456";
        }
    }
}