package demo.nopcommerce.com;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestSuit_Register {

    Helper helperObj = new Helper();
    WebDriver browserObj = null;

    Faker faker = new Faker();
    Hashtable<String, String> account = new Hashtable<>();

    public TestSuit_Register(){
    }

    public TestSuit_Register(Helper h){
        helperObj = h;
    }

    @BeforeMethod
    public void openBrowser(){
        helperObj.openBrowser();
        browserObj = helperObj.getDriverObj();
    }

    @AfterMethod(enabled = true)
    public void closeBrowser(){
        browserObj.quit();
    }

    @AfterClass(enabled = false)
    public void quitBrowser(){
        browserObj.quit();
    }

    public void loadRegisterPage(){
        //load Homepage
        Assert.assertTrue(helperObj.loadHomepage());
        //load Register page
        Assert.assertTrue(helperObj.loadNavByText("Register", "Register"));
    }
    @Test
    public void TC01_EmptyData(){
        loadRegisterPage();
        //click btn Register
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_FIRSTNAME_ERR)).getText(), "First name is required.");
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_LASTNAME_ERR)).getText(), "Last name is required.");
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_EMAIL_ERR)).getText(), "Email is required.");
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_PASS_ERR)).getText(), "Password is required.");
        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_PASS_CONFIRM_ERR)).getText(), "Password is required.");
    }

    @Test
    public void TC02_InvalidEmail(){
        loadRegisterPage();
        //Generate faker data
        String password = faker.internet().password(6,8);

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(faker.name().firstName());
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(faker.name().lastName());
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys("test");
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys(password);
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys(password);
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_EMAIL_ERR)).getText(), "Wrong email");
    }

    @Test
    public void TC03_ValidAccount(){
        loadRegisterPage();
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

    @Test(dependsOnMethods = "TC03_ValidAccount")
    public void TC04_ExistedEmail(){
        loadRegisterPage();

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(faker.name().firstName());
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(faker.name().lastName());
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys(account.get("email"));
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys("123456");
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys("123456");
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        String msgSummaryErr = browserObj.findElement(By.xpath(UI_Register.VALIDATION_SUM_ERROR)).getText();
        Assert.assertEquals(msgSummaryErr, "The specified email already exists");
    }

    @Test
    public void TC05_PasswordLessThan6Chars(){
        loadRegisterPage();

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(faker.name().firstName());
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(faker.name().lastName());
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys(faker.internet().emailAddress());
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys("1234");
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys("1234");
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        String msgPassError = browserObj.findElement(By.xpath(UI_Register.SPAN_PASS_ERR)).getText();
        Assert.assertTrue(msgPassError.contains("must have at least 6 characters"));
    }

    @Test
    public void TC06_PassAndPassConfirmNotMatch(){
        loadRegisterPage();

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(faker.name().firstName());
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(faker.name().lastName());
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys(faker.internet().emailAddress());
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys("123456");
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys("654321");
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        String msgPassConfirmError = browserObj.findElement(By.xpath(UI_Register.SPAN_PASS_CONFIRM_ERR)).getText();
        Assert.assertEquals(msgPassConfirmError, "The password and confirmation password do not match.");
    }

}
