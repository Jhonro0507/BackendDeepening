package com.OpenFeign.WorkshopSpringValidator.Service;

import com.OpenFeign.WorkshopSpringValidator.Model.CSVEntry;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CSVEntryValidator {
    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public boolean validateCSVEntry(CSVEntry csvEntry) {
        boolean isEmailValid = validateEmail(csvEntry.getEmail());
        boolean isDateOfBirthValid = validateDateOfBirth(csvEntry.getDateOfBirth());
        boolean isJobTitleValid = validateJobTitle(csvEntry.getJobTitle());

        return isEmailValid && isDateOfBirthValid && isJobTitleValid;
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateDateOfBirth(String dateOfBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(dateOfBirth);
            Date referenceDate = dateFormat.parse("1980-01-01");
            return date.after(referenceDate);
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validateJobTitle(String jobTitle) {
        String[] allowedJobTitles = {
                "Haematologist",
                "Phytotherapist",
                "Building surveyor",
                "Insurance account manager",
                "Educational psychologist"
        };

        for (String allowedTitle : allowedJobTitles) {
            if (jobTitle.equalsIgnoreCase(allowedTitle)) {
                return true;
            }
        }

        return false;
    }
}
