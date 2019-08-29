package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverContainer {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = initDriver();
        }
        return driver;
    }

    private static WebDriver initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
