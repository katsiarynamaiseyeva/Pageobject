package page;


import org.openqa.selenium.By;

public class LoginPage extends AbstractPage {

    private static final By USERNAME_FIELD_LOCATOR = By.xpath("//input[@name='Username']");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@name='PasswordView']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//input[@name='LoginButton']");

    public void open() {
        driver.get(DirectUrl.URL);
    }

    public DashboardPage login(String username, String password) {
        driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(username);
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        return new DashboardPage(driver);
    }
}
