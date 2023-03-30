package main.nopcommerce.com;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Hashtable;

public class BaseTest {

    protected WebDriver driver = null;
    protected Faker faker = new Faker();
    protected Hashtable<String, String> account = new Hashtable<>();

    @BeforeMethod
    public void loadHomePage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Config.URL);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected void click(By locator){
        find(locator).click();
    }

    protected void click(WebElement element){
        element.click();
    }

    protected void type(By locator, String text){
        find(locator).sendKeys(text);
    }

    protected void type(WebElement element, String text){
        element.sendKeys(text);
    }

    protected Boolean isDisplayed(By locator){
        return find(locator).isDisplayed();
    }

    protected Boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }

    protected String getText(By locator){
        return find(locator).getText();
    }

    protected String getText(WebElement element){
        return element.getText();
    }

    public Boolean loadNavByText(String text, String expectedText){
        String navLowerStr = text.toLowerCase();
        switch (navLowerStr){
            case "register":
                click(UI_Common.HEADER_NAV_REGISTER);
                break;
            case "login":
                click(UI_Common.HEADER_NAV_LOGIN);
                break;
            case "wishlist":
                click(UI_Common.HEADER_NAV_WISHLIST);
                break;
            default:
                click(UI_Common.HEADER_NAV_WISHLIST);
        }
        return getText(UI_Common.PAGE_TITLE).toLowerCase().equals(expectedText.toLowerCase());
    }

}
