package demo.nopcommerce.com;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSuit_Register {

    Helper helperObj = new Helper();
    WebDriver browserObj = null;

    @BeforeMethod
    public void openBrowser(){
        helperObj.openBrowser();
        browserObj = helperObj.getDriverObj();
    }

    @AfterMethod
    public void closeBrowser(){
        browserObj.close();
    }
    @Test
    public void TC01_EmptyData(){
        //load Homepage
        Assert.assertTrue(helperObj.loadHomepage());
        //load Register page
        Assert.assertTrue(helperObj.loadNavByText("Register", "Register"));
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
        //load Homepage
        Assert.assertTrue(helperObj.loadHomepage());
        //load Register page
        Assert.assertTrue(helperObj.loadNavByText("Register", "Register"));

        //Generate faker data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "test";
        String password = faker.internet().password(6,8);

        browserObj.findElement(By.xpath(UI_Register.TXT_FIRSTNAME)).sendKeys(firstName);
        browserObj.findElement(By.xpath(UI_Register.TXT_LASTNAME)).sendKeys(lastName);
        browserObj.findElement(By.xpath(UI_Register.TXT_EMAIL)).sendKeys(email);
        browserObj.findElement(By.xpath(UI_Register.TXT_PASS)).sendKeys(password);
        browserObj.findElement(By.xpath(UI_Register.TXT_PASSCONFIRM)).sendKeys(password);
        browserObj.findElement(By.xpath(UI_Register.BTN_RIGISTER)).click();

        Assert.assertEquals(browserObj.findElement(By.xpath(UI_Register.SPAN_EMAIL_ERR)).getText(), "Wrong email");
    }

}
