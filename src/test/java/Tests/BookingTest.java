package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;

/**
 * BookingTest class contains test cases for the booking.com workflow.
 * It includes test cases for verifying the "See Availability" button, check-in/check-out dates, and hotel name.
 */
public class BookingTest extends BaseTest
{

    // DataProvider to fetch test data from Excel
    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() throws Exception
    {
        return ExcelReader.readExcel("search_test_data.xlsx", "Sheet1"); // Read data from Excel
    }

    /**
     * Test Case 1: Verify that the "See Availability" button works correctly.
     */
    @Test(dataProvider = "bookingData")
    public void testSeeAvailabilityButton(String location, String checkInDate, String checkOutDate)
    {
        // Step 1: Open booking.com
        homePage.openUrl("https://www.booking.com");

        // Step 2: Enter location and dates, then search
        homePage.enterLocation(location);
        homePage.selectDates(checkInDate, checkOutDate);
        homePage.clickSearch();

        // Step 3: Select Tolip Hotel and Click "See Availability"
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.selectTolipHotel();

        // Step 4: Assert that the hotel name exists in the page source
        String pageSource = driver.getPageSource(); // Get the page source
        String expectedHotelName = "Tolip Hotel Alexandria"; // Expected hotel name
        Assert.assertTrue(pageSource.contains(expectedHotelName), "Hotel name is not displayed on the page!");
    }

    /**
     * Test Case 2: Verify that the chosen check-in and check-out dates are displayed correctly.
     */
    @Test(dataProvider = "bookingData")
    public void testCheckInCheckOutDates(String location, String checkInDate, String checkOutDate)
    {
        // Step 1: Open booking.com
        homePage.openUrl("https://www.booking.com");

        // Step 2: Enter location and dates, then search
        homePage.enterLocation(location);
        homePage.selectDates(checkInDate, checkOutDate);
        homePage.clickSearch();

        // Step 3: Select Tolip Hotel
        searchResultsPage.selectTolipHotel();
        // Step 4: Verify check-in and check-out dates
        String[] displayedDates = hotelDetailsPage.getCheckInOutDates();

        // Check if dates were retrieved successfully
        if (displayedDates == null || displayedDates.length < 2)
        {
            Assert.fail("Failed to retrieve check-in and check-out dates from the page.");
        }

        String displayedCheckInDate = displayedDates[0];
        String displayedCheckOutDate = displayedDates[1];

        // Assert dates
        Assert.assertEquals(displayedCheckInDate, checkInDate,
                "Check-in date is not displayed correctly! Expected: " + checkInDate + ", but found: " + displayedCheckInDate);

        Assert.assertEquals(displayedCheckOutDate, checkOutDate,
                "Check-out date is not displayed correctly! Expected: " + checkOutDate + ", but found: " + displayedCheckOutDate);
    }

    /**
     * Test Case 3: Verify that the "I'll Reserve" button works correctly.
     */
    @Test(dataProvider = "bookingData")
    public void testReserveButtonFunctionality(String location, String checkInDate, String checkOutDate)
    {
        // Step 1: Open booking.com
        homePage.openUrl("https://www.booking.com");

        // Step 2: Enter location and dates, then search
        homePage.enterLocation(location);
        homePage.selectDates(checkInDate, checkOutDate);
        homePage.clickSearch();

        // Step 3: Select Tolip Hotel and Click "See Availability"
        searchResultsPage.selectTolipHotel();

        // Step 4: Click "Reserve" button
        hotelDetailsPage.clickReserveButton();

        // Step 6: Select bed type and number of rooms
        hotelDetailsPage.selectBedType();
        hotelDetailsPage.selectNumberOfRooms(1); // Select 1 room

        // Step 7: Click "I'll Reserve" button
        hotelDetailsPage.clickIllReserveButton();

        // Step 8: Verify that the reservation page is loaded
        ReservationPage reservationPage = new ReservationPage(driver);
        Assert.assertTrue(reservationPage.isReservationPageLoaded(), "Reservation page is not loaded");
    }

    /**
     * Test Case 4: Verify that the hotel name is displayed correctly on the reservation page.
     */
    @Test(dataProvider = "bookingData")
    public void testHotelNameInReservationPage(String location, String checkInDate, String checkOutDate)
    {
        // Step 1: Open booking.com
        homePage.openUrl("https://www.booking.com");

        // Step 2: Enter location and dates, then search
        homePage.enterLocation(location);
        homePage.selectDates(checkInDate, checkOutDate);
        homePage.clickSearch();

        // Step 3: Select Tolip Hotel and Click "See Availability"
        String hotelName = searchResultsPage.selectTolipHotel();

        // Step 4: Click "Reserve" button
        hotelDetailsPage.clickReserveButton();

        // Step 6: Select bed type and number of rooms
        hotelDetailsPage.selectBedType();
        hotelDetailsPage.selectNumberOfRooms(1); // Select 1 room

        // Step 7: Click "I'll Reserve" button
        hotelDetailsPage.clickIllReserveButton();

        // Step 8: Verify hotel name on the reservation page
        ReservationPage reservationPage = new ReservationPage(driver);
        Assert.assertEquals(reservationPage.getHotelName(), hotelName, "Hotel name is incorrect");
    }


}