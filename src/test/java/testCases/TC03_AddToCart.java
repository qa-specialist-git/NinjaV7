package testCases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import testBase.BaseClass;

public class TC03_AddToCart extends BaseClass {

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testAddToCart() throws InterruptedException {

        log.info("===== TC03_AddToCart Test Started =====");

        try {
            log.debug("Initializing CategoryPage...");
            CategoryPage cp = new CategoryPage(getDriver());

            log.debug("Clicking Laptops & Notebooks...");
            cp.clickLaptopsAndNotebooks();

            log.debug("Clicking Show All...");
            cp.clickShowAll();

            Thread.sleep(500);

            log.debug("Selecting HP Product...");
            cp.selectHPProduct();

            log.debug("Initializing ProductPage...");
            ProductPage pp = new ProductPage(getDriver());

            log.debug("Setting delivery date...");
            pp.setDeliveryDate();

            log.debug("Clicking Add to Cart...");
            pp.clickAddToCart();

            log.debug("Validating success message...");
            try {
                Assert.assertTrue(pp.isSuccessMessageDisplayed(), "Add to Cart Failed!");
                log.info("Assertion Passed: Product successfully added to cart");
            }
            catch (AssertionError ae) {
                log.error("Assertion Failed: " + ae.getMessage());
                throw ae;   // rethrow so RetryAnalyzer can catch it
            }

        } catch (Exception e) {
            log.error("Unexpected error occurred in testAddToCart()", e);
            throw e;
        }

        log.info("===== TC03_AddToCart Test Finished =====");
    }
}
