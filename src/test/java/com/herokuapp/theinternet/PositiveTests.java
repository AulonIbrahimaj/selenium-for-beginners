package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
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
        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);
        System.out.println("Page is opened");

//       enter username
//       enter password
//       click login button
//       verification
//       new url
//       logout button is visible
//       successful login message
        driver.close();
        System.out.println("Test finished");

    }
}
