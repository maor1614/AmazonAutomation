package pages;

import base.BasePageObject;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePageObject {
    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchIcon;
    @FindBy(css = ".a-size-medium.a-color-base.a-text-normal")
    private List<WebElement> productTitles;

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

    @Step("ספירת כותרות המוצרים")
    public HomePage countProductTitles() {
        log.info("Counting product titles containing 'Harry Potter'");
        int count = 0;
        for (WebElement title : productTitles) {
            if (title.getText().contains("Harry Potter")) {
                count++;
            }
        }
        System.out.println("The term 'Harry Potter' appears in " + count + " product titles.");
        return this;
    }

}
