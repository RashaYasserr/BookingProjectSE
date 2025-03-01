package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ReservationPage class contains methods to interact with the reservation confirmation page.
 * It includes methods to verify the hotel name and check if the reservation page is loaded.
 */
public class ReservationPage extends BasePage
{
    // Locators
    private By hotelName = By.xpath("//h1[contains(@class, 'e1eebb6a1e')]");
    //private By hotelName = By.xpath("//*[@id="bodyconstraint-inner"]/div[3]/div[3]/aside/div/div[1]/div/div/div/div/div/div/div[1]/div[1]/h1");

    private By reservationDetails = By.xpath("//div[@data-testid=\"booking-details-date-summary\"]");

    // Constructor
    public ReservationPage(WebDriver driver)
    {
        super(driver); // Call BasePage constructor
    }

    // Method to get the hotel name from the reservation page
    public String getHotelName()
    {
        return getElementText(hotelName); // Use getElementText from BasePage
    }

    /**
     * Method to check if the reservation page is loaded.
     *return True if the reservation details are visible, otherwise false.
     */
    public boolean isReservationPageLoaded()
    {
        try {
            // Wait for the reservation details element to be visible
            waitForElementToBeVisible(reservationDetails);
            return true; // Return true if the element is found
        } catch (Exception e) {
            return false; // Return false if the element is not found
        }
    }
    // Method to verify reservation details (e.g., dates, price)
    public String getReservationDetails()
    {
        return getElementText(reservationDetails); // Use getElementText from BasePage
    }
}