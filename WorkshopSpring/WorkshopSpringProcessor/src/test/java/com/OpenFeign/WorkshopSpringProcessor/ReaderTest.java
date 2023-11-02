package com.OpenFeign.WorkshopSpringProcessor;

import com.OpenFeign.WorkshopSpringProcessor.Model.CSVEntry;
import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import com.OpenFeign.WorkshopSpringProcessor.Model.XLSXEntry;
import com.OpenFeign.WorkshopSpringProcessor.Service.Reader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReaderTest {

    private Reader reader;

    @BeforeEach
    public void setUp() {
        this.reader = new Reader();
    }

    @Test
    public void testReadCSV() throws IOException, CsvValidationException {
        // Arrange
        String filePath = "test.csv";
        String[] csvRecord = {"value1", "value2", "value3"};
        // Mock CSVReader para simular el comportamiento
        CSVReader csvReader = mock(CSVReader.class);
        when(csvReader.readNext()).thenReturn(csvRecord, null);

        try {
            whenNew(CSVReader.class)
                    .withArguments(any(FileReader.class))
                    .thenReturn(csvReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        List<Entry> entries = reader.readCSV(filePath);

        // Assert
        assertEquals(1, entries.size());
        Entry expectedEntry = new Entry("CSVEntry", null, new CSVEntry(csvRecord));
        assertEquals(expectedEntry, entries.get(0));
    }

    @Test
    public void testReadXLSX() throws IOException {
        // Arrange
        String filePath = "test.xlsx";
        // Mock Workbook y Sheet para simular el comportamiento
        Workbook workbook = mock(XSSFWorkbook.class);
        Sheet sheet = mock(Sheet.class);
        Row row = mock(Row.class);
        when(workbook.getSheetAt(0)).thenReturn(sheet);
        when(sheet.getRow(1)).thenReturn(row);

        when(row.getCell(0)).thenReturn(mock(Cell.class));
        when(row.getCell(1)).thenReturn(mock(Cell.class));
        when(row.getCell(2)).thenReturn(mock(Cell.class));
        when(row.getCell(3)).thenReturn(mock(Cell.class));
        when(row.getCell(4)).thenReturn(mock(Cell.class));
        when(row.getCell(5)).thenReturn(mock(Cell.class));
        when(row.getCell(6)).thenReturn(mock(Cell.class));
        when(row.getCell(7)).thenReturn(mock(Cell.class));
        when(row.getCell(8)).thenReturn(mock(Cell.class));
        when(row.getCell(9)).thenReturn(mock(Cell.class));
        when(row.getCell(10)).thenReturn(mock(Cell.class));
        when(row.getCell(11)).thenReturn(mock(Cell.class));
        when(row.getCell(12)).thenReturn(mock(Cell.class));
        when(row.getCell(13)).thenReturn(mock(Cell.class));

        when(row.getCell(0).toString()).thenReturn("value1");
        when(row.getCell(1).toString()).thenReturn("value2");
        when(row.getCell(2).toString()).thenReturn("value3");
        // Act
        List<Entry> entries = reader.readXLSX(filePath);

        // Assert
        assertEquals(1, entries.size());
        XLSXEntry xlsxEntry = new XLSXEntry(
                "value1", "value2", "value3", "value4", "value5",
                "value6", "value7", "value8", "value9", "value10",
                "value11", "value12", 1, 2);
        Entry expectedEntry = new Entry("XLSXEntry", xlsxEntry, null);
        assertEquals(expectedEntry, entries.get(0));
    }
}
