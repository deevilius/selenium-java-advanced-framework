package com.letskodeit.base;

import java.time.Duration;

import com.letskodeit.pages.CategoryFilterPage;
import com.letskodeit.pages.LoginPage;
import com.letskodeit.pages.NavigationPage;
import com.letskodeit.pages.ResultsPage;
import com.letskodeit.pages.SearchBarPage;

import com.letskodeit.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    public WebDriver driver;
    protected String baseURL;
    protected LoginPage login;
    protected NavigationPage nav;
    protected SearchBarPage search;
    protected ResultsPage result;
    protected CategoryFilterPage category;
    
    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(@Optional("chrome") String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        nav = new NavigationPage(driver);
        login = nav.login();
    }
    @BeforeMethod
    public void  methodSetup() {
        CheckPoint.clearHashMap();
    }
    
    @AfterClass
    public void commonTearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}
