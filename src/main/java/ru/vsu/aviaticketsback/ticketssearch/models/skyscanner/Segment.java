package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Segment implements Serializable {
    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("OriginStation")
    private Integer originStation;

    @JsonProperty("DestinationStation")
    private Integer destinationStation;

    @JsonProperty("DepartureDateTime")
    private Date departureDateTime;

    @JsonProperty("ArrivalDateTime")
    private Date arrivalDateTime;

    @JsonProperty("Carrier")
    private Integer carrier;

    @JsonProperty("Duration")
    private Integer duration;

    @JsonProperty("FlightNumber")
    private String flightNumber;

    @JsonProperty("Directionality")
    private String directionality;

    public Segment(Integer id, Integer originStation, Integer destinationStation, Date departureDateTime, Date arrivalDateTime, Integer carrier, Integer duration, String flightNumber, String directionality) {
        this.id = id;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.carrier = carrier;
        this.duration = duration;
        this.flightNumber = flightNumber;
        this.directionality = directionality;
    }

    public Segment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOriginStation() {
        return originStation;
    }

    public void setOriginStation(Integer originStation) {
        this.originStation = originStation;
    }

    public Integer getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Integer destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Integer getCarrier() {
        return carrier;
    }

    public void setCarrier(Integer carrier) {
        this.carrier = carrier;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }
}
