package main.nopcommerce.com;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pom.Common;
import pom.LoginPage;
import pom.RegisterPage;

import java.util.Hashtable;

public class BaseTest {

    protected WebDriver driver = null;
    protected Faker faker = new Faker();
    protected Hashtable<String, String> account = new Hashtable<String, String>();
    protected Common cm;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;

    @BeforeMethod
    public void loadHomePage(){
        driver = new ChromeDriver();
        driver.get(Config.URL);
        cm = new Common(this.driver);
        loginPage = new LoginPage(this.driver);
        registerPage = new RegisterPage(this.driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
