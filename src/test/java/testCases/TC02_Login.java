package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import PageObjects.AccountPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC02_Login extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC02_Login.class);

    @Test(
        groups = {"sanity", "regression", "datadriven"},
        dataProvider = "LoginData",
        dataProviderClass = DataProviders.class,
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testLogin(String email, String pwd) {

        log.info("==== TC02_Login started ====");
        log.debug("Test data — Email: {} | Password: {}", email, pwd);

        try {
            HomePage hp = new HomePage(getDriver());
            log.debug("Navigating to Login page via My Account menu");
            hp.clickMyAccount();
            hp.goToLogin();

            LoginPage lp = new LoginPage(getDriver());
            log.debug("Entering login credentials");
            lp.setEmail(email);
            lp.setPwd(pwd);
            lp.clickLogin();

            AccountPage ap = new AccountPage(getDriver());
            log.debug("Checking My Account page visibility");
            boolean status = ap.getMyAccountConfirmation().isDisplayed();

            // Assertions wrapped with try-catch
            try {
                if (status) {
                    log.info("Login successful — performing logout steps");

                    ap.clickMyAccountDropDown();
                    ap.clickLogout();

                    Assert.assertTrue(status);
                    log.info("Assertion passed — login verified");
                } else {
                    log.error("Login failed — My Account page not displayed");
                    Assert.assertTrue(false);
                }
            }
            catch (AssertionError ae) {
                log.error("Assertion FAILED in TC02_Login", ae);
                throw ae;
            }

        } catch (Exception e) {
            log.error("Unexpected exception in TC02_Login test", e);
            throw e;
        }

        log.info("==== TC02_Login finished ====");
    }
}
