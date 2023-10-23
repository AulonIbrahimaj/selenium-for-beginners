package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void incorrectUsernameTest() {

        System.out.println("Starting incorrect test");

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
        username.sendKeys("incorrectUsername");

//       enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

//       click login button
        WebElement loginBtn = driver.findElement(By.cssSelector(".radius"));
        loginBtn.click();

        //verifications
        WebElement errorMessage = driver.findElement(By.id("flash"));

        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contains expected. \nActual: " + actualErrorMessage+
                "\nExpected: " + expectedErrorMessage);

//        Close browser
        driver.quit();
    }
}
