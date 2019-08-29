package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class UiElement extends RemoteWebElement {

    protected WebDriver driver = DriverContainer.getDriver();

}
