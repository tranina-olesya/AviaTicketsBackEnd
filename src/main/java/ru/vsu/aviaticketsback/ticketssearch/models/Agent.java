package ru.vsu.aviaticketsback.ticketssearch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Agent implements Serializable {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("ImageUrl")
    private String imageUrl;

    public Agent(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Agent() {
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
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(name, agent.name) &&
                Objects.equals(imageUrl, agent.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl);
    }
}
