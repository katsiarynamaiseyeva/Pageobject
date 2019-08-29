package test;

import element.NotesPopup;
import element.Popover;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static core.DriverContainer.getDriver;


public class AddNoteTest {

    private WebDriver driver;


    @Test(description = "Add note test")
    public void AddNoteTest() {
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
        OtherFormsPage otherFormsPage = new HamburgerMenu().navigateToForms();
        OtherFormsOpenTabPage otherFormsOpenTabPage = new OtherFormsPage().navigateToOpenTab();

        int numberOfNotesBefore = new OtherFormsOpenTabPage().countNumberOfNotes();

        Popover popover = new OtherFormsOpenTabPage().clickOnThreeDots();
        NotesPopup comment = new Popover().openCommentPopup();
        OtherFormsOpenTabPage otherFormsOpenTabPage1 = new NotesPopup().addComment("comment text");

        OtherFormsOpenTabPage otherFormsOpenTabPage2 = new OtherFormsOpenTabPage();

        otherFormsOpenTabPage2.waitForNoteCounter2(numberOfNotesBefore+1);

        int numberOfNotesAfter = otherFormsOpenTabPage2.countNumberOfNotes();

        Assert.assertEquals(numberOfNotesAfter, numberOfNotesBefore + 1, "Note is not added");
    }


    @AfterClass(description = "Close browser")
    public void closeBrowser() {
        getDriver().quit();
        System.out.println("Browser is closed");
    }
}
