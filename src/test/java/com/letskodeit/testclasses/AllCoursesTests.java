package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeit.pages.CategoryFilterPage;
import com.letskodeit.pages.SearchBarPage;

import com.letskodeit.utilities.Constants;
import com.letskodeit.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @DataProvider(name = "verifySearchCourseData")
    public Object[][] getVerifySearchCourseData(){
        return  ExcelUtility.getTestData("verify_search_course");
    }

    @BeforeClass
    public void setUp() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "AllCoursesTests");
    }

    @Test(dataProvider = "verifySearchCourseData")
    public void verifySearchCourse(String courseName) {
        nav.allCourses();
        search = new SearchBarPage(driver);
        result = search.course(courseName);
        boolean searchResult = result.verifySearchResult();
        CheckPoint.markFinal("test-02", searchResult, "search course verification");
    }

    @Test(enabled = false)
    public void filterByCategory() {
        nav.allCourses();
        category = new CategoryFilterPage(driver);
        result = category.select("Software Testing");
        boolean filterResult = result.verifyFilterCourse("Category : Software Development");
        CheckPoint.markFinal("test-03", filterResult, "filter by category verification");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
