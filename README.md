# Hotel Reservation Automation Project

## Overview

This project is an automated test suite designed to validate the functionality of the hotel reservation process on the **Booking.com** website. It ensures that the correct hotel name (**Tolip Hotel Alexandria**) is displayed on the reservation page after selecting the hotel from the search results.

The project is built using:

- **Selenium WebDriver** for browser automation.
- **TestNG** for test management and assertions.
- **Apache POI** for reading test data from Excel files.
- **Maven** for dependency management.
- **Page Object Model (POM)** design pattern for better code structure and maintainability.
- **Data Provider** in TestNG to read test data dynamically from an Excel file.

## Features

- **Search for Hotels:** Automates the process of searching for hotels based on location, check-in, and check-out dates.
- **Select a Specific Hotel:** Searches for and selects **Tolip Hotel Alexandria** from the search results.
- **Verify Hotel Name on Reservation Page:** Asserts that the hotel name is correctly displayed on the reservation page.
- **Data-Driven Testing:** Uses an Excel sheet to provide test data (e.g., location, check-in date, check-out date) through **TestNG Data Provider**.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java Development Kit (JDK)** (Version 8 or higher)
- **Maven** for dependency management
- **WebDriver** (e.g., ChromeDriver) compatible with your browser version
- **IDE** (IntelliJ IDEA, Eclipse, or any Java-supported IDE)
- **Excel File** (testdata.xlsx) with the required test data

## Setup

### Clone the Repository:

```sh
git clone https://github.com/your-username/hotel-reservation-automation.git
cd hotel-reservation-automation
```

### Install Dependencies:

```sh
mvn clean install
```

### Configure WebDriver:

- Download the appropriate WebDriver (e.g., ChromeDriver).
- Add its path to system environment variables or specify it in the code.

### Prepare Test Data:

Create an Excel file (testdata.xlsx) in the `src/test/resources` folder with the following structure:

| Location   | Check-In Date | Check-Out Date |
| ---------- | ------------- | -------------- |
| Alexandria | 2025-10-01    | 2025-10-14     |

## Running the Tests

### Run All Tests:

```sh
mvn test
```

### Run a Specific Test:

```sh
mvn -Dtest=HotelReservationTest test
```

## Project Structure

```
hotel-reservation-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── pages/
│   │           │   ├── HomePage.java
│   │           │   ├── SearchResultsPage.java
│   │           │   ├── HotelDetailsPage.java
│   │           │   └── ReservationPage.java
│   │           └── utils/
│   │               ├── WebDriverManager.java
│   │               └── ExcelReader.java
│   └── test/
│       ├── java/
│       │   └── com/example/
│       │       └── tests/
│       │           ├── SearchTest.java
│       │           ├── DateSelectionTest.java
│       │           ├── ReservationTest.java
│       │           └── HotelReservationTest.java
│       └── resources/
│           └── search_test_data.xlsx
├── pom.xml
└── README.md
```

## Key Components

### Pages:

- **HomePage.java:** Handles interactions with the Booking.com homepage (e.g., entering location and dates).
- **SearchResultsPage.java:** Handles interactions with the search results page (e.g., selecting a hotel).
- **HotelDetailsPage.java:** Handles interactions with the hotel details page (e.g., selecting a room and proceeding to reservation).
- **ReservationPage.java:** Handles interactions with the reservation page (e.g., verifying the hotel name).

### Tests:

- **SearchTest.java:** Validates the search functionality for hotels.
- **DateSelectionTest.java:** Validates the check-in and check-out date selection.
- **ReservationTest.java:** Ensures the reservation process functions correctly.
- **HotelReservationTest.java:** Integrates all steps to verify end-to-end functionality.

### Utilities:

- **WebDriverManager.java:** Manages the WebDriver instance and browser setup.
- **ExcelReader.java:** Reads test data from the Excel file (`testdata.xlsx`).
- **DataProvider in TestNG:** Used to fetch test data dynamically from Excel for parameterized testing.

