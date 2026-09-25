package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    WebDriverWait driverWait;

    public BasePage(WebDriver d){
        this.driver = d;
        this.driverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);
    }

    protected void customFill(WebElement ele, String msg){
        ele = this.driverWait.until(
                ExpectedConditions.visibilityOf(ele)
        );
        ele.sendKeys(msg);
    }

    protected void customClick(WebElement ele){
        ele = this.driverWait.until(
                ExpectedConditions.visibilityOf(ele)
        );
        ele.click();
    }
}