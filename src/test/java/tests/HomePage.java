package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private static final String BASE_URL = "https://www.mts.by";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(BASE_URL);
        try { Thread.sleep(3000); } catch (Exception e) {}

        try {
            WebElement cookie = driver.findElement(By.xpath("//button[contains(text(), 'Принять')]"));
            if (cookie.isDisplayed()) {
                cookie.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }

    public void scrollToPaymentForm() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 600);");
            Thread.sleep(2000);
        } catch (Exception e) {}
    }

    public int getPaymentLogosCount() {
        List<WebElement> logos = driver.findElements(By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]"));
        return logos.size();
    }

    public void clickMoreDetailsLink() {
        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        link.click();
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    public void selectService(String serviceName) {
        try {
            WebElement service = driver.findElement(By.xpath("//span[contains(text(), '" + serviceName + "')]"));
            service.click();
        } catch (Exception e) {
            try {
                WebElement service = driver.findElement(By.xpath("//label[contains(text(), '" + serviceName + "')]"));
                service.click();
            } catch (Exception e2) {}
        }
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    public String getPlaceholderForService(String serviceName) {
        String id;
        switch (serviceName) {
            case "Услуги связи":
                id = "connection-phone";
                break;
            case "Домашний интернет":
                id = "internet-phone";
                break;
            case "Рассрочка":
                id = "score-instalment";
                break;
            case "Задолженность":
                id = "score-arrears";
                break;
            default:
                return null;
        }
        try {
            WebElement input = driver.findElement(By.id(id));
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            try {
                WebElement input = driver.findElement(By.name(id));
                return input.getAttribute("placeholder");
            } catch (Exception e2) {
                return null;
            }
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement input = driver.findElement(By.id("connection-phone"));
        input.clear();
        input.sendKeys(phoneNumber);
        try { Thread.sleep(500); } catch (Exception e) {}
    }

    public PaymentModalPage clickContinueButton() {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        button.click();
        try { Thread.sleep(3000); } catch (Exception e) {}
        return new PaymentModalPage(driver);
    }
}