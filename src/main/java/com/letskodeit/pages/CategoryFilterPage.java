package com.letskodeit.pages;

import com.letskodeit.base.BasePage;

import org.openqa.selenium.WebDriver;

public class CategoryFilterPage extends BasePage{

    public CategoryFilterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    private String CATEGORY_DROPDOWN = "xpath=>//select[@name = 'categories']";

    public ResultsPage select(String categoryName) {
        // Find category dropdown
        selectOption(CATEGORY_DROPDOWN, categoryName, "Category Dropdown");
        
        return new ResultsPage(driver);
    }

}
