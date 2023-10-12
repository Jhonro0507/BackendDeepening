package com.OpenFeign.WorkshopSpringProcessor.Controller;


import com.OpenFeign.WorkshopSpringProcessor.Client.EntryValidatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import com.OpenFeign.WorkshopSpringProcessor.Service.Reader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/processor")
public class ReaderController {

    @Autowired
    private EntryValidatorClient entryValidatorClient;

    @PostMapping()
    public ResponseEntity<List<Entry>> processCSVOrXLSX(@RequestBody Map<String, String> requestBody) {
        String filePath = requestBody.get("filePath");
        Reader entryProcessor = new Reader();

        try {
            List<Entry> entries = entryProcessor.readCSVOrXLSX(filePath);

            // Enviar cada entrada al servicio de validación y contar las válidas
            int validEntryCount = 0;
            for (Entry entry : entries) {
                if (entryValidatorClient.validateEntry(entry)) {
                    validEntryCount++;
                }
            }

            // Enviar el recuento de entradas válidas al servicio de validación
            entryValidatorClient.countValidEntries(validEntryCount);

            return ResponseEntity.ok(entries);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar el error de E/S
            return ResponseEntity.status(500).body(null);
        } catch (CsvValidationException e) {
            // Manejar el error de validación CSV
            return ResponseEntity.status(400).body(null);
        }
    }

}

