package demo.nopcommerce.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Helper {

    WebDriver driver = null;

    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriverObj(){
        return this.driver;
    }

    public Boolean loadHomepage(){
        driver.get(Config.URL);
        return driver.findElement(By.xpath(UI_Common.HEADER_LOGO)).isDisplayed();
    }

    public Boolean loadNavByText(String text, String expectedText){
        String navLowerStr = text.toLowerCase();
        switch (navLowerStr){
            case "register":
                driver.findElement(By.xpath(UI_Common.HEADER_NAV_REGISTER)).click();
                break;
            case "login":
                driver.findElement(By.xpath(UI_Common.HEADER_NAV_LOGIN)).click();
                break;
            case "wishlist":
                driver.findElement(By.xpath(UI_Common.HEADER_NAV_WISHLIST)).click();
                break;
            default:
                driver.findElement(By.xpath(UI_Common.HEADER_NAV_SHOPCART)).click();
        }
        return driver.findElement(By.xpath(UI_Common.PAGE_TITLE)).getText().toLowerCase().equals(expectedText.toLowerCase());
    }

}
