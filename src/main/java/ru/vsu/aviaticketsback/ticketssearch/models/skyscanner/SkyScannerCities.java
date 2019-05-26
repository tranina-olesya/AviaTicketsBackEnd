package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SkyScannerCities {

    @JsonProperty("Places")
    private List<SkyScannerCity> places = null;

    public List<SkyScannerCity> getPlaces() {
        return places;
    }

    public void setPlaces(List<SkyScannerCity> places) {
        this.places = places;
    }

}
