package ru.vsu.aviaticketsback.entities;

import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "search_history_entries")
public class SearchHistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;

    private String origin;

    private String destination;

    private LocalDateTime outboundDate;

    private LocalDateTime inboundDate;

    private Integer adultsCount;

    private Integer childrenCount;

    private Integer infantsCount;

    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    private boolean transfers;

    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass;

    public SearchHistoryEntry() {
    }

    public SearchHistoryEntry(User user, Date date, String origin, String destination, LocalDateTime outboundDate, LocalDateTime inboundDate, Integer adultsCount, Integer childrenCount, Integer infantsCount, FlightType flightType, boolean transfers, CabinClass cabinClass) {
        this.user = user;
        this.origin = origin;
        this.destination = destination;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.adultsCount = adultsCount;
        this.childrenCount = childrenCount;
        this.infantsCount = infantsCount;
        this.flightType = flightType;
        this.transfers = transfers;
        this.cabinClass = cabinClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
