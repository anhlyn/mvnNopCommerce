# CLAUDE.md

## Project overview

UI test automation for a **local nopCommerce** storefront (Docker, `http://localhost:8080/`; see `README.md` for setup).
Stack: Java 11, Maven, Selenium 4.27 (`ChromeDriver`), TestNG 7.10, JavaFaker.

Run: `docker compose up -d` (first time: install wizard + create the test account, see README), then `mvn test`.
Surefire runs **only** `testsuite_nopcommerce.xml`, so a new test class must be registered there.

## Layout

```
src/main/java/main/nopcommerce/com/BaseTest.java   # driver lifecycle, page-object instances, Faker
src/main/java/main/nopcommerce/com/Config.java     # base URL
src/main/java/pom/BasePage.java                    # PageFactory init, WebDriverWait(5s), customFill/customClick
src/main/java/pom/Common.java                      # header links (login/register/logout), page title
src/main/java/pom/LoginPage.java, RegisterPage.java
src/test/java/test/nopcommerce/com/TestSuite_Login.java, TestSuite_Register.java
testsuite_nopcommerce.xml                          # suite + shared test data (parameters)
TestPlan.md                                        # source of truth for TC descriptions (its automation status is maintained there only, never mirrored in this file)
```

## Conventions

- Page objects: locators are package-private `@FindBy` fields; expose behavior as methods (`fillX`, `clickX`, `getErrX`, `isXDisplayed`). Tests never touch `WebElement`s.
- Assertions live in tests, not page objects (page objects return text/booleans).
- Test data that is stable (existing account) comes from suite XML `<parameter>`s via `@Parameters`; add `@Optional("...")` defaults so tests can also run from the IDE. Random data comes from `faker`.
- Fresh browser per test method; tests must be independent.

## How to define a new test case

1. **Document first** in `TestPlan.md`: add `### TCnn — <title>` under the right feature section with **Priority**, **Automated Status**, **Steps**, **Expected result**; update the Summary Table at the bottom.
2. **Page object**: if the page/element doesn't exist, add locators + methods to the matching class in `src/main/java/pom/` (or create `XxxPage extends BasePage` with a `super(d)` constructor). For a new page, also add a `protected XxxPage xxxPage;` field and instantiate it in `BaseTest.loadHomePage()`.
3. **Test method** in `src/test/java/test/nopcommerce/com/TestSuite_<Feature>.java` (extends `BaseTest`), named `TCnn_ShortDescription`, matching the TestPlan numbering:
   ```java
   @Test
   @Parameters({"email"})
   public void TC07_Example(@Optional("hector.koelpin@gmail.com") String mail){
       cm.goToLogin();
       loginPage.fillEmail(mail);
       loginPage.clickLogin();
       Assert.assertTrue(loginPage.getSummaryError().contains("Login was unsuccessful."));
   }
   ```
4. **New feature class?** Create `TestSuite_<Feature>` and add `<class name="test.nopcommerce.com.TestSuite_<Feature>" />` to `testsuite_nopcommerce.xml`; add any new shared data as `<parameter>` there.
5. Run `mvn test` (needs Docker site up).
6. **Always update the automation status back into `TestPlan.md`** every time you implement a TC from it (both the TC's section and the Summary Table), once the test runs successfully. This is a `TestPlan.md` edit only; don't mirror the status in this file.
## Known issues / notes

- Test account credentials are hardcoded in the suite XML and as `@Optional` defaults; the account must exist in the local site.
