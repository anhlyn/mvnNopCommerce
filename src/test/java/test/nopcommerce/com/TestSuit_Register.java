package test.nopcommerce.com;

import com.github.javafaker.Faker;
import main.nopcommerce.com.BaseTest;
import main.nopcommerce.com.UI_Register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Hashtable;

public class TestSuit_Register extends BaseTest {
    private void typeAndClickRegister(String fn, String ln, String email, String p, String cp){
        if(!fn.isEmpty()){
            type(UI_Register.TXT_FIRSTNAME, fn);
        }
        if(!ln.isEmpty()){
            type(UI_Register.TXT_LASTNAME, ln);
        }
        if(!email.isEmpty()){
            type(UI_Register.TXT_EMAIL, email);
        }
        if(!p.isEmpty()){
            type(UI_Register.TXT_PASS, p);
        }
        if(!cp.isEmpty()){
            type(UI_Register.TXT_PASSCONFIRM, cp);
        }
        click(UI_Register.BTN_RIGISTER);
    }

    @Test
    public void TC01_EmptyData(){
        loadNavByText("register", "register");
        click(UI_Register.BTN_RIGISTER);

        Assert.assertEquals(getText(UI_Register.SPAN_FIRSTNAME_ERR), "First name is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_LASTNAME_ERR), "Last name is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_EMAIL_ERR), "Email is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_PASS_ERR), "Password is required.");
        Assert.assertEquals(getText(UI_Register.SPAN_PASS_CONFIRM_ERR), "Password is required.");
    }

    @Test
    public void TC02_InvalidEmail(){
        loadNavByText("register", "register");

        String password = faker.internet().password(6,8);
        typeAndClickRegister(faker.name().firstName(), faker.name().lastName(), "test@", password, password);

        Assert.assertEquals(getText(UI_Register.SPAN_EMAIL_ERR), "Wrong email");
    }

    @Test(enabled = false)
    public void TC03_ValidAccount(){
        //preparing test data
        account.put("firstname", faker.name().firstName());
        account.put("lastname", faker.name().lastName());
        account.put("email", faker.internet().emailAddress());
        account.put("password", faker.internet().password(6,8));

        loadNavByText("register", "register");
        typeAndClickRegister(account.get("firstname"), account.get("lastname"), account.get("email"), account.get("password"), account.get("password"));
        System.out.println(account.toString());
        Assert.assertEquals(getText(UI_Register.RESULT_MSG), "Your registration completed");

    }

    @Test
    @Parameters({"email", "password", "firstname", "lastname"})
    public void TC04_ExistedEmail(String mail, String p, String fn, String ln){
        loadNavByText("register", "register");
        typeAndClickRegister(fn, ln, mail, p, p);
        Assert.assertEquals(getText(UI_Register.VALIDATION_SUM_ERROR), "The specified email already exists");
    }

    @Test
    public void TC05_PasswordLessThan6Chars(){
        loadNavByText("register", "register");
        typeAndClickRegister(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "1234", "1234");
        Assert.assertTrue(getText(UI_Register.SPAN_PASS_ERR).contains("must have at least 6 characters"));
    }

    @Test
    public void TC06_PassAndPassConfirmNotMatch(){
        loadNavByText("register", "register");
        typeAndClickRegister(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "123456", "123400");
        Assert.assertEquals(getText(UI_Register.SPAN_PASS_CONFIRM_ERR), "The password and confirmation password do not match.");
    }
}