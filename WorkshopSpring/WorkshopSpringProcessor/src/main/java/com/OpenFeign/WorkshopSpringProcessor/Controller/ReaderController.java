package com.OpenFeign.WorkshopSpringProcessor.Controller;


import com.OpenFeign.WorkshopSpringProcessor.Client.EntryValidatorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final EntryValidatorClient entryValidatorClient;
    private final Reader entryProcessor;

    @Autowired
    public ReaderController(EntryValidatorClient entryValidatorClient, Reader entryProcessor) {
        this.entryValidatorClient = entryValidatorClient;
        this.entryProcessor = entryProcessor;
    }

    @PostMapping()
    public ResponseEntity<List<Entry>> processCSVOrXLSX(@RequestBody Map<String, String> requestBody) {
        String filePath = requestBody.get("filePath");

        try {
            List<Entry> entries = entryProcessor.readCSVOrXLSX(filePath);

            // Enviar cada entrada al servicio de validación y contar las válidas
            int validEntryCount = 0;
            if (entryValidatorClient == null) {
                // Manejar el caso en el que entryValidatorClient es nulo
            } else {
                for (Entry entry : entries) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        // Convertir el objeto a JSON
                        String json = objectMapper.writeValueAsString(entry);

                        // Imprimir el JSON en la consola
                        System.out.println("JSON a enviar: " + json);

                        // Ahora puedes enviar el JSON al otro servicio

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (entryValidatorClient.validateEntry(entry)) {
                        validEntryCount++;
                    }
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

