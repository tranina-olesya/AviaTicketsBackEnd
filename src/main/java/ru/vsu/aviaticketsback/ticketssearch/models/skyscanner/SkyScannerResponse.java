package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SkyScannerResponse {
    @JsonProperty("SessionKey")
    private String sessionKey;

    @JsonProperty("Query")
    private SkyScannerQuery query;

    @JsonProperty("Segments")
    private List<Segment> segments;

    @JsonProperty("Legs")
    private List<Leg> legs;

    @JsonProperty("Places")
    private List<SkyScannerPlace> places;

    @JsonProperty("Itineraries")
    private List<Itinerary> itineraries;

    @JsonProperty("Carriers")
    @JsonInclude
    private List<SkyScannerCarrier> carriers;

    @JsonProperty("Agents")
    private List<SkyScannerAgent> skyScannerAgents;

    public SkyScannerResponse(String sessionKey, SkyScannerQuery query, List<Segment> segments, List<Leg> legs, List<SkyScannerPlace> places, List<Itinerary> itineraries, List<SkyScannerCarrier> carriers, List<SkyScannerAgent> skyScannerAgents) {
        this.sessionKey = sessionKey;
        this.query = query;
        this.segments = segments;
        this.legs = legs;
        this.places = places;
        this.itineraries = itineraries;
        this.carriers = carriers;
        this.skyScannerAgents = skyScannerAgents;
    }

    public SkyScannerResponse() {
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public List<SkyScannerPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<SkyScannerPlace> places) {
        this.places = places;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<SkyScannerCarrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<SkyScannerCarrier> carriers) {
        this.carriers = carriers;
    }

    public List<SkyScannerAgent> getSkyScannerAgents() {
        return skyScannerAgents;
    }

    public void setSkyScannerAgents(List<SkyScannerAgent> skyScannerAgents) {
        this.skyScannerAgents = skyScannerAgents;
    }

    public SkyScannerQuery getQuery() {
        return query;
    }

    public void setQuery(SkyScannerQuery query) {
        this.query = query;
    }
}
