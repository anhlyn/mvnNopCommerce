package main.nopcommerce.com;

import org.openqa.selenium.By;

public class UI_Register {
    public static final By BTN_RIGISTER = By.xpath("//button[@id='register-button']");
    public static final By SPAN_FIRSTNAME_ERR = By.id("FirstName-error");
    public static final By SPAN_LASTNAME_ERR = By.id("LastName-error");
    public static final By SPAN_EMAIL_ERR = By.id("Email-error");
    public static final By SPAN_PASS_ERR = By.id("Password-error");
    public static final By SPAN_PASS_CONFIRM_ERR = By.id("ConfirmPassword-error");
    public static final By TXT_FIRSTNAME = By.id("FirstName");
    public static final By TXT_LASTNAME = By.id("LastName");
    public static final By TXT_EMAIL = By.id("Email");
    public static final By TXT_PASS = By.id("Password");
    public static final By TXT_PASSCONFIRM = By.id("ConfirmPassword");
    public static final By RESULT_MSG = By.xpath("//div[@class='page-body']/div[@class='result']");
    public static final By VALIDATION_SUM_ERROR = By.xpath("//div[@class='page-body']//div[contains(@class,'validation-summary-errors')]/ul/li");

}
