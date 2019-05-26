package ru.vsu.aviaticketsback.ticketssearch.models.kiwi;

public class Seats {

    private Integer infants;

    private Integer passengers;

    private Integer adults;

    private Integer children;

    public Seats() {
    }

    public Seats(Integer infants, Integer passengers, Integer adults, Integer children) {
        super();
        this.infants = infants;
        this.passengers = passengers;
        this.adults = adults;
        this.children = children;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

}
