package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver.findElement(By.cssSelector(".radius")).click();
//       verification
//       new url
//       logout button is visible
//       successful login message
        driver.close();
        System.out.println("Test finished");

    }
}
