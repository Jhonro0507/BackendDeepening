package com.OpenFeign.WorkshopSpringValidator.Controller;

import com.OpenFeign.WorkshopSpringValidator.Model.CSVEntry;
import com.OpenFeign.WorkshopSpringValidator.Model.Entry;
import com.OpenFeign.WorkshopSpringValidator.Service.CSVEntryValidator;
import com.OpenFeign.WorkshopSpringValidator.Service.XLSXEntryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/entryValidator")
public class EntryValidatorController {
    private CSVEntryValidator csvEntryValidator;

    private XLSXEntryValidator xlsxEntryValidator;
    @Autowired
    public EntryValidatorController(CSVEntryValidator csvEntryValidator, XLSXEntryValidator xlsxEntryValidator) {
        this.csvEntryValidator = csvEntryValidator;
        this.xlsxEntryValidator = xlsxEntryValidator;
    }


    @PostMapping(value = "/validateEntry", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean validateEntry(@RequestBody Entry entry) {
        String type = entry.getType();

        if ("CSVEntry".equals(type)) {
            if (entry.getEntryCSVData() != null) {
                return csvEntryValidator.validateCSVEntry(entry.getEntryCSVData());
            } else {
                // Lanzar una excepción personalizada si entryData no es un objeto CSVEntry
                throw new CustomValidationException("EntryData no es un objeto CSVEntry");
            }
        } else if ("XLSXEntry".equals(type)) {
            if (entry.getEntryXLSXData() != null) {
                return xlsxEntryValidator.validateXLSXEntry(entry.getEntryXLSXData());
            } else {
                // Lanzar una excepción personalizada si entryData no es un objeto XLSXEntry
                throw new CustomValidationException("EntryData no es un objeto XLSXEntry");
            }
        } else {
            // Lanzar una excepción personalizada para manejar otros casos, si es necesario
            throw new CustomValidationException("Tipo de entrada no válido");
        }
    }

}
