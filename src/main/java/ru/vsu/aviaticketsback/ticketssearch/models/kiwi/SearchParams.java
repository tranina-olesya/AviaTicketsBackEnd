
package ru.vsu.aviaticketsback.ticketssearch.models.kiwi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchParams {

    @JsonProperty("to_type")
    private String toType;

    @JsonProperty("flyFrom_type")
    private String flyFromType;

    private Seats seats;

    public SearchParams() {
    }

    public SearchParams(String toType, String flyFromType, Seats seats) {
        super();
        this.toType = toType;
        this.flyFromType = flyFromType;
        this.seats = seats;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getFlyFromType() {
        return flyFromType;
    }

    public void setFlyFromType(String flyFromType) {
        this.flyFromType = flyFromType;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

}
