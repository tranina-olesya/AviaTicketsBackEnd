
package ru.vsu.aviaticketsback.ticketssearch.models.kiwi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Datum {

    private Double quality;

    private String flyTo;

    @JsonProperty("deep_link")
    private String deepLink;

    private List<String> airlines = null;

    @JsonProperty("pnr_count")
    private Integer pnrCount;

    @JsonProperty("fly_duration")
    private String flyDuration;

    @JsonProperty("return_duration")
    private String returnDuration;

    @JsonProperty("has_airport_change")
    private Boolean hasAirportChange;

    private Double distance;

    @JsonProperty("type_flights")
    private List<String> typeFlights = null;

    private String flyFrom;

    @JsonProperty("dTime")
    private Long dTime;

    private String cityFrom;

    private Duration duration;

    private String id;

    private Country countryTo;

    private Double price;

    private List<List<String>> routes = null;

    private String cityTo;

    private List<Object> transfers = null;

    private List<Route> route = null;

    @JsonProperty("aTime")
    private Long aTime;

    private Country countryFrom;


    public Datum() {
    }

    public Datum(Double quality, String flyTo, String deepLink, List<String> airlines, Integer pnrCount, String flyDuration, String returnDuration, Boolean hasAirportChange, Double distance, List<String> typeFlights, String flyFrom, Long dTime, String cityFrom, Duration duration, String id, Country countryTo, Double price, List<List<String>> routes, String cityTo, List<Object> transfers, List<Route> route, Long aTime, Country countryFrom) {
        this.quality = quality;
        this.flyTo = flyTo;
        this.deepLink = deepLink;
        this.airlines = airlines;
        this.pnrCount = pnrCount;
        this.flyDuration = flyDuration;
        this.returnDuration = returnDuration;
        this.hasAirportChange = hasAirportChange;
        this.distance = distance;
        this.typeFlights = typeFlights;
        this.flyFrom = flyFrom;
        this.dTime = dTime;
        this.cityFrom = cityFrom;
        this.duration = duration;
        this.id = id;
        this.countryTo = countryTo;
        this.price = price;
        this.routes = routes;
        this.cityTo = cityTo;
        this.transfers = transfers;
        this.route = route;
        this.aTime = aTime;
        this.countryFrom = countryFrom;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public void setFlyTo(String flyTo) {
        this.flyTo = flyTo;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public List<String> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<String> airlines) {
        this.airlines = airlines;
    }

    public Integer getPnrCount() {
        return pnrCount;
    }

    public void setPnrCount(Integer pnrCount) {
        this.pnrCount = pnrCount;
    }

    public String getFlyDuration() {
        return flyDuration;
    }

    public void setFlyDuration(String flyDuration) {
        this.flyDuration = flyDuration;
    }

    public Boolean getHasAirportChange() {
        return hasAirportChange;
    }

    public void setHasAirportChange(Boolean hasAirportChange) {
        this.hasAirportChange = hasAirportChange;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getTypeFlights() {
        return typeFlights;
    }

    public void setTypeFlights(List<String> typeFlights) {
        this.typeFlights = typeFlights;
    }

    public String getFlyFrom() {
        return flyFrom;
    }

    public void setFlyFrom(String flyFrom) {
        this.flyFrom = flyFrom;
    }

    public Long getDTime() {
        return dTime;
    }

    public void setDTime(Long dTime) {
        this.dTime = dTime;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(Country countryTo) {
        this.countryTo = countryTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<List<String>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<String>> routes) {
        this.routes = routes;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public List<Object> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Object> transfers) {
        this.transfers = transfers;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Long getATime() {
        return aTime;
    }

    public void setATime(Long aTime) {
        this.aTime = aTime;
    }

    public Country getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(Country countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getReturnDuration() {
        return returnDuration;
    }

    public void setReturnDuration(String returnDuration) {
        this.returnDuration = returnDuration;
    }
}
