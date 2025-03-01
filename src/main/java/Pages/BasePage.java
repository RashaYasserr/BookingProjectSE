package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage class contains common methods and utilities for all pages.
 * It initializes WebDriver and WebDriverWait for explicit waits.
 */
public class BasePage {
    protected WebDriver driver; // Shared WebDriver for all pages
    protected WebDriverWait wait; // WebDriverWait for explicit waits

    // Constructor to initialize WebDriver and WebDriverWait
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to open a URL
    public void openUrl(String url)
    {
        driver.get(url);
    }

    // Method to get the current page title
    public String getPageTitle()
    {
        return driver.getTitle();
    }

    // Method to wait until the page is fully loaded
    public void waitForPageToLoad()
    {
        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    // Method to wait until an element is visible
    public void waitForElementToBeVisible(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method to wait until an element is clickable
    public void waitForElementToBeClickable(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Method to click on an element
    public void clickElement(By locator)
    {
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    // Method to enter text into an input field
    public void enterText(By locator, String text)
    {
        waitForElementToBeVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Method to get the text of an element
    public String getElementText(By locator)
    {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("Failed to get element text: " + e.getMessage());
            return ""; // Return an empty string in case of an exception
        }
    }
}