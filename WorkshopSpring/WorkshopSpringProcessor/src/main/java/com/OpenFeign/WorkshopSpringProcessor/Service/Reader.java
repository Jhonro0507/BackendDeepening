package com.OpenFeign.WorkshopSpringProcessor.Service;

import com.OpenFeign.WorkshopSpringProcessor.Model.CSVEntry;
import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import com.OpenFeign.WorkshopSpringProcessor.Model.XLSXEntry;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class Reader {

    public List<Entry> readCSVOrXLSX(String filePath) throws IOException, CsvValidationException {
        List<Entry> entries = new ArrayList<>();

        if (filePath.endsWith(".csv")) {
            entries.addAll(readCSV(filePath));
        } else if (filePath.endsWith(".xlsx")) {
            entries.addAll(readXLSX(filePath));
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }

        return entries;
    }

    private List<Entry> readCSV(String filePath) throws IOException, CsvValidationException {
        List<Entry> entries = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            String[] record;
            int index = 1;
            while ((record = csvReader.readNext()) != null) {
                CSVEntry csvEntry = new CSVEntry(record);
                Entry entry = new Entry("CSVEntry", null,csvEntry);
                entries.add(entry);
            }
        }

        return entries;
    }


    private List<Entry> readXLSX(String filePath) throws IOException {
        List<Entry> entries = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {

                    XLSXEntry xlsxEntry = new XLSXEntry(
                            dataFormatter.formatCellValue(row.getCell(0)),
                            dataFormatter.formatCellValue(row.getCell(1)),
                            dataFormatter.formatCellValue(row.getCell(2)),
                            dataFormatter.formatCellValue(row.getCell(3)),
                            dataFormatter.formatCellValue(row.getCell(4)),
                            dataFormatter.formatCellValue(row.getCell(5)),
                            dataFormatter.formatCellValue(row.getCell(6)),
                            dataFormatter.formatCellValue(row.getCell(7)),
                            dataFormatter.formatCellValue(row.getCell(8)),
                            dataFormatter.formatCellValue(row.getCell(9)),
                            dataFormatter.formatCellValue(row.getCell(10)),
                            evaluator.evaluate(row.getCell(11)).getStringValue(),
                            (int) row.getCell(12).getNumericCellValue(),
                            (int) row.getCell(13).getNumericCellValue()
                    );
                    Entry entry = new Entry("XLSXEntry", xlsxEntry,null);
                    entries.add(entry);
                }
            }
        }

        return entries;
    }


}
