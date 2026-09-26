package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    @FindBy(id = "q")
    WebElement inputKeyword;

    @FindBy(css = "button.search-button")
    WebElement btnSearch;

    @FindBy(css = "div.search-results div.no-result")
    WebElement msgNoResult;

    public SearchPage(WebDriver d){
        super(d);
    }

    public void fillKeyword(String keyword){
        customFill(this.inputKeyword, keyword);
    }

    public void clickSearch(){
        customClick(this.btnSearch);
    }

    public String getNoResultMsg(){
        return this.driverWait.until(
                d -> this.msgNoResult.isDisplayed() ? this.msgNoResult.getText() : null
        );
    }
}
