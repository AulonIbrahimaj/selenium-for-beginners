package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ExceptionTests {

    private WebDriver driver;
    WebDriverWait wait;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser) {
        //        Create driver
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Don't know to start " + browser + ", starting chrome instead");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        System.out.println("Browser started");

        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        //        Close browser
        driver.quit();
    }

    @Test
    public void noSuchElementExceptionTest() {
        /*
          Test case 1: NoSuchElementException
          Open page
          Click Add button
          Verify Row 2 input field is displayed
         */

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement addButtonElement = driver.findElement(By.id("add_btn"));
        addButtonElement.click();

        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']//input[@type='text']")));
        Assert.assertTrue(row2Input.isDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void elementNotInteractableExceptionTest() {
        /*
         * Test case 2: ElementNotInteractableException
         * Open page
         * Click Add button
         * Wait for the second row to load
         * Type text into the second input field
         * Push Save button using locator By.name(“Save”)
         * Verify text saved
         */

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement addButtonElement = driver.findElement(By.id("add_btn"));
        addButtonElement.click();

        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='row2']//input[@type='text']")));

        row2Input.sendKeys("Sushi");

        WebElement saveBtn = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
        saveBtn.click();

        WebElement confirmationMessage = driver.findElement(By.id("confirmation"));
        String messageText = confirmationMessage.getText();
        Assert.assertEquals(messageText, "Row 2 was saved", "Confirmation text is not as expected");
    }

    @Test
    public void invalidElementStateExceptionTest() {
        /*
         * Test case 3: InvalidElementStateException
         * Open page
         * Clear input field
         * Type text into the input field
         * Verify text changed
         */
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement row1Input = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='row1']//input[@type='text']")));

        WebElement editBtn = driver.findElement(By.id("edit_btn"));
        editBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(row1Input));

        row1Input.clear();
        row1Input.sendKeys("Sushi");

        WebElement saveBtn = driver.findElement(By.id("save_btn"));
        saveBtn.click();

        String value = row1Input.getAttribute("value");
        Assert.assertEquals(value, "Sushi", "Input 1 field value is not as expected");

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String messageText = confirmationMessage.getText();
        Assert.assertEquals(messageText, "Row 1 was saved", "Confirmation text is not as expected");
    }

    @Test
    public void staleElementReferenceExceptionTest() {
        /**
         * Test case 4: StaleElementReferenceException
         * Open page
         * Find the instructions text element
         * Push add button
         * Verify instruction text element is no longer displayed
         */

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

        WebElement addButtonElement = driver.findElement(By.id("add_btn"));
        addButtonElement.click();

        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))), "Instructions are still displayed");
    }

    @Test
    public void timeoutExceptionTest() {
        /**
         * Test case 5: TimeoutException
         * Open page
         * Click Add button
         * Wait for 3 seconds for the second input field to be displayed
         * Verify second input field is displayed
         */

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

        WebElement addButtonElement = driver.findElement(By.id("add_btn"));
        addButtonElement.click();

        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='row2']//input[@type='text']")));

        Assert.assertTrue(row2Input.isDisplayed(), "Row 2 is not displayed");

    }
}
