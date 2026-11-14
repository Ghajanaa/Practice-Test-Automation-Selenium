package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By usernameField =
            By.id("username");

    private By passwordField =
            By.id("password");

    private By submitButton =
            By.id("submit");

    private By errorMessage =
            By.id("error");

    private By logoutButton =
            By.linkText("Log out");


    // Constructor
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Enter username
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
    }


    //Enter Password
    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .sendKeys(password);
    }


    //click login button
    public void clickSubmit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton))
                .click();
    }


    // Get error message
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                .getText();
    }

    // Check logout button
    public boolean isLogoutButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton))
                .isDisplayed();
    }

    // Login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

}
