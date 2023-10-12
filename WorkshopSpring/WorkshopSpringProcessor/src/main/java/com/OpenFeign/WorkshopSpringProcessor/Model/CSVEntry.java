package com.OpenFeign.WorkshopSpringProcessor.Model;

import lombok.Data;
@Data
public class CSVEntry {
    private Integer index;
    private String userId;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String phone;
    private String dateOfBirth;
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