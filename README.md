# mvnNopCommerce

This project has Selenium + TestNG tests for a nopCommerce website (Login and Register pages).

## Why we use a local website

The public demo site (`demo.nopcommerce.com`) now blocks automated browsers with a
"Just a moment..." Cloudflare check. A robot (like Selenium) cannot pass this check.

So instead, we run our own copy of nopCommerce on our computer using Docker. The
tests point to this local copy at `http://localhost:8080/`.

## What you need installed

- Docker Desktop (must be running)
- Java 11 or newer
- Maven

## Step 1: Start the local website

From the project folder, run:

```
docker compose up -d
```

This starts two containers:
- `nopcommerce_local` — the nopCommerce website (port 8080)
- `nopcommerce_mysql_local` — the MySQL database it uses (port 3307)

Wait about 20-30 seconds for both to start.

## Step 2: First-time only — install the website

The very first time you start these containers, the website is empty and needs
a one-time setup (like installing WordPress). This is called the "install wizard."

You can do this two ways:

**Easy way (open in browser):**
1. Open `http://localhost:8080/install` in your browser.
2. Fill in:
   - Admin email: any email, e.g. `admin@nopcommerce.local`
   - Admin password: any password, e.g. `Admin123!`
   - Database: choose **MySQL**
   - Server name: `nopcommerce_database`
   - Database name: `nopcommerce`
   - Username: `root`
   - Password: `nopCommerce_db_password`
3. Click **Install** and wait. The page will restart itself when done.

After install, go to `http://localhost:8080/register` and create the test account
used by the automated tests (see `testsuite_nopcommerce.xml` for the email/password
values it expects — `hector.koelpin@gmail.com` / `iqemtg8` / Keisha Gottlieb by default).

You only need to do this once. As long as you don't delete the Docker volumes
(`docker compose down -v`), your data stays.

## Step 3: Run the tests

```
mvn test
```

This runs all the tests listed in `testsuite_nopcommerce.xml` against
`http://localhost:8080/`.

## Step 4: Stop the local website (optional)

When you're done testing:

```
docker compose down
```

This stops the containers but keeps your data. If you want to wipe everything
and start fresh next time (you'll need to redo the install wizard):

```
docker compose down -v
```

## Where things are configured

- `docker-compose.yml` — defines the local website and database containers.
- `src/main/java/main/nopcommerce/com/Config.java` — the URL the tests point to.
- `testsuite_nopcommerce.xml` — test data (email, password, etc.) used by the tests.
