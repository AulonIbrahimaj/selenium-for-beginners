package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {
    private WebDriver driver;

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
    }

    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest() {
        System.out.println("Test started");


//       open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

//       enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

//       enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

//       click login button
        WebElement loginBtn = driver.findElement(By.cssSelector(".radius"));
        loginBtn.click();

//       verifications:

//       new url
        String expectedURL = "https://the-internet.herokuapp.com/secure";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL, "Actual page url is not the same as expected");

//       logout button is visible
        WebElement logOutButton = driver.findElement(By.cssSelector(".icon-2x.icon-signout"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");

//       successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
        //Assert.assertEquals(actualMessage,expectedMessage,"Actual message is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message does not contain expected message.\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

        System.out.println("Test finished");

    }


    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        System.out.println("Starting negativeLoginTest with " + username + " and " + password);


//       open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

//       enter username
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);

//       enter password
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);

//       click login button
        WebElement loginBtn = driver.findElement(By.cssSelector(".radius"));
        loginBtn.click();

        //verifications
        WebElement errorMessage = driver.findElement(By.id("flash"));


        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contains expected. \nActual: " + actualErrorMessage +
                        "\nExpected: " + expectedErrorMessage);

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        //        Close browser
        driver.quit();
    }
}
