package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * HomePage class contains methods to interact with the booking.com homepage.
 * It includes methods to enter location, select dates, and perform a search.
 */
public class HomePage extends BasePage
{
    // Locators
    private By locationInput = By.name("ss");
    private By checkInButton = By.cssSelector("button[data-testid='date-display-field-start']");
    private By checkOutButton = By.cssSelector("button[data-testid='date-display-field-end']");
    private By searchButton = By.cssSelector("button[type='submit']");

    // Constructor
    public HomePage(WebDriver driver)
    {
        super(driver); // Call BasePage constructor
    }

    // Method to enter location
    public void enterLocation(String location)
    {
        enterText(locationInput, location); // Use enterText from BasePage
    }

    // Method to select dates
    public void selectDates(String checkIn, String checkOut)
    {
        clickElement(checkInButton); // Open the calendar
        navigateToMonth(checkIn); // Navigate to the correct month for check-in
        clickElement(By.xpath(String.format("//span[@data-date='%s']", checkIn))); // Select check-in date
        navigateToMonth(checkOut); // Navigate to the correct month for check-out
        clickElement(By.xpath(String.format("//span[@data-date='%s']", checkOut))); // Select check-out date
    }

    // Helper method to navigate to the correct month
    private void navigateToMonth(String date)
    {
        // Split the date into year and month
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        // Get the current month and year displayed in the calendar
        WebElement currentMonthElement = driver.findElement(By.xpath("(//h3[@aria-live='polite'])[1]"));
        String currentMonthText = currentMonthElement.getText();
        String[] currentMonthParts = currentMonthText.split(" ");
        String currentMonthName = currentMonthParts[0];
        int currentYear = Integer.parseInt(currentMonthParts[1]);

        // Calculate the difference in months
        int targetMonth = month;
        int targetYear = year;
        int monthsDifference = (targetYear - currentYear) * 12 + (targetMonth - getMonthNumber(currentMonthName));

        // Navigate to the target month
        By nextButton = By.xpath("//button[@aria-label=\"Next month\"]");
        for (int i = 0; i < monthsDifference; i++)
        {
            clickElement(nextButton);
        }
    }
    // Helper method to get the month number from the month name
    private int getMonthNumber(String monthName)
    {
        switch (monthName) {
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
            default: throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }

    // Method to click the search button
    public void clickSearch()
    {
        clickElement(searchButton); // Use clickElement from BasePage
    }
}