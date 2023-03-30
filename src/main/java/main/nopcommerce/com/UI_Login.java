package main.nopcommerce.com;

import org.openqa.selenium.By;

public class UI_Login {
    public static final By TXT_EMAIL = By.id("Email");
    public static final By TXT_PASS = By.id("Password");
    public static final By BTN_LOGIN = By.xpath("//button[@type='submit' and text()='Log in']");
    public static final By SPAN_EMAIL_ERR = By.id("Email-error");
    public static final By DIV_VALID_SUMMARY_ERR = By.xpath("//div[contains(@class, 'validation-summary-errors')]");
}
