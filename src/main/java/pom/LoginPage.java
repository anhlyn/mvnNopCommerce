package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "Email")
    WebElement inputEmail;

    @FindBy(id = "Password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit' and text()='Log in']")
    WebElement btnLogin;

    @FindBy(id = "Email-error")
    WebElement emError;

    @FindBy(xpath = "//div[contains(@class, 'validation-summary-errors')]")
    WebElement summaryError;

    public LoginPage(WebDriver d){
        super(d);
    }

    public void fillEmail(String em){
        customFill(this.inputEmail, em);
    }

    public void fillPass(String pass){
        customFill(this.inputPassword, pass);
    }

    public void clickLogin(){
        customClick(this.btnLogin);
    }

    public String getEmailError(){
        return this.emError.getText();
    }

    public String getSummaryError(){
        //return this.summaryError.getText();
        return customGetText(this.summaryError);
    }
}
