
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExceptionsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addButton = By.id("add_btn");
    private By row2 = By.id("row2");
    private By row2Input = By.cssSelector("#row2 input.input-field");
    private By saveButton = By.name("Save");
    private By editButton = By.id("edit");
    private By inputField = By.id("input");

    // Constructor
    public ExceptionsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Click Add button
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton))
                .click();
    }

    // Verify Row 2 is displayed
    public boolean isRow2Displayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(row2)
        ).isDisplayed();
    }


    // Enter text into Row 2
    public void enterRow2Text(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(row2Input))
                .sendKeys(text);
    }

    // Click Save button
    public void clickSaveButton() {
        WebElement visibleSaveButton = wait.until(driver ->
                driver.findElements(saveButton)
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .filter(WebElement::isEnabled)
                        .findFirst()
                        .orElse(null)
        );

        visibleSaveButton.click();
    }


    // Get saved text
    public String getRow2Text() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(row2Input))
                .getAttribute("value");
    }

}
