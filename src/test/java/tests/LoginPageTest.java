package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;

import java.time.Duration;

public class LoginPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    // Runs before each test
    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://practicetestautomation.com/practice-test-login/");

        loginPage = new LoginPage(driver, wait);
    }

    // Test 1: Valid Login
    @Test
    public void validLoginTest() {

        // Enter valid credentials
        loginPage.login("student", "Password123");


        // Verify successful login URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/logged-in-successfully/"),
                "User was not redirected to the successful login page"
        );

        // Verify logout button is displayed
        Assert.assertTrue(
                loginPage.isLogoutButtonDisplayed(),
                "Logout button is not displayed"
        );
    }


    @Test
    public void invalidUsernameTest() {

        // Enter valid credentials
        loginPage.login("incorrectUser", "Password123");

        // Get the actual error message
        String actualErrorMessage = loginPage.getErrorMessage();

        // Verify the error message
        Assert.assertEquals(
                actualErrorMessage,
                "Your username is invalid!",
                "Incorrect error message for invalid username"
        );
    }

    @Test
    public void invalidPasswordTest() {

        // Enter valid credentials
        loginPage.login("student", "incorrectPassword ");

        // Get the actual error message
        String actualErrorMessage = loginPage.getErrorMessage();

        // Verify the error message
        Assert.assertEquals(
                actualErrorMessage,
                "Your password is invalid!",
                "Incorrect error message for invalid password"
        );
    }

    // Runs after each test
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
