package com.letskodeit.pages;

import com.letskodeit.base.BasePage;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    
    private String EMAIL_FIELD = "id=>email";
    private String PASSWORD_FIELD = "id=>password";
    private String LOG_IN_BUTTON = "xpath=>//input[@value='Login']";

    public NavigationPage signInWith(String email, String password) {
        sendData(EMAIL_FIELD, email, "Email Field");
        sendData(PASSWORD_FIELD, password, "Password Field");
        elementClick(LOG_IN_BUTTON, "Login Button");
        return new NavigationPage(driver);
    }
}
