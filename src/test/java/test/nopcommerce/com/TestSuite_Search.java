package test.nopcommerce.com;

import main.nopcommerce.com.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_Search extends BaseTest {

    @Test
    public void TC01_NoMatchingResults(){
        cm.goToSearch();
        searchPage.fillKeyword("zzzznoresultzzzz");
        searchPage.clickSearch();
        Assert.assertEquals(searchPage.getNoResultMsg(),
                "No products were found that matched your criteria.");
    }
}
