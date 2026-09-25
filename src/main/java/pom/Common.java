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

    @FindBy(xpath = "//a[@href='/contactus' and contains(@class,'footer-menu__link')]")
    WebElement footerLinkContactUs;

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

    public void goToContactUs(){
        customClick(this.footerLinkContactUs);
    }
}
