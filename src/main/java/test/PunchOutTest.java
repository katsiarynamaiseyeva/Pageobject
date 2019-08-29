package test;

import core.DriverContainer;
import element.ConfirmationPopup;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PunchOutTest {

    @Test(description = "Punch out test")
    public void punchOutTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        Properties property = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DashboardPage dashboardPage = loginPage.login(property.getProperty("kronos.username"), property.getProperty("kronos.password"));
        ConfirmationPopup confirmationPopup = dashboardPage.punchOut();
        DashboardPage dashboardPage1 = confirmationPopup.clickOkClockButton();

    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() {
        DriverContainer.getDriver().quit();
        System.out.println("Browser is closed");
    }

}