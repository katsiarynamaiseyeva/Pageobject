import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WfrWebDriver {

    public static void main(String[] args) {

        //TODO: WebDriver move to separate class
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://wfrsecure.int.kronos.com/ta/kmosm.login?rnd=HGG&@rtm=1");

        //LOGIN
        //TODO: LoginPage
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='Username']"));
        usernameField.sendKeys("mng01");

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='PasswordView']"));
        passwordField.sendKeys("Password1!");

        WebElement loginButton = driver.findElement(By.xpath("//input[@name='LoginButton']"));
        loginButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='icon-email' and @aria-label='To-Do']")));

        //CLOCK IN

        // TODO: DashboardPage
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='time-clock-button' and @data-type='PUNCH']")));
        WebElement clockInButton = driver.findElement(By.xpath("//button[@class='time-clock-button' and @data-type='PUNCH']"));
        clockInButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@role='alertdialog']")));

        WebElement messageClockInPopup = driver.findElement(By.xpath("//*[@id='msg--body-react_modal']"));

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ok']")));
        WebElement okClockInButton = driver.findElement(By.xpath("//button[text()='ok']"));
        okClockInButton.click();


        //LocalDateTime dt = DateTimeFormatter.ofPattern("yyyy/");


        //CLOCK OUT

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-type='PUNCH_OUT']")));
        WebElement clockOutButton = driver.findElement(By.xpath("//*[@data-type='PUNCH_OUT']"));
        clockOutButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@role='alertdialog']")));

        WebElement messageClockOutPopup = driver.findElement(By.xpath("//*[@id='msg--body-react_modal']"));

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ok']")));
        WebElement okClockOutButton = driver.findElement(By.xpath("//button[text()='ok']"));
        okClockOutButton.click();



        //LocalDateTime dt = DateTimeFormatter.ofPattern("yyyy/");


        //NAVIGATION
        //TODO: Menu has method navigate();
        //TODO: Page has open();
        WebElement hamburgerMenuButton = driver.findElement(By.xpath("//*[@id='mainMenuBtn']"));
        hamburgerMenuButton.click();

        //TODO: Remove explicit waiters
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Team']")));
        WebElement teamButton = driver.findElement(By.xpath("//button[@title='Team']"));
        teamButton.click();

        WebElement hrButton = driver.findElement(By.xpath("//button[@aria-label='HR section']"));
        hrButton.click();

        WebElement formsButton = driver.findElement(By.xpath("//button[@aria-label='HR - Forms section']"));
        formsButton.click();

        WebElement otherFormsButton = driver.findElement(By.cssSelector("#tab-manager [aria-label='Forms - Other Forms']"));
        otherFormsButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'btn btn-secondary ') and text()='Open']")));
        WebElement openTab = driver.findElement(By.xpath("//*[contains(@class,'btn btn-secondary ') and text()='Open']"));
        openTab.click();


        //NOTES


        OtherFormsPage otherFormsPage = new OtherFormsPage();
        otherFormsPage.open();
        final int firstRow = 1;
        final String comment = "Comment";

        otherFormsPage.addNoteToForm(firstRow, comment);
        //TODO: Add validation

        //WebElement firstRow = driver.findElement(By.xpath("//table[@class='div-table-body']/tr[1]"));

        //TODO: OtherFormsPage
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")));

        if (driver.findElement(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")).isDisplayed()) {
            int numberOfNotes = Integer.parseInt(driver.findElement(By.xpath("//td/span/button[contains(@class, 'btn-meta notes  unread')]")).getAttribute("data-counter"));
        } else {
            int numberOfNotes = 0;
        }

        //new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='portal-dropdown']//button[1]")));

        //WebElement threeDots = driver.findElement(By.xpath("//*[@class='portal-dropdown']//button[1]"));
        WebElement threeDots = driver.findElement(By.xpath("(//*[@class='div-table-body']/tr)[1]//i[@class='icon-more-actions']"));
        // (//*[@class='div-table-body']/tr)[1]
        threeDots.click();

        //TODO:popover portal
        WebElement addNotesOption = driver.findElement(By.xpath("//button[text()='Add Notes']"));
        addNotesOption.click();


        WebElement notesPopup = driver.findElement(By.xpath("//*[@role='dialog']"));

        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        //commentArea.click();
        commentArea.sendKeys("New Comment");

        WebElement saveButton = driver.findElement(By.xpath("//button[text()='save']"));
        saveButton.click();


        driver.quit();
    }
}