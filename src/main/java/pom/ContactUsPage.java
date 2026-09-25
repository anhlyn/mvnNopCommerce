package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(id = "FullName")
    WebElement inputName;

    @FindBy(id = "Email")
    WebElement inputEmail;

    @FindBy(id = "Enquiry")
    WebElement inputEnquiry;

    @FindBy(name = "send-email")
    WebElement btnSubmit;

    @FindBy(css = "span[data-valmsg-for='FullName']")
    WebElement errName;

    @FindBy(css = "span[data-valmsg-for='Email']")
    WebElement errEmail;

    @FindBy(css = "span[data-valmsg-for='Enquiry']")
    WebElement errEnquiry;

    public ContactUsPage(WebDriver d){
        super(d);
    }

    public void clickSubmit(){
        customClick(this.btnSubmit);
    }

    public String getErrName(){
        return this.errName.getText();
    }

    public String getErrEmail(){
        return this.errEmail.getText();
    }

    public String getErrEnquiry(){
        return this.errEnquiry.getText();
    }
}
