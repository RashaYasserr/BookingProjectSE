package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * BaseTest class contains setup and teardown methods for all test cases.
 * It initializes the WebDriver and page objects.
 */
public class BaseTest
{
    protected WebDriver driver; // WebDriver instance to be shared across test classes
    protected HomePage homePage;
    protected SearchResultsPage searchResultsPage;
    protected HotelDetailsPage hotelDetailsPage;
    protected ReservationPage reservationPage;

    /**
     * Setup method to initialize WebDriver and page objects before each test method.
     */
    @BeforeMethod
    public void setUp()
    {
        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Configure WebDriver settings
        driver.manage().window().maximize(); // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

        // Initialize page objects
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        hotelDetailsPage = new HotelDetailsPage(driver);
        reservationPage = new ReservationPage(driver);
    }

    /**
     * Teardown method to close the browser after each test method.
     */
    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit(); // Close the browser
        }
    }
}