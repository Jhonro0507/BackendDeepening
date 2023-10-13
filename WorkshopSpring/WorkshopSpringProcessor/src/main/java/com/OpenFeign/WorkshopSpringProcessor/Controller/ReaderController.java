package com.OpenFeign.WorkshopSpringProcessor.Controller;


import com.OpenFeign.WorkshopSpringProcessor.Client.EntryValidatorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> processCSVOrXLSX(@RequestBody Map<String, String> requestBody) {
        String filePath = requestBody.get("filePath");
        try {
            List<Entry> entries = entryProcessor.readCSVOrXLSX(filePath);

            // Enviar cada entrada al servicio de validación y contar las válidas
            int validEntryCount = 0;

            if (entryValidatorClient != null) {
                for (Entry entry : entries) {
                    if (entryValidatorClient.validateEntry(entry)) {
                        validEntryCount++;
                    }
                }
            }

            return ResponseEntity.ok("Número de líneas válidas: " +
                    validEntryCount + "\nNúmero de líneas inválidas: " +
                    (entries.size()-validEntryCount));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de E/S");
        } catch (CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Archivo CSV no válido");
        } catch (Exception e) {
            // Manejar otras excepciones no especificadas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado");
        }
    }


}

