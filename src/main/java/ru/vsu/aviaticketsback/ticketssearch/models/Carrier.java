package ru.vsu.aviaticketsback.ticketssearch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Carrier implements Serializable {
    @JsonProperty("Code")
    private String code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ImageUrl")
    private String imageUrl;

    public Carrier(String code, String name, String imageUrl) {
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Carrier() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Carrier carrier = (Carrier) o;
        return Objects.equals(code, carrier.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
