package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Common extends BasePage {
    @FindBy(xpath = "//div[@class='header-links']//a[@class='ico-register']")
    WebElement headerLinkRegister;

    @FindBy(xpath = "//div[@class='header-links']//a[@class='ico-login']")
    WebElement headerLinkLogin;

    @FindBy(xpath = "//div[@class='header-links']//a[@class='ico-logout']")
    WebElement headerLinkLogout;

    @FindBy(xpath = "//div[@class='page-title']/h1")
    WebElement headingTitle;

    public Common(WebDriver d){
        super(d);
    }

    public boolean isLogoutDisplayed(){
        return this.headerLinkLogout.isDisplayed();
    }

    public void goToLogin(){
        customClick(this.headerLinkLogin);
    }

    public void goToRegister(){
        customClick(this.headerLinkRegister);
    }

    public boolean navigateByText(String text, String expectedText){
        String navLowerStr = text.toLowerCase();
        switch (navLowerStr){
            case "register":
                this.headerLinkRegister.click();
                break;
            default:
                this.headerLinkLogin.click();
        }
        return this.headingTitle.getText().toLowerCase().equals(expectedText.toLowerCase());
    }

}
