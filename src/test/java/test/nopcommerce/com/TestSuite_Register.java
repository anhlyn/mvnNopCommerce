package test.nopcommerce.com;

import main.nopcommerce.com.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pom.Common;
import pom.RegisterPage;

public class TestSuite_Register extends BaseTest {
    @Test
    public void TC01_EmptyData(){
        cm.goToRegister();
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getErrFirstName().contains("First name is required."));
        Assert.assertTrue(registerPage.getErrLastName().contains("Last name is required."));
        Assert.assertTrue(registerPage.getErrEmail().contains("Email is required."));
        Assert.assertTrue(registerPage.getErrPassConfirm().contains("Password is required."));
    }

    @Test
    public void TC02_InvalidEmail(){
        cm.goToRegister();
        String password = faker.internet().password(6,8);
        registerPage.fillForm(faker.name().firstName(), faker.name().lastName(), "test@", password, password);
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getErrEmail().contains("Please enter a valid email address."));
    }

    @Test(enabled = false)
    public void TC03_ValidAccount(){
        //preparing test data
        account.put("firstname", faker.name().firstName());
        account.put("lastname", faker.name().lastName());
        account.put("email", faker.internet().emailAddress());
        account.put("password", faker.internet().password(6,8));
        cm.goToRegister();
        registerPage.fillForm(account.get("firstname"), account.get("lastname"), account.get("email"), account.get("password"), account.get("password"));
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getResultMsg().contains("Your registration completed"));
        System.out.println("-- ACCOUNT -- ");
        System.out.println(account);
    }

    //flaky test here
    @Test
    @Parameters({"email", "password", "firstname", "lastname"})
    public void TC04_ExistedEmail(@Optional("hector.koelpin@gmail.com") String mail, @Optional("iqemtg8") String p, @Optional("Keisha") String fn, @Optional("Gottlieb") String ln){
        cm.goToRegister();
        registerPage.fillForm(fn, ln, mail, p, p);
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getErrSummary().contains("The specified email already exists"));
    }

    @Test
    public void TC05_PasswordLessThan6Chars(){
        cm.goToRegister();
        registerPage.fillForm(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "1234", "1234");
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getErrPass().contains("must have at least 6 characters"));
    }

    @Test
    public void TC06_PassAndPassConfirmNotMatch(){
        cm.goToRegister();
        registerPage.fillForm(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "123456", "654321");
        registerPage.clickRegister();
        Assert.assertTrue(registerPage.getErrPassConfirm().contains("The password and confirmation password do not match."));
    }
}