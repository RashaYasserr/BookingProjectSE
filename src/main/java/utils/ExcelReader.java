package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    /**
     * Reads data from an Excel file and returns it as a 2D Object array.
     *
     * @param filePath  The path to the Excel file (e.g., "testdata.xlsx").
     * @param sheetName The name of the sheet to read from.
     * @return A 2D Object array containing the data from the Excel sheet.
     * @throws IOException If the file is not found or cannot be read.
     */
    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        // Open the Excel file
        FileInputStream file = new FileInputStream(new File("src/test/resources/" + filePath));
        Workbook workbook = new XSSFWorkbook(file); // Use XSSFWorkbook for .xlsx files
        Sheet sheet = workbook.getSheet(sheetName); // Get the specified sheet

        // Get the number of rows and columns
        int rowCount = sheet.getPhysicalNumberOfRows(); // Total rows
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // Total columns (based on the first row)

        // Create a 2D array to store the data (excluding the header row)
        Object[][] data = new Object[rowCount - 1][colCount];

        // Iterate through rows (skip the header row)
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i); // Get the current row
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j); // Get the current cell
                data[i - 1][j] = (cell == null) ? "" : cell.toString(); // Add the cell value to the data array
            }
        }

        // Close the workbook and file input stream
        workbook.close();
        file.close();

        return data; // Return the data array
    }
}