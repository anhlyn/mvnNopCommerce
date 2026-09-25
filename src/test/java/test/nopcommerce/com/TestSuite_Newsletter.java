package test.nopcommerce.com;

import main.nopcommerce.com.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_Newsletter extends BaseTest {

    @Test
    public void TC01_SubscribeFromFooter(){
        newsletterPage.fillEmail(faker.internet().emailAddress());
        newsletterPage.clickSubscribe();
        Assert.assertEquals(newsletterPage.getResultMsg(),
                "Thank you for signing up! A verification email has been sent. We appreciate your interest.");
    }
}
