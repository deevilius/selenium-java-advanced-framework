package com.letskodeit.pages;

import com.letskodeit.base.BasePage;

import org.openqa.selenium.WebDriver;

public class SearchBarPage extends BasePage{

    public SearchBarPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    
    public WebDriver driver;
    private String SEARCH_COURSE_FIELD = "class=>find-input";
    private String SEARCH_COURSE_BUTTON = "class=>find-course";

    public ResultsPage course(String courseName) {
        sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field");
        elementClick(SEARCH_COURSE_BUTTON, "Search Course Button");

        return new ResultsPage(driver);
    }
}
