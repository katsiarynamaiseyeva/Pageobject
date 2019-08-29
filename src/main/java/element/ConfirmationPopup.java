package element;

import core.UiElement;
import org.openqa.selenium.By;
import page.DashboardPage;

public class ConfirmationPopup extends UiElement {
    private static final By OK_BUTTON_CLOCK_LOCATOR = By.xpath("//button[text()='ok']");


    public DashboardPage clickOkClockButton() {
        driver.findElement(OK_BUTTON_CLOCK_LOCATOR).click();
        return new DashboardPage(driver);
    }
}
