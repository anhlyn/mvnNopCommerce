package demo.nopcommerce.com;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestSuit_Login {

    Helper helperObj = new Helper();
    WebDriver browserObj = null;
    Faker faker = new Faker();

    Hashtable<String, String> account = new Hashtable<>();

    @BeforeMethod
    public void openBrowser(){
        helperObj.openBrowser();
        browserObj = helperObj.getDriverObj();
    }

    @AfterMethod(enabled = false)
    public void quitBrowser(){
        browserObj.quit();
    }

    @Test
    public void TC01_EmptyData(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Login.SPAN_EMAIL_ERR)).getText(), "Please enter your email");
    }

    @Test
    public void TC02_InvalidEmail(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.TXT_EMAIL)).sendKeys("test");
        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Login.SPAN_EMAIL_ERR)).getText(), "Wrong email");
    }

    @Test
    public void TC03_UnregisteredEmail(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.TXT_EMAIL)).sendKeys(faker.internet().emailAddress());
        browserObj.findElement(By.xpath(UI_Login.TXT_PASS)).sendKeys(faker.internet().password(6,8));
        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();

        String msgSummaryErr = browserObj.findElement(By.xpath(UI_Login.DIV_VALID_SUMMARY_ERR)).getText();
        Assert.assertTrue(msgSummaryErr.contains("Login was unsuccessful. Please correct the errors and try again."));
        Assert.assertTrue(msgSummaryErr.contains("No customer account found"));
    }

    @Test
    public void RegisterNewAcc(){
        TestSuit_Register tsRegisterObj = new TestSuit_Register(this.helperObj);
        tsRegisterObj.loadRegisterPage();

        //preparing test data
        account.put("firstname", faker.name().firstName());
        account.put("lastname", faker.name().lastName());
        account.put("email", faker.internet().emailAddress());
        account.put("password", faker.internet().password(6,8));

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(account.get("firstname"));
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(account.get("lastname"));
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys(account.get("email"));
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys(account.get("password"));
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys(account.get("password"));
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        System.out.println(account.toString());
        String resultMsg = browserObj.findElement(By.xpath(UI_Register.RESULT_MSG)).getText();
        Assert.assertEquals(resultMsg, "Your registration completed");
    }

    @Test(dependsOnMethods = "RegisterNewAcc")
    public void TC04_LoginWithExistedEmail_EmptyPass(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.TXT_EMAIL)).sendKeys(account.get("email"));
        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();
    }

    @Test(dependsOnMethods = "RegisterNewAcc")
    public void TC05_LoginWithExistedEmail_WrongPass(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.TXT_EMAIL)).sendKeys(account.get("email"));
        browserObj.findElement(By.xpath(UI_Login.TXT_PASS)).sendKeys(faker.internet().password(6,8));
        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();

        String msgSummaryErr = browserObj.findElement(By.xpath(UI_Login.DIV_VALID_SUMMARY_ERR)).getText();
        Assert.assertTrue(msgSummaryErr.contains("Login was unsuccessful. Please correct the errors and try again."));
        Assert.assertTrue(msgSummaryErr.contains("The credentials provided are incorrect"));
    }

    @Test(dependsOnMethods = "RegisterNewAcc")
    public void TC06_LoginSuccessWithNewAcc(){
        helperObj.loadHomepage();
        helperObj.loadNavByText("Login", "Welcome, Please Sign In!");

        browserObj.findElement(By.xpath(UI_Login.TXT_EMAIL)).sendKeys(account.get("email"));
        browserObj.findElement(By.xpath(UI_Login.TXT_PASS)).sendKeys(account.get("password"));
        browserObj.findElement(By.xpath(UI_Login.BTN_LOGIN)).click();

        Assert.assertTrue(browserObj.findElement(By.xpath(UI_Common.HEADER_NAV_LOGOUT)).isDisplayed());
    }

}
