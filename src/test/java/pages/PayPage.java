package pages;
1
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PayPage {

    private WebDriver driver;

    public PayPage(WebDriver driver) {
        this.driver = driver;
    }

    private By title = By.xpath("//h2[contains(text(),'Онлайн пополнение')]");
    private By logos = By.xpath("//img[contains(@src,'visa') or contains(@src,'mastercard')]");
    private By moreLink = By.xpath("//a[contains(text(),'Подробнее')]");
    private By phoneInput = By.xpath("//input[@placeholder='Номер телефона']");
    private By continueButton = By.xpath("//button[contains(text(),'Продолжить')]");

    public boolean isTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public int getLogosCount() {
        return driver.findElements(logos).size();
    }

    public boolean isMoreLinkDisplayed() {
        return driver.findElement(moreLink).isDisplayed();
    }

    public void clickMoreLink() {
        driver.findElement(moreLink).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public boolean isContinueButtonDisplayed() {
        return driver.findElement(continueButton).isDisplayed();
    }
}