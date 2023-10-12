package com.OpenFeign.WorkshopSpringProcessor.Service;

import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reader entryProcessor = new Reader();
        try {
            List<Entry> entries = entryProcessor.readCSVOrXLSX("/Users/jhonatanromanroman/Documents/Makaia/Bootcamp/BackendDeepening/WorkshopSpring/people.csv");
            for (Entry entry : entries) {
                System.out.println(entry.getType());
                if (entry.getEntryCSVData() != null) {
                    System.out.println(entry.getEntryCSVData());
                }
                if (entry.getEntryXLSXData() != null) {
                    System.out.println(entry.getEntryXLSXData());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
