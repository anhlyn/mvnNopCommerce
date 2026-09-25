package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsletterPage extends BasePage {

    @FindBy(id = "newsletter-email")
    WebElement inputEmail;

    @FindBy(id = "newsletter-subscribe-button")
    WebElement btnSubscribe;

    @FindBy(id = "newsletter-result-block")
    WebElement msgResult;

    public NewsletterPage(WebDriver d){
        super(d);
    }

    public void fillEmail(String em){
        customFill(this.inputEmail, em);
    }

    public void clickSubscribe(){
        customClick(this.btnSubscribe);
    }

    public String getResultMsg(){
        this.driverWait.until(
                d -> !this.msgResult.getText().isEmpty()
        );
        return this.msgResult.getText();
    }
}
