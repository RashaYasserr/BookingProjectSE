package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * HotelDetailsPage class contains methods to interact with the hotel details page.
 * It includes methods to select bed type, number of rooms, and click "I'll Reserve".
 */
public class HotelDetailsPage extends BasePage
{
    // Locators

    private By checkInDateElement = By.cssSelector("button[data-testid='date-display-field-start']");
    private By checkOutDateElement = By.cssSelector("button[data-testid='date-display-field-end']");
    private By reserveButton = By.xpath("//button[@id='hp_book_now_button']");

    private By bedTypeRadioButton = By.xpath("(//input[@type=\"radio\"])[1]"); // Bed type selection
    private By roomDropdown = By.xpath("(//select[@data-testid=\"select-room-trigger\"])[1]"); // Room selection dropdown
    private By illReserveButton = By.cssSelector("button[id^='b_tt_holder_2']"); // "I'll Reserve" button (final confirmation)

    // Constructor
    public HotelDetailsPage(WebDriver driver)
    {
        super(driver); // Call BasePage constructor
    }


    // Method to click "Reserve" button (leads to bed/room selection)
    public void clickReserveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increase timeout to 20 seconds
        WebElement reserveButtonElement = wait.until(ExpectedConditions.elementToBeClickable(reserveButton));

        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", reserveButtonElement);

        // Wait a bit after scrolling (optional but useful)
        try {
            Thread.sleep(500); // 0.5 second delay to let UI stabilize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Ensure the element is enabled before clicking
            if (reserveButtonElement.isEnabled()) {
                // Try clicking with Actions (alternative to standard click)
                Actions actions = new Actions(driver);
                actions.moveToElement(reserveButtonElement).click().perform();
            } else {
                throw new ElementNotInteractableException("Element is not interactable.");
            }
        } catch (Exception e) {
            // If normal click fails, use JavaScript click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reserveButtonElement);
        }
    }



    // Method to select bed type
    public void selectBedType()
    {
        waitForPageToLoad();
        clickElement(bedTypeRadioButton); // Use clickElement from BasePage
    }

    public void selectNumberOfRooms(int numberOfRooms)
    {
            // Wait for the page to load completely
            waitForPageToLoad();
            System.out.println("Waiting for room dropdown to be visible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait for up to 20 seconds
            WebElement roomDropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(roomDropdown));

            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roomDropdownElement);

            Select roomSelect = new Select(roomDropdownElement);
            roomSelect.selectByVisibleText(String.valueOf(numberOfRooms)); // Select the number of rooms

    }

    /**
     * Method to wait for the page to load completely.
     */
    public void waitForPageToLoad()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait for up to 20 seconds
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    // Method to click "I'll Reserve" button (final confirmation)
    public void clickIllReserveButton()
    {
        clickElement(illReserveButton); // Use clickElement from BasePage
    }

    // Method to get check-in and check-out dates
    public String[] getCheckInOutDates()
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDateElement));

            // Get check-in and check-out dates from the page
            String checkInDate = driver.findElement(checkInDateElement).getText(); // Example: "Wed, Oct 1"
            String checkOutDate = driver.findElement(checkOutDateElement).getText();

            // Add year manually if not present
            String checkInDateWithYear = checkInDate + ", 2025"; // Add year
            String checkOutDateWithYear = checkOutDate + ", 2025";

            // Convert dates to the same format as Excel (yyyy-MM-dd)
            SimpleDateFormat websiteFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
            SimpleDateFormat excelFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Parse and format check-in date
            Date parsedCheckInDate = websiteFormat.parse(checkInDateWithYear);
            String formattedCheckInDate = excelFormat.format(parsedCheckInDate); // Becomes: "2025-10-01"

            // Parse and format check-out date
            Date parsedCheckOutDate = websiteFormat.parse(checkOutDateWithYear);
            String formattedCheckOutDate = excelFormat.format(parsedCheckOutDate);

            // Return formatted dates
            return new String[]{formattedCheckInDate, formattedCheckOutDate};

        } catch (Exception e)
        {
            System.out.println("Failed to get check-in/check-out dates: " + e.getMessage());
            return new String[]{"", ""}; // Return empty array if an error occurs
        }
    }
}