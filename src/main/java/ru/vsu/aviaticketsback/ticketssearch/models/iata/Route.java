package ru.vsu.aviaticketsback.ticketssearch.models.iata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {
    @JsonProperty("origin")
    private City origin;

    @JsonProperty("destination")
    private City destination;

    public Route(City origin, City destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route() {
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Origin:\n{\n" + origin.toString() + "\n}\nDestination:\n{\n" + destination.toString() + "\n}\n";
    }
}
