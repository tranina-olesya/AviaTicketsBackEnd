package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.aviaticketsback.ticketssearch.models.Agent;

public class SkyScannerAgent extends Agent {
    @JsonProperty("Id")
    private Integer id;

    public SkyScannerAgent(Integer id, String name, String imageUrl) {
        super(name, imageUrl);
        this.id = id;
    }

    public SkyScannerAgent() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
