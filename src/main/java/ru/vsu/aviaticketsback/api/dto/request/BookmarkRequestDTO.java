package ru.vsu.aviaticketsback.api.dto.request;

import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

public class BookmarkRequestDTO {
    private Long userId;

    private String origin;

    private String destination;

    private int adultCount;

    private int childCount;

    private int infantCount;

    private FlightType flightType;

    private boolean transfers;

    private CabinClass classType;

    public BookmarkRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(int infantCount) {
        this.infantCount = infantCount;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public boolean getTransfers() {
        return transfers;
    }

    public void setTransfers(boolean transfers) {
        this.transfers = transfers;
    }

    public CabinClass getClassType() {
        return classType;
    }

    public void setClassType(CabinClass classType) {
        this.classType = classType;
    }
}
