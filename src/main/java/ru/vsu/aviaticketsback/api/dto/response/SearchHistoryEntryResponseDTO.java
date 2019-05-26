package ru.vsu.aviaticketsback.api.dto.response;

import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.time.LocalDateTime;

public class SearchHistoryEntryResponseDTO {
    private Long id;

    private Long userId;

    private String origin;

    private String destination;

    private LocalDateTime outboundDate;

    private LocalDateTime inboundDate;

    private Integer adultsCount;

    private Integer childrenCount;

    private Integer infantsCount;

    private FlightType flightType;

    private boolean transfers;

    private CabinClass cabinClass;

    public SearchHistoryEntryResponseDTO() {
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

    public LocalDateTime getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(LocalDateTime outboundDate) {
        this.outboundDate = outboundDate;
    }

    public LocalDateTime getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(LocalDateTime inboundDate) {
        this.inboundDate = inboundDate;
    }

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getInfantsCount() {
        return infantsCount;
    }

    public void setInfantsCount(Integer infantsCount) {
        this.infantsCount = infantsCount;
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

    public CabinClass getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(CabinClass cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Long getId() {
    return id;
}

    public void setId(Long id) {
        this.id = id;
    }
}
