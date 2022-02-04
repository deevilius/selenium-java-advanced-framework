package com.letskodeit.pages;

import java.util.List;

import com.letskodeit.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage extends BasePage{
    
    public ResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    private String URL = "/courses";
    private String COURSES_LIST = "xpath=>//div[@class='zen-course-list']";

    public boolean isOpen() {
        return getURL().contains(URL);
    }

    public int coursesCount() {
        List<WebElement> coursesList = getElementList(COURSES_LIST, "Cources List");
        return coursesList.size();
    }

    public String filterResult() {
        WebElement filterResult = driver.findElement(By.tagName("h1"));
        String filterResultText = filterResult.getText();
        return filterResultText;
    }

    public boolean verifySearchResult() {
        boolean result = true;
        if (coursesCount() > 0 ) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }

    public boolean verifyFilterCourse(String category) {
        boolean result = false;
        if (filterResult() == category) {
            result = true;
        }
        return result;
    }
}
