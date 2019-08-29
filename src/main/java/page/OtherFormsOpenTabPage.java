package page;

import element.Popover;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtherFormsOpenTabPage extends AbstractPage {

    private static final By THREE_DOTS_LOCATOR = By.xpath("(//*[@class='div-table-body']/tr)[1]//i[@class='icon-more-actions']");
    private static final By NUMBER_OF_NOTES_LOCATOR = By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]");
    int numberOfNotes;


    public Popover clickOnThreeDots() {
        driver.findElement(THREE_DOTS_LOCATOR).click();
        return new Popover();
    }

    public int countNumberOfNotes() {
        if (driver.findElement(NUMBER_OF_NOTES_LOCATOR).isDisplayed()) {
            numberOfNotes = Integer.parseInt(driver.findElement(NUMBER_OF_NOTES_LOCATOR).getAttribute("data-counter"));
        } else {
            numberOfNotes = 0;
        }
        return numberOfNotes;
    }

    public void waitForNoteCounter2(int numberOfNotesAfterExpected){
        new WebDriverWait(driver, 20)
            .until(ExpectedConditions.attributeToBe(NUMBER_OF_NOTES_LOCATOR, "data-counter", String.valueOf(numberOfNotesAfterExpected)));
    }

}