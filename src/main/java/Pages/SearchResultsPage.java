package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage
{
    // Locators
    private By hotelCards = By.cssSelector("div[data-testid='property-card']"); // All hotel cards on the page
    private By hotelName = By.cssSelector("div[data-testid='title']");
    private By seeAvailabilityButton = By.cssSelector("a[data-testid='availability-cta-btn']");

    // Constructor
    public SearchResultsPage(WebDriver driver)
    {
        super(driver); // Call BasePage constructor
    }
    // Method to click "See Availability" button
    public void clickSeeAvailability()
    {
        clickElement(seeAvailabilityButton);
    }
    // Method to select Tolip Hotel and return its name
    public String selectTolipHotel()
    {
        boolean found = false;
        String hotelNameText = ""; // Variable to store the hotel name

        while (!found)
        {
            // Get all hotel cards
            List<WebElement> hotels = driver.findElements(hotelCards);

            // Loop through each hotel card
            for (WebElement hotel : hotels)
            {
                // Get the hotel name
                hotelNameText = hotel.findElement(hotelName).getText();

                // Check if the hotel is Tolip Hotel Alexandria
                if (hotelNameText.contains("Tolip Hotel Alexandria"))
                {
                    // Scroll to the hotel card
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotel);

                    // Click on See Availability button
                    WebElement availabilityButton = hotel.findElement(seeAvailabilityButton);
                    availabilityButton.click();
                    found = true;
                    break;
                }
            }

            // If the hotel is not found, scroll down and load more results
            if (!found)
            {
                // Scroll down to load more results
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

                // Wait for new results to load
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(hotelCards, hotels.size()));
            }
        }

        return hotelNameText; // Return the hotel name
    }
}
