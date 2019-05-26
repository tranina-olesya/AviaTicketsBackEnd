package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.aviaticketsback.ticketssearch.models.Carrier;

public class SkyScannerCarrier extends Carrier {

    @JsonProperty("Id")
    private Integer id;

    public SkyScannerCarrier(Integer id, String code, String name, String imageUrl) {
        super(code, name, imageUrl);
        this.id = id;
    }

    public SkyScannerCarrier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
