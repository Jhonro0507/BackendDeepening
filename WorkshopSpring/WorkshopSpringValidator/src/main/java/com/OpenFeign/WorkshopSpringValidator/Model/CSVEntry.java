package com.OpenFeign.WorkshopSpringValidator.Model;

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

    public CSVEntry(Integer index, String userId, String firstName, String lastName, String sex, String email, String phone, String dateOfBirth, String jobTitle) {
        this.index = index;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

}
