import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WfrWebDriver {

    public static void main(String[] args) {

        WebDriver driver;
        driver = new ChromeDriver();
        Properties property = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://wfrsecure.int.kronos.com/ta/kmosm.login?rnd=HGG&@rtm=1");

        //LOGIN

        WebElement usernameField = driver.findElement(By.xpath("//input[@name='Username']"));
        usernameField.sendKeys(property.getProperty("kronos.username"));

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='PasswordView']"));
        passwordField.sendKeys(property.getProperty("kronos.password"));

        WebElement loginButton = driver.findElement(By.xpath("//input[@name='LoginButton']"));
        loginButton.click();

        //new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='icon-email' and @aria-label='To-Do']")));

        //CLOCK IN


        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='time-clock-button' and @data-type='PUNCH']")));
        WebElement clockInButton = driver.findElement(By.xpath("//button[@class='time-clock-button' and @data-type='PUNCH']"));
        clockInButton.click();

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@role='alertdialog']")));

        WebElement messageClockInPopup = driver.findElement(By.xpath("//*[@id='msg--body-react_modal']"));

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ok']")));
        WebElement okClockInButton = driver.findElement(By.xpath("//button[text()='ok']"));
        okClockInButton.click();


        LocalTime myTime = LocalTime.now();
        WebElement messageOnPopup = driver.findElement(By.xpath("//div[@id='msg--body-react_modal'])"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        Assert.assertEquals(messageOnPopup, "You clocked in at" + dtf.format(myTime));


        //CLOCK OUT

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-type='PUNCH_OUT']")));
        WebElement clockOutButton = driver.findElement(By.xpath("//*[@data-type='PUNCH_OUT']"));
        clockOutButton.click();

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@role='alertdialog']")));

        WebElement messageClockOutPopup = driver.findElement(By.xpath("//*[@id='msg--body-react_modal']"));

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ok']")));
        WebElement okClockOutButton = driver.findElement(By.xpath("//button[text()='ok']"));
        okClockOutButton.click();


        //NAVIGATION

        WebElement hamburgerMenuButton = driver.findElement(By.xpath("//*[@id='mainMenuBtn']"));
        hamburgerMenuButton.click();

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Team']")));
        WebElement teamButton = driver.findElement(By.xpath("//button[@title='Team']"));
        teamButton.click();

        WebElement hrButton = driver.findElement(By.xpath("//button[@aria-label='HR section']"));
        hrButton.click();

        WebElement formsButton = driver.findElement(By.xpath("//button[@aria-label='HR - Forms section']"));
        formsButton.click();

        WebElement otherFormsButton = driver.findElement(By.cssSelector("#tab-manager [aria-label='Forms - Other Forms']"));
        otherFormsButton.click();

        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'btn btn-secondary ') and text()='Open']")));
        WebElement openTab = driver.findElement(By.xpath("//*[contains(@class,'btn btn-secondary ') and text()='Open']"));
        openTab.click();


        //NOTES


        //WebElement firstRow = driver.findElement(By.xpath("//table[@class='div-table-body']/tr[1]"));


        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")));
        int numberOfNotes;
        if (driver.findElement(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")).isDisplayed()) {
            numberOfNotes = Integer.parseInt(driver.findElement(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")).getAttribute("data-counter"));
        } else {
            numberOfNotes = 0;
        }

        WebElement threeDots = driver.findElement(By.xpath("(//*[@class='div-table-body']/tr)[1]//i[@class='icon-more-actions']"));
        threeDots.click();

        WebElement addNotesOption = driver.findElement(By.xpath("//button[text()='Add Notes']"));
        addNotesOption.click();

        WebElement notesPopup = driver.findElement(By.xpath("//*[@role='dialog']"));

        WebElement commentArea = notesPopup.findElement(By.xpath("//textarea[@name='comment']"));
        //commentArea.click();
        commentArea.sendKeys("New Comment");

        WebElement saveButton = notesPopup.findElement(By.xpath("//button[text()='save']"));
        saveButton.click();

        int numberOfAddedNotes = Integer.parseInt(driver.findElement(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")).getAttribute("data-counter"));
        Assert.assertEquals(numberOfAddedNotes, numberOfNotes + 1);

        driver.quit();
    }
}