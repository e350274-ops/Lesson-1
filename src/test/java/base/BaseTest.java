package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
1
import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.mts.by");

        try {
            List<WebElement> cookies = driver.findElements(
                    By.xpath("//button[contains(text(),'Отклонить')]")
            );

            if (!cookies.isEmpty()) {
                cookies.get(0).click();
            }
        } catch (Exception ignored) {}
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
