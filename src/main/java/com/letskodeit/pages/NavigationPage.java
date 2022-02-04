package com.letskodeit.pages;

import java.util.List;

import com.letskodeit.base.BasePage;
import com.letskodeit.utilities.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationPage extends BasePage{

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    private String LOGIN_LINK = "xpath=>//a[contains(@href, 'login')]";
    private String ALL_COURSES_LINK = "xpath=>//a[contains(@href, 'courses')]";
    private String MY_COURSES_LINK = "xpath=>//a[contains(@href, 'mycourses')]";
    private String ACCOUNT_ICON = "id=>dropdownMenu1";
    private String LOGOUT_LINK = "xpath=>//a[contains(@href, 'logout')]";

    public void allCourses() {
        elementClick(ALL_COURSES_LINK, "All Courses Link");
    }

    public void myCourses() {
        elementClick(MY_COURSES_LINK, "My Cources Link");
    }

    public LoginPage login() {
        elementClick(LOGIN_LINK, "Login Link");
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        try {
            List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
            if (accountImage.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyHeader() {
        String text = getText(ALL_COURSES_LINK, "All Cources Link");
        return Util.verifyTextContains(text, "ALL sadasdCOURSES");
    }

    public void logout() {
        elementClick(ACCOUNT_ICON, "User Account Icon");
        WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logoutLink, "Logout Link");
    }
}