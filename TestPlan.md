# Test Plan — nopCommerce

- **Application under test:** nopCommerce (local instance, see `README.md`)
- **Test tool:** Selenium 4 + TestNG + Maven
- **Test data:** email, password, first name, last name come from `testsuite_nopcommerce.xml`

Automated Status legend:
- **Automated** — runs every time `mvn test` is executed
- **Automated (Disabled)** — code exists but is turned off (`@Test(enabled = false)`), does not run
- **Not Automated** — found during exploratory testing, documented here as a candidate for future automation

Priority legend:
- **High** — core user flow or money/legal risk; a bug here blocks or costs real customers
- **Medium** — important, but has a workaround or affects a smaller group of users
- **Low** — nice-to-have area; a bug here is annoying but not blocking

---

## 1. Login

### TC01 — Login with empty data
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Leave email and password blank.
  3. Click the **Log in** button.
- **Expected result:** An error message appears under the email field: "Please enter your email".

### TC02 — Login with invalid email format
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Type `test` into the email field (not a real email format).
  3. Click **Log in**.
- **Expected result:** An error message appears: "Please enter a valid email address."

### TC03 — Login with an email that is not registered
- **Priority:** High
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Type a random, made-up email address.
  3. Type a random password.
  4. Click **Log in**.
- **Expected result:** A summary error box appears saying "Login was unsuccessful. Please correct the errors and try again." and mentioning "No customer account found".

### TC04 — Login with a real email but no password
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Type a known, existing account's email (from test data).
  3. Leave password blank.
  4. Click **Log in**.
- **Expected result:** The login is rejected (no assertion is checked in code today — this test only performs the action).

### TC05 — Login with a real email but wrong password
- **Priority:** High
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Type a known, existing account's email (from test data).
  3. Type a random, wrong password.
  4. Click **Log in**.
- **Expected result:** A summary error box appears saying "Login was unsuccessful. Please correct the errors and try again." and mentioning "The credentials provided are incorrect".

### TC06 — Login successfully with correct email and password
- **Priority:** High
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Login page.
  2. Type a known, existing account's email and correct password (from test data).
  3. Click **Log in**.
- **Expected result:** The **Log out** link appears in the header, showing the user is logged in.

### (Extra) RegisterNewAcc — Register a brand-new random account
- **Priority:** Low
- **Automated Status:** Automated (Disabled)
- **Steps:**
  1. Go to the Register page.
  2. Fill in a randomly generated first name, last name, email, and password.
  3. Click **Register**.
- **Expected result:** The page shows "Your registration completed".
- **Note:** This is not really a Login test — it lives in the Login test file, used to create a new test account when needed.

---

## 2. Register

### TC01 — Register with all fields empty
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Register page.
  2. Leave every field blank.
  3. Click **Register**.
- **Expected result:** Each field shows a "required" error:
  - First name: "First name is required."
  - Last name: "Last name is required."
  - Email: "Email is required."
  - Password: "Password is required."
  - Confirm password: "Password is required."
- **Known issue:** In the current app version, the Password field's error does not appear. This is a real bug in the app (the Password field is missing its "required" check), not a problem with the test.

### TC02 — Register with invalid email format
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Register page.
  2. Fill in a random first name, last name, and password.
  3. Type `test@` as the email (missing the domain part).
  4. Click **Register**.
- **Expected result:** An error message appears: "Please enter a valid email address."

### TC03 — Register a new account with valid data
- **Priority:** High
- **Automated Status:** Automated (Disabled)
- **Steps:**
  1. Go to the Register page.
  2. Fill in a randomly generated first name, last name, email, and password.
  3. Click **Register**.
- **Expected result:** The page shows "Your registration completed".

### TC04 — Register with an email that already exists
- **Priority:** High
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Register page.
  2. Fill in the form using an email that is already registered (from test data), plus its matching first name, last name, and password.
  3. Click **Register**.
- **Expected result:** An error message appears: "The specified email already exists".

### TC05 — Register with a password shorter than 6 characters
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Register page.
  2. Fill in a random first name, last name, and email.
  3. Type `1234` as both password and confirm password.
  4. Click **Register**.
- **Expected result:** An error message appears mentioning "must have at least 6 characters".

### TC06 — Register with password and confirm-password not matching
- **Priority:** Medium
- **Automated Status:** Automated
- **Steps:**
  1. Go to the Register page.
  2. Fill in a random first name, last name, and email.
  3. Type `123456` as the password and `123400` as the confirm password (different values).
  4. Click **Register**.
- **Expected result:** An error message appears: "The password and confirmation password do not match."

---

## 3. Shopping Cart

Found through exploratory testing. Not automated yet.

### TC01 — Add a simple product to the cart
- **Priority:** High
- **Automated Status:** Not Automated
- **Steps:**
  1. Open a normal product page (example: Apple MacBook Pro).
  2. Click **Add to cart**.
- **Expected result:** A success message pops up: "The product has been added to your shopping cart". The cart icon in the header shows 1 item.

### TC02 — Add a configurable product to the cart without picking a required option
- **Priority:** High
- **Automated Status:** Not Automated
- **Steps:**
  1. Open a product that has required choices (example: "Build your own computer", which requires picking RAM).
  2. Do **not** select any RAM option.
  3. Click **Add to cart**.
- **Expected result:** The product is **not** added. A message appears telling the customer what is missing: "Please select RAM".

### TC03 — Apply an invalid discount coupon code in the cart
- **Priority:** Medium
- **Automated Status:** Not Automated
- **Steps:**
  1. Add any product to the cart.
  2. Go to the Shopping cart page.
  3. Type a made-up code (example: `INVALIDCODE123`) into the "Discount Code" box.
  4. Click **Apply coupon**.
- **Expected result:** An error message appears: "The coupon code cannot be found". The order total does not change.

---

## 4. Checkout

Found through exploratory testing. Not automated yet.

### TC01 — Try to check out without accepting the Terms of Service
- **Priority:** High
- **Automated Status:** Not Automated
- **Steps:**
  1. Add a product to the cart.
  2. Go to the Shopping cart page.
  3. Do **not** tick the "I agree with the terms of service" checkbox.
  4. Click **Checkout**.
- **Expected result:** Checkout is blocked. A pop-up warning box appears asking the customer to accept the terms first.

### TC02 — Guest user goes to checkout
- **Priority:** High
- **Automated Status:** Not Automated
- **Steps:**
  1. Add a product to the cart while not logged in.
  2. Tick the "I agree with the terms of service" checkbox.
  3. Click **Checkout**.
- **Expected result:** The customer is taken to the Login page, which also offers a **Checkout as guest** button, so they don't have to create an account to buy.

---

## 5. Wishlist

Found through exploratory testing. Not automated yet.

### TC01 — Add a product to the Wishlist
- **Priority:** Medium
- **Automated Status:** Not Automated
- **Steps:**
  1. Open any product page.
  2. Click **Add to wishlist**.
  3. Open the Wishlist page.
- **Expected result:** The product appears in the Wishlist, and the Wishlist counter in the header updates.

---

## 6. Compare Products

Found through exploratory testing. Not automated yet.

### TC01 — Add a product to the Compare list
- **Priority:** Low
- **Automated Status:** Not Automated
- **Steps:**
  1. Open any product page.
  2. Click **Add to compare**.
  3. Open the "Compare products list" page.
- **Expected result:** The product appears on the Compare page so it can be compared with other products.

---

## 7. Password Recovery

Found through exploratory testing. Not automated yet.

### TC01 — Password recovery with a registered email
- **Priority:** High
- **Automated Status:** Not Automated
- **Steps:**
  1. Go to the "Password recovery" page.
  2. Type in an email that already has an account (from test data).
  3. Click **Recover**.
- **Expected result:** A confirmation message appears: "Email with instructions has been sent to you."

### TC02 — Password recovery with an email that is not registered
- **Priority:** Medium
- **Automated Status:** Not Automated
- **Steps:**
  1. Go to the "Password recovery" page.
  2. Type in a made-up email that has no account.
  3. Click **Recover**.
- **Expected result:** An error message appears: "Email not found."

---

## 8. Contact Us

Found through exploratory testing. Not automated yet.

### TC01 — Submit the Contact Us form with everything empty
- **Priority:** Low
- **Automated Status:** Not Automated
- **Steps:**
  1. Go to the "Contact us" page.
  2. Leave Name, Email, and Enquiry blank.
  3. Click **Submit**.
- **Expected result:** Three "required" errors appear: "Enter your name", "Enter email", "Enter enquiry".

---

## 9. Newsletter

Found through exploratory testing. Not automated yet.

### TC01 — Subscribe to the newsletter from the footer
- **Priority:** Low
- **Automated Status:** Not Automated
- **Steps:**
  1. Scroll to the footer on any page.
  2. Type a valid email address into the "Newsletter" box.
  3. Click **Subscribe**.
- **Expected result:** A confirmation message appears: "Thank you for signing up! A verification email has been sent. We appreciate your interest."

---

## 10. Search

Found through exploratory testing. Not automated yet.

### TC01 — Search for something that does not exist
- **Priority:** Medium
- **Automated Status:** Not Automated
- **Steps:**
  1. Go to the Search page.
  2. Type in a made-up word that no product matches (example: `zzzznoresultzzzz`).
  3. Submit the search.
- **Expected result:** The page shows: "No products were found that matched your criteria." (No products are listed.)

---

## Summary Table

| # | Category | TC ID | Test Case Description | Priority | Automated Status |
|---|---|---|---|---|---|
| 1 | Login | TC01 | Login with empty data | Medium | Automated |
| 2 | Login | TC02 | Login with invalid email format | Medium | Automated |
| 3 | Login | TC03 | Login with unregistered email | High | Automated |
| 4 | Login | TC04 | Login with existing email, empty password | Medium | Automated |
| 5 | Login | TC05 | Login with existing email, wrong password | High | Automated |
| 6 | Login | TC06 | Login success | High | Automated |
| 7 | Login | RegisterNewAcc | Register random new account (helper, lives in Login suite) | Low | Automated (Disabled) |
| 8 | Register | TC01 | Register with empty data | Medium | Automated |
| 9 | Register | TC02 | Register with invalid email format | Medium | Automated |
| 10 | Register | TC03 | Register with valid data | High | Automated (Disabled) |
| 11 | Register | TC04 | Register with existing email | High | Automated |
| 12 | Register | TC05 | Register with password < 6 characters | Medium | Automated |
| 13 | Register | TC06 | Register with password/confirm mismatch | Medium | Automated |
| 14 | Shopping Cart | TC01 | Add simple product to cart | High | Not Automated |
| 15 | Shopping Cart | TC02 | Add configurable product without required option | High | Not Automated |
| 16 | Shopping Cart | TC03 | Apply invalid discount coupon | Medium | Not Automated |
| 17 | Checkout | TC01 | Checkout blocked without accepting Terms of Service | High | Not Automated |
| 18 | Checkout | TC02 | Guest user checkout flow | High | Not Automated |
| 19 | Wishlist | TC01 | Add product to Wishlist | Medium | Not Automated |
| 20 | Compare Products | TC01 | Add product to Compare list | Low | Not Automated |
| 21 | Password Recovery | TC01 | Password recovery, registered email | High | Not Automated |
| 22 | Password Recovery | TC02 | Password recovery, unregistered email | Medium | Not Automated |
| 23 | Contact Us | TC01 | Contact Us form, empty submit | Low | Not Automated |
| 24 | Newsletter | TC01 | Newsletter subscribe, valid email | Low | Not Automated |
| 25 | Search | TC01 | Search with no matching results | Medium | Not Automated |
