package test.nopcommerce.com;

import com.sun.tools.jconsole.JConsoleContext;
import main.nopcommerce.com.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pom.Common;
import pom.LoginPage;

public class TestSuite_Login extends BaseTest {

    @Test
    public void TC01_EmptyData(){
        cm.goToLogin();
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getEmailError(), "Please enter your email");
    }

    @Test
    public void TC02_InvalidEmail(){
        cm.goToLogin();
        loginPage.fillEmail("test");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getEmailError(), "Please enter a valid email address.");
    }

    @Test
    public void TC03_UnregisteredEmail(){
        cm.goToLogin();
        loginPage.fillEmail(faker.internet().emailAddress());
        loginPage.fillPass(faker.internet().password(6,8));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getSummaryError().contains("Login was unsuccessful."));
    }

    //need to investigate flaky test
    @Test
    @Parameters({"email"})
    public void TC04_LoginWithExistedEmail_EmptyPass(String mail){
        System.out.println(mail);
        cm.goToLogin();
        loginPage.fillEmail(mail);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getSummaryError().contains("Login was unsuccessful."));
    }

    @Test
    @Parameters({"email"})
    public void TC05_LoginWithExistedEmail_WrongPass(@Optional("hector.koelpin@gmail.com") String mail){
        cm.goToLogin();
        loginPage.fillEmail(mail);
        loginPage.fillPass(faker.internet().password(6,8));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getSummaryError().contains("The credentials provided are incorrect"));
    }

    @Test
    @Parameters({"email", "password"})
    public void TC06_LoginSuccess(@Optional("hector.koelpin@gmail.com") String mail,@Optional("iqemtg8") String p){
        cm.goToLogin();
        loginPage.fillEmail(mail);
        loginPage.fillPass(p);
        loginPage.clickLogin();
        Assert.assertTrue(cm.isLogoutDisplayed());
    }

}
