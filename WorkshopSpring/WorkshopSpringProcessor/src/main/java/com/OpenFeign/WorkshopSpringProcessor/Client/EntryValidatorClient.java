package com.OpenFeign.WorkshopSpringProcessor.Client;

import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "validator-service", url = "http://localhost:8081/api/v1/entryValidator")
public interface EntryValidatorClient {

    @PostMapping()
    boolean validateEntry(Entry entry);

    @PostMapping("/countValidEntries")
    int countValidEntries(int validEntryCount);
}
