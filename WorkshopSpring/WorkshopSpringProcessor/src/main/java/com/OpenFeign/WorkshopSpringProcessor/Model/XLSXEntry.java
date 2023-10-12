package com.OpenFeign.WorkshopSpringProcessor.Model;

import lombok.Data;

@Data
public class XLSXEntry {

    private String date;
    private String injuryLocation;
    private String gender;
    private String ageGroup;
    private String incidentType;
    private String daysLost;
    private String plant;
    private String reportType;
    private String shift;
    private String department;
    private String incidentCost;
    private String wkDay;
    private Integer month;
    private Integer year;

    public XLSXEntry(String date, String injuryLocation, String gender, String ageGroup, String incidentType, String daysLost, String plant, String reportType, String shift, String department, String incidentCost, String wkDay, Integer month, Integer year) {
        this.date = date;
        this.injuryLocation = injuryLocation;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.incidentType = incidentType;
        this.daysLost = daysLost;
        this.plant = plant;
        this.reportType = reportType;
        this.shift = shift;
        this.department = department;
        this.incidentCost = incidentCost;
        this.wkDay = wkDay;
        this.month = month;
        this.year = year;
    }

}

