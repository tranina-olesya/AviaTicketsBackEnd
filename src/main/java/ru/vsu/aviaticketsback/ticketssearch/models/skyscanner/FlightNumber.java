package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FlightNumber implements Serializable {
    @JsonProperty("FlightNumber")
    private String flightNumber;

    @JsonProperty("CarrierId")
    private Integer carrierId;

    public FlightNumber(String flightNumber, Integer carrierId) {
        this.flightNumber = flightNumber;
        this.carrierId = carrierId;
    }

    public FlightNumber() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }
}
