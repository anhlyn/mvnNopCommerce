package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//button[@id='register-button']")
    WebElement btnRegister;
    @FindBy(id = "FirstName")
    WebElement inputFirstName;
    @FindBy(id = "LastName")
    WebElement inputLastName;
    @FindBy(id = "Email")
    WebElement inputEmail;
    @FindBy(id = "Password")
    WebElement inputPass;
    @FindBy(id = "ConfirmPassword")
    WebElement inputPassConfirm;
    @FindBy(id = "FirstName-error")
    WebElement errFirstName;
    @FindBy(id = "LastName-error")
    WebElement errLastName;
    @FindBy(id = "Email-error")
    WebElement errEmail;
    @FindBy(css = "span[data-valmsg-for='Password']")
    WebElement errPass;
    @FindBy(id = "ConfirmPassword-error")
    WebElement errPassConfirm;
    @FindBy(xpath = "//div[@class='page-body']//div[contains(@class,'validation-summary-errors')]/ul/li")
    WebElement errSummary;
    @FindBy(xpath = "//div[@class='page-body']/div[@class='result']")
    WebElement msgResult;

    public RegisterPage(WebDriver d){
        super(d);
    }

    public void fillFirstName(String fn){
        customFill(this.inputFirstName, fn);
    }

    public void fillLastName(String ln){
        customFill(this.inputLastName, ln);
    }

    public void fillEmail(String em){
        customFill(this.inputEmail, em);
    }

    public void fillPass(String p){
        customFill(this.inputPass, p);
    }

    public void fillConfirmPass(String cp){
        customFill(this.inputPassConfirm, cp);
    }

    public void fillForm(String fn, String ln, String em, String p, String cp){
        if(!fn.isEmpty()){
            customFill(this.inputFirstName, fn);
        }
        if(!ln.isEmpty()){
            customFill(this.inputLastName, ln);
        }
        if(!em.isEmpty()){
            customFill(this.inputEmail, em);
        }
        if(!p.isEmpty()){
            customFill(this.inputPass, p);
        }
        if(!cp.isEmpty()){
            customFill(this.inputPassConfirm, cp);
        }
    }

    public void clickRegister(){
        customClick(this.btnRegister);
    }

    public String getErrFirstName(){
        return this.errFirstName.getText();
    }

    public String getErrLastName(){
        return this.errLastName.getText();
    }

    public String getErrEmail(){
        return this.errEmail.getText();
    }

    public String getErrPass(){
        return this.errPass.getText();
    }

    public String getErrPassConfirm(){
        return this.errPassConfirm.getText();
    }

    public String getErrSummary(){
        return this.errSummary.getText();
    }

    public String getResultMsg(){
        return this.msgResult.getText();
    }
}
