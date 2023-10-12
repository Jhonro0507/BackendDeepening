package com.OpenFeign.WorkshopSpringProcessor.Model;

import lombok.Getter;

public class Entry {
    @Getter
    private String type; // La propiedad que contiene el tipo (CSV o XLSX)
    private XLSXEntry entryXLSXData;
    private CSVEntry entryCSVData;

    public Entry(String type, XLSXEntry entryXLSXData) {
        this.type = type;
        this.entryXLSXData = entryXLSXData;
    }

    public Entry(String type, CSVEntry entryCSVData) {
        this.type = type;
        this.entryCSVData = entryCSVData;
    }

    public void setType(String type) {
        this.type = type;
    }
    public XLSXEntry getEntryXLSXData() {
        return entryXLSXData;
    }
    public CSVEntry getEntryCSVData() {
        return entryCSVData;
    }

}