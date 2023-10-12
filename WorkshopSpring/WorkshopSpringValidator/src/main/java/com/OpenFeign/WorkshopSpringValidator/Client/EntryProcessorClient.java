package com.OpenFeign.WorkshopSpringValidator.Client;

import com.OpenFeign.WorkshopSpringValidator.Model.Entry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "processor-service", url = "http://localhost:8080/api/v1/processor")
public interface EntryProcessorClient {

    @PostMapping()
    List<Entry> processCSVOrXLSX(Entry entry);
}
