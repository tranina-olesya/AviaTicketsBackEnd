package ru.vsu.aviaticketsback.ticketssearch.models.iata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class City {
    @JsonProperty("iata")
    private String iata;

    @JsonProperty("name")
    private String name;

    public City(String iata, String name) {
        this.iata = iata;
        this.name = name;
    }

    public City() {
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Iata: " + iata + "\nName: " + name;
    }
}
