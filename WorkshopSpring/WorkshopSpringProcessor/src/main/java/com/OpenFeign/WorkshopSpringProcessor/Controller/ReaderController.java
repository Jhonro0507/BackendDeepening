package com.OpenFeign.WorkshopSpringProcessor.Controller;

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
@RequestMapping("api/v1/reader")
public class ReaderController {

    @GetMapping()
    public ResponseEntity<List<Entry>> processCSVOrXLSX(@RequestBody Map<String, String> requestBody) {
        String filePath = requestBody.get("filePath");
        Reader entryProcessor = new Reader();

        try {
            List<Entry> entries = entryProcessor.readCSVOrXLSX(filePath);
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

