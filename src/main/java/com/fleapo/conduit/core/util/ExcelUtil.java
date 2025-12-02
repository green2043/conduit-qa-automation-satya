package com.fleapo.conduit.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/ConduitTestData.xlsx";

    public static Workbook book;
    public static Sheet sheet;

    public static Object[][] getTestdata(String sheetName) {
        Object[][] data = null;

        try (FileInputStream file = new FileInputStream(TEST_DATA_SHEET_PATH)) {
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in file: " 
                                           + TEST_DATA_SHEET_PATH);
            }

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            data = new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel file not present", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read file", e);
        }

        return data;
    }

}
