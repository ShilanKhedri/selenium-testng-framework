package com.Shilan.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getSheetData(String filePath, String sheetName) {
        Object[][] data = null;

        try (FileInputStream input = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Can't find sheet: " + sheetName);
            }

            int totalRows = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException("Sheet is empty or header row is missing!");
            }

            int totalCols = headerRow.getLastCellNum();

            data = new Object[totalRows][totalCols];
            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i < totalRows; i++) {
                Row row = sheet.getRow(i + 1);

                for (int j = 0; j < totalCols; j++) {
                    if (row == null) {
                        data[i][j] = "";
                    } else {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            data[i][j] = "";
                        } else {
                            data[i][j] = formatter.formatCellValue(cell);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read file: " + e.getMessage());
        }

        return data;
    }
}