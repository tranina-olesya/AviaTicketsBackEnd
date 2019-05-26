package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Leg implements Serializable {
    @JsonProperty("Id")
    private String id;

    @JsonProperty("SegmentIds")
    private List<Integer> segmentIds;

    @JsonProperty("OriginStation")
    private Integer originStation;

    @JsonProperty("DestinationStation")
    private Integer destinationStation;

    @JsonProperty("Departure")
    private Date departure;

    @JsonProperty("Arrival")
    private Date arrival;

    @JsonProperty("Duration")
    private Integer duration;
//
//    @JsonProperty("Carriers")
//    private List<Integer> carriers;

    @JsonProperty("Directionality")
    private String directionality;
//
//    @JsonProperty("FlightNumbers")
//    private List<FlightNumber> flightNumbers;

    public Leg(String id, List<Integer> segmentIds, Integer originStation, Integer destinationStation, Date departure, Date arrival, Integer duration, List<Integer> carriers, String directionality, List<FlightNumber> flightNumbers) {
        this.id = id;
        this.segmentIds = segmentIds;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
//        this.carriers = carriers;
        this.directionality = directionality;
//        this.flightNumbers = flightNumbers;
    }

    public Leg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Integer> segmentIds) {
        this.segmentIds = segmentIds;
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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

//    public List<Integer> getCarriers() {
//        return carriers;
//    }
//
//    public void setCarriers(List<Integer> carriers) {
//        this.carriers = carriers;
//    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }

//    public List<FlightNumber> getFlightNumbers() {
//        return flightNumbers;
//    }
//
//    public void setFlightNumbers(List<FlightNumber> flightNumbers) {
//        this.flightNumbers = flightNumbers;
//    }
}
