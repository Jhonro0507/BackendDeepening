package com.OpenFeign.WorkshopSpringProcessor.Client;

import com.OpenFeign.WorkshopSpringProcessor.Model.Entry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "validator-service", url = "http://localhost:8081/api/v1/entryValidator")
@Component
public interface EntryValidatorClient {

    @PostMapping(value = "/validateEntry", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean validateEntry(@RequestBody Entry entry);

}

