package test.nopcommerce.com;

import com.github.javafaker.Faker;
import main.nopcommerce.com.BaseTest;
import main.nopcommerce.com.UI_Register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestSuit_Register extends BaseTest {

    Faker faker = new Faker();
    Hashtable<String, String> account = new Hashtable<>();

    //@Test
    public void tc01(){

    }

    @Test
    public void TC01_EmptyData(){
        loadNavByText("register", "register");

        Assert.assertEquals(getText(UI_Register.SPAN_FIRSTNAME_ERR), "First name is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_LASTNAME_ERR), "Last name is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_EMAIL_ERR), "Email is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_PASS_ERR), "Password is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_PASS_CONFIRM_ERR), "Password is required.");

        click(UI_Register.BTN_RIGISTER);
    }

    @Test
    public void TC02_InvalidEmail(){
        loadNavByText("register", "register");
        //Generate faker data
        String password = faker.internet().password(6,8);
        type(UI_Register.TXT_FIRSTNAME, faker.name().firstName());
        type(UI_Register.TXT_LASTNAME, faker.name().lastName());
        type(UI_Register.TXT_EMAIL, faker.internet().emailAddress());
        type(UI_Register.TXT_PASS, password);
        type(UI_Register.TXT_PASSCONFIRM, password);
        click(UI_Register.BTN_RIGISTER);
        Assert.assertEquals(getText(UI_Register.SPAN_EMAIL_ERR), "Wrong email");
    }

    @Test
    public void TC03_ValidAccount(){
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

    @Test(dependsOnMethods = "TC03_ValidAccount")
    public void TC04_ExistedEmail(){
        loadNavByText("register", "register");

        type(UI_Register.TXT_FIRSTNAME, account.get("firstname"));
        type(UI_Register.TXT_LASTNAME, account.get("lastname"));
        type(UI_Register.TXT_EMAIL, account.get("email"));
        type(UI_Register.TXT_PASS, account.get("password"));
        type(UI_Register.TXT_PASSCONFIRM, account.get("password"));
        click(UI_Register.BTN_RIGISTER);

        String msgSummaryErr = getText(UI_Register.VALIDATION_SUM_ERROR);
        Assert.assertEquals(msgSummaryErr, "The specified email already exists");
    }

    @Test
    public void TC05_PasswordLessThan6Chars(){
        loadNavByText("register", "register");

        type(UI_Register.TXT_FIRSTNAME, faker.name().firstName());
        type(UI_Register.TXT_LASTNAME, faker.name().lastName());
        type(UI_Register.TXT_EMAIL, faker.internet().emailAddress());
        type(UI_Register.TXT_PASS, "1234");
        type(UI_Register.TXT_PASSCONFIRM, "1234");
        click(UI_Register.BTN_RIGISTER);


        String msgPassError = getText(UI_Register.SPAN_PASS_ERR);
        Assert.assertTrue(msgPassError.contains("must have at least 6 characters"));
    }

    @Test
    public void TC06_PassAndPassConfirmNotMatch(){
        loadNavByText("register", "register");

        type(UI_Register.TXT_FIRSTNAME, faker.name().firstName());
        type(UI_Register.TXT_LASTNAME, faker.name().lastName());
        type(UI_Register.TXT_EMAIL, faker.internet().emailAddress());
        type(UI_Register.TXT_PASS, "123456");
        type(UI_Register.TXT_PASSCONFIRM, "654321");
        click(UI_Register.BTN_RIGISTER);

        String msgPassConfirmError = getText(UI_Register.SPAN_PASS_CONFIRM_ERR);
        Assert.assertEquals(msgPassConfirmError, "The password and confirmation password do not match.");
    }

}
