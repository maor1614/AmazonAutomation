package pages;

import base.BasePageObject;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePageObject {
    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchIcon;


    @Step("כתיבה בשורת חיפוש")
    public HomePage type_search_bar(String text) {
        log.info("type in search bar");
        searchBar.clear();
        Assert.assertTrue(mytype(searchBar, text), "typing in search bar not working");
        return this;
    }

    @Step("לחיצה על כפתור חיפוש")
    public HomePage click_serach_icon() {
        log.info("click search icon");
        Assert.assertTrue(m_click(searchIcon), "search icon not clicked ");
        return this;
    }


}
