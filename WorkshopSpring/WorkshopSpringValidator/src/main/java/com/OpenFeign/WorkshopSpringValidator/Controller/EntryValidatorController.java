package com.OpenFeign.WorkshopSpringValidator.Controller;

import com.OpenFeign.WorkshopSpringValidator.Model.CSVEntry;
import com.OpenFeign.WorkshopSpringValidator.Model.Entry;
import com.OpenFeign.WorkshopSpringValidator.Model.XLSXEntry;
import com.OpenFeign.WorkshopSpringValidator.Service.CSVEntryValidator;
import com.OpenFeign.WorkshopSpringValidator.Service.XLSXEntryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/entryValidator")
public class EntryValidatorController {

    @Autowired
    private CSVEntryValidator csvEntryValidator;

    @Autowired
    private XLSXEntryValidator xlsxEntryValidator;

    @PostMapping()
    public boolean validateEntry(@RequestBody Entry entry) {
        String type = entry.getType();

        if ("CSVEntry".equals(type)) {
            if (entry.getEntryCSVData() instanceof CSVEntry) {
                return csvEntryValidator.validateCSVEntry((CSVEntry) entry.getEntryCSVData());
            } else {
                return false; // Manejar el caso en el que entryData no es un objeto CSVEntry
            }
        } else if ("XLSXEntry".equals(type)) {
            if (entry.getEntryXLSXData() instanceof XLSXEntry) {
                return xlsxEntryValidator.validateXLSXEntry((XLSXEntry) entry.getEntryXLSXData());
            } else {
                return false; // Manejar el caso en el que entryData no es un objeto XLSXEntry
            }
        } else {
            return false; // Manejar otros casos, si es necesario
        }
    }
}
