package com.OpenFeign.WorkshopSpringValidator.Controller;

import com.OpenFeign.WorkshopSpringValidator.Client.EntryProcessorClient;
import com.OpenFeign.WorkshopSpringValidator.Model.CSVEntry;
import com.OpenFeign.WorkshopSpringValidator.Model.Entry;
import com.OpenFeign.WorkshopSpringValidator.Model.XLSXEntry;
import com.OpenFeign.WorkshopSpringValidator.Service.CSVEntryValidator;
import com.OpenFeign.WorkshopSpringValidator.Service.XLSXEntryValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/entryValidator")
public class EntryValidatorController {
    private EntryProcessorClient entryProcessorClient;

    private CSVEntryValidator csvEntryValidator;

    private XLSXEntryValidator xlsxEntryValidator;
    @Autowired
    public EntryValidatorController(EntryProcessorClient entryProcessorClient, CSVEntryValidator csvEntryValidator, XLSXEntryValidator xlsxEntryValidator) {
        this.entryProcessorClient = entryProcessorClient;
        this.csvEntryValidator = csvEntryValidator;
        this.xlsxEntryValidator = xlsxEntryValidator;
    }


    @PostMapping(value = "/validateEntry", consumes = MediaType.APPLICATION_JSON_VALUE)
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
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // Convertir el objeto a JSON
                    String json = objectMapper.writeValueAsString(entry.getEntryXLSXData());

                    // Imprimir el JSON en la consola
                    System.out.println("JSON a evaluar: " + json);

                    // Ahora puedes enviar el JSON al otro servicio

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(entry.getEntryXLSXData());
                return xlsxEntryValidator.validateXLSXEntry(entry.getEntryXLSXData());
            } else {
                return false; // Manejar el caso en el que entryData no es un objeto XLSXEntry
            }
        } else {
            return false; // Manejar otros casos, si es necesario
        }
    }
    private int validEntryCount = 0; // Variable para almacenar el recuento de entradas válidas

    @PostMapping("/countValidEntries")
    public void countValidEntries(int count) {
        validEntryCount += count;
    }
}
