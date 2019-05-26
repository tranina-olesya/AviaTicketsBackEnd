package ru.vsu.aviaticketsback.ticketssearch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Place implements Serializable {
    @JsonProperty("Code")
    private String code;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Name")
    private String name;

    public Place(String code, String type, String name) {
        this.code = code;
        this.type = type;
        this.name = name;
    }

    public Place() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Place place = (Place) o;
        return Objects.equals(code, place.code) &&
                Objects.equals(type, place.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }
}
