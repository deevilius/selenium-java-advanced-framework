package com.letskodeit.overview;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        baseURL = "https://courses.letskodeit.com";
        driver.get(baseURL);
        
    }

    @Test
    public void testLogin() {
        driver.findElement(By.xpath("//a[contains(@href, 'login')]")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("test@email.com");
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys("abcabc");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        WebElement accountImage = null;
        try {
            accountImage =driver.findElement(By.id("dropdownMenu1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(accountImage);
    }
    
}
