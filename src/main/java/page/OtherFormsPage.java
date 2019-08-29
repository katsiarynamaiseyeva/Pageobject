package page;

import org.openqa.selenium.By;

public class OtherFormsPage extends AbstractPage {

    private static final By OPEN_TAB_LOCATOR = By.xpath("//*[contains(@class,'btn btn-secondary ') and text()='Open']");


    public OtherFormsOpenTabPage navigateToOpenTab() {
        driver.findElement(OPEN_TAB_LOCATOR).click();
        return new OtherFormsOpenTabPage();
    }
}

