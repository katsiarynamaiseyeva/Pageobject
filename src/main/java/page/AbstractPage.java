package page;

import core.DriverContainer;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 8;

    public AbstractPage() {
        this.driver = DriverContainer.getDriver();
    }

}
