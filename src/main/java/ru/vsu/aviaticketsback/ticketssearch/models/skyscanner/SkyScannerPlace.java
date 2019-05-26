package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.aviaticketsback.ticketssearch.models.Place;

public class SkyScannerPlace extends Place {
    @JsonProperty("Id")
    private Integer id;

    public SkyScannerPlace(Integer id, String code, String type, String name) {
        super(code, type, name);
        this.id = id;
    }

    public SkyScannerPlace() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
