package com.OpenFeign.WorkshopSpringValidator.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Entry {
    private String type;
    private XLSXEntry entryXLSXData;
    private CSVEntry entryCSVData;

    @JsonCreator
    public Entry(
            @JsonProperty("type") String type,
            @JsonProperty("entryXLSXData") XLSXEntry entryXLSXData,
            @JsonProperty("entryCSVData") CSVEntry entryCSVData) {
        this.type = type;
        this.entryXLSXData = entryXLSXData;
        this.entryCSVData = entryCSVData;
    }
}
