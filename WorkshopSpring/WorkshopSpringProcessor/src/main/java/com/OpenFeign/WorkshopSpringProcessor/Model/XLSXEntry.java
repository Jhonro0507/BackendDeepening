package com.OpenFeign.WorkshopSpringProcessor.Model;

import lombok.Getter;

public class XLSXEntry {

    @Getter
    private String date;
    @Getter
    private String injuryLocation;
    @Getter
    private String gender;
    @Getter
    private String ageGroup;
    @Getter
    private String incidentType;
    @Getter
    private String daysLost;
    @Getter
    private String plant;
    @Getter
    private String reportType;
    @Getter
    private String shift;
    @Getter
    private String department;
    @Getter
    private String incidentCost;
    @Getter
    private String wkDay;
    @Getter
    private Integer month;
    @Getter
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

