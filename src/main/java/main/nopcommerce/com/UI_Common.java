package main.nopcommerce.com;

import org.openqa.selenium.By;

public class UI_Common {

    public static final By HEADER_LOGO =By.xpath("//div[@class='header-logo']");
    public static final By HEADER_NAV_REGISTER = By.xpath("//div[@class='header-links']//a[@class='ico-register']");
    public static final By HEADER_NAV_LOGIN = By.xpath("//div[@class='header-links']//a[@class='ico-login']");
    public static final By HEADER_NAV_LOGOUT = By.xpath("//div[@class='header-links']//a[@class='ico-logout']");
    public static final By HEADER_NAV_WISHLIST = By.xpath("//div[@class='header-links']//a[@class='ico-wishlist']");
    public static final By HEADER_NAV_SHOPCART = By.xpath("//div[@class='header-links']//a[@class='ico-cart']");
    public static final By PAGE_TITLE = By.xpath("//div[@class='page-title']/h1");
}
