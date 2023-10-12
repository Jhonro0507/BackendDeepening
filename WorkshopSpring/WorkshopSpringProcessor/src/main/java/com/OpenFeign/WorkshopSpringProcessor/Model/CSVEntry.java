package com.OpenFeign.WorkshopSpringProcessor.Model;

import lombok.Getter;

public class CSVEntry {
    @Getter
    private Integer index;
    @Getter
    private String userId;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String sex;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private String dateOfBirth;
    @Getter
    private String jobTitle;
    public CSVEntry(String[] record) {
        this.index = Integer.parseInt(record[0]);
        this.userId = record[1];
        this.firstName = record[2];
        this.lastName = record[3];
        this.sex = record[4];
        this.email = record[5];
        this.phone = record[6];
        this.dateOfBirth = record[7];
        this.jobTitle = record[8];
    }


}