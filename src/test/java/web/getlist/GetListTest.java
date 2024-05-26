package web.getlist;

import base.TestUtilities;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

public class GetListTest extends TestUtilities {
    @Parameters({"jsonPath"})
    @Epic("amazontest")
    @Feature("Add")
    @Test(testName = "getList", description = "חיפוש ובדיקות באתר")
    public void GetList(String jsonPath) {
        HomePage home = new HomePage(getDriver(), log);
        home.type_search_bar(dJson(jsonPath).readData("title"))
                .click_serach_icon()
                .countProductTitles();

        sleep(4000);


    }

}
