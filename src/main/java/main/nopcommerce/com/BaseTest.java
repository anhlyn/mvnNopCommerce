package main.nopcommerce.com;

import com.github.javafaker.Faker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pom.Common;
import pom.ContactUsPage;
import pom.LoginPage;
import pom.NewsletterPage;
import pom.RegisterPage;
import pom.SearchPage;

import java.util.Hashtable;

public class BaseTest {

    protected WebDriver driver = null;
    protected Faker faker = new Faker();
    protected Hashtable<String, String> account = new Hashtable<String, String>();
    protected Common cm;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ContactUsPage contactUsPage;
    protected NewsletterPage newsletterPage;
    protected SearchPage searchPage;

    @BeforeMethod
    public void loadHomePage(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1200, 1366));

        driver.get(Config.URL);
        System.out.println("window size: " + driver.manage().window().getSize());
        cm = new Common(this.driver);
        loginPage = new LoginPage(this.driver);
        registerPage = new RegisterPage(this.driver);
        contactUsPage = new ContactUsPage(this.driver);
        newsletterPage = new NewsletterPage(this.driver);
        searchPage = new SearchPage(this.driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
