package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() {
        System.out.println("Test started");

//        Create driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser started");

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

        driver.close();
        System.out.println("Test finished");

    }
}
