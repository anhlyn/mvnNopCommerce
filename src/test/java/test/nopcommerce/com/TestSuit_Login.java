package test.nopcommerce.com;

import com.github.javafaker.Faker;
import main.nopcommerce.com.BaseTest;
import main.nopcommerce.com.UI_Common;
import main.nopcommerce.com.UI_Login;
import main.nopcommerce.com.UI_Register;
import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestSuit_Login extends BaseTest {
    @Test
    public void TC01_EmptyData(){
        loadNavByText("Login", "Welcome, Please Sign In!");

        click(UI_Login.BTN_LOGIN);
        Assert.assertEquals(getText(UI_Login.SPAN_EMAIL_ERR), "Please enter your email");
    }

    @Test
    public void TC02_InvalidEmail(){
        loadNavByText("Login", "Welcome, Please Sign In!");

        type(UI_Login.TXT_EMAIL, "test");
        click(UI_Login.BTN_LOGIN);
        Assert.assertEquals(getText(UI_Login.SPAN_EMAIL_ERR), "Wrong email");
    }

    @Test
    public void TC03_UnregisteredEmail(){
        loadNavByText("Login", "Welcome, Please Sign In!");

        type(UI_Login.TXT_EMAIL, faker.internet().emailAddress());
        type(UI_Login.TXT_PASS, faker.internet().password(6,8));
        click(UI_Login.BTN_LOGIN);

        String msgSummaryErr = getText(UI_Login.DIV_VALID_SUMMARY_ERR);
        Assert.assertTrue(msgSummaryErr.contains("Login was unsuccessful. Please correct the errors and try again."));
        Assert.assertTrue(msgSummaryErr.contains("No customer account found"));
    }

    @Test(enabled = false)
    public void RegisterNewAcc(){
        loadNavByText("register", "register");

        //preparing test data
        account.put("firstname", faker.name().firstName());
        account.put("lastname", faker.name().lastName());
        account.put("email", faker.internet().emailAddress());
        account.put("password", faker.internet().password(6,8));

        type(UI_Register.TXT_FIRSTNAME, account.get("firstname"));
        type(UI_Register.TXT_LASTNAME, account.get("lastname"));
        type(UI_Register.TXT_EMAIL, account.get("email"));
        type(UI_Register.TXT_PASS, account.get("password"));
        type(UI_Register.TXT_PASSCONFIRM, account.get("password"));
        click(UI_Register.BTN_RIGISTER);

        System.out.println(account.toString());
        String resultMsg = getText(UI_Register.RESULT_MSG);
        Assert.assertEquals(resultMsg, "Your registration completed");
    }

    @Test
    @Parameters({"email"})
    public void TC04_LoginWithExistedEmail_EmptyPass(String mail){
        loadNavByText("Login", "Welcome, Please Sign In!");

        type(UI_Login.TXT_EMAIL, mail);
        click(UI_Login.BTN_LOGIN);
    }

    @Test
    @Parameters({"email"})
    public void TC05_LoginWithExistedEmail_WrongPass(String mail){
        loadNavByText("Login", "Welcome, Please Sign In!");

        type(UI_Login.TXT_EMAIL, mail);
        type(UI_Login.TXT_PASS, faker.internet().password(6,8));
        click(UI_Login.BTN_LOGIN);

        String msgSummaryErr = getText(UI_Login.DIV_VALID_SUMMARY_ERR);
        Assert.assertTrue(msgSummaryErr.contains("Login was unsuccessful. Please correct the errors and try again."));
        Assert.assertTrue(msgSummaryErr.contains("The credentials provided are incorrect"));
    }

    @Test
    @Parameters({"email", "password"})
    public void TC06_LoginSuccess(String mail, String p){
        loadNavByText("Login", "Welcome, Please Sign In!");

        type(UI_Login.TXT_EMAIL, mail);
        type(UI_Login.TXT_PASS, p);
        click(UI_Login.BTN_LOGIN);

        Assert.assertTrue(isDisplayed(UI_Common.HEADER_NAV_LOGOUT));
    }

}
