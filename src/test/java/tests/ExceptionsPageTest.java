
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.ExceptionsPage;

import java.time.Duration;

public class ExceptionsPageTest {

    WebDriver driver;
    WebDriverWait wait;
    ExceptionsPage exceptionsPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

        exceptionsPage = new ExceptionsPage(driver, wait);
    }


    @Test
    public void verifyRow2AppearsTest() {

        exceptionsPage.clickAddButton();

        Assert.assertTrue(
                exceptionsPage.isRow2Displayed(),
                "Row 2 was not displayed"
        );
    }


    @Test
    public void addAndSaveRow2Test() {

        //Click Add button
        exceptionsPage.clickAddButton();

        // Step 3: Wait for second row to load
        Assert.assertTrue(
                exceptionsPage.isRow2Displayed(),
                "Row 2 was not displayed"
        );

        // Step 4: Enter text into second input field
        String expectedText = "Selenium Practice";
        exceptionsPage.enterRow2Text(expectedText);

        // Step 5: Click Save button
        exceptionsPage.clickSaveButton();

        // Step 6: Verify saved text
        String actualText = exceptionsPage.getRow2Text();

        Assert.assertEquals(
                actualText,
                expectedText,
                "Saved text does not match"
        );
    }


    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
