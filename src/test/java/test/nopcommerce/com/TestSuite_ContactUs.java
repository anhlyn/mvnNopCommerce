package test.nopcommerce.com;

import main.nopcommerce.com.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_ContactUs extends BaseTest {

    @Test
    public void TC01_EmptyData(){
        cm.goToContactUs();
        contactUsPage.clickSubmit();
        Assert.assertEquals(contactUsPage.getErrName(), "Enter your name");
        Assert.assertEquals(contactUsPage.getErrEmail(), "Enter email");
        Assert.assertEquals(contactUsPage.getErrEnquiry(), "Enter enquiry");
    }
}
