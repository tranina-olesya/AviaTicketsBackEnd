package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PricingOption implements Serializable {
    @JsonProperty("Agents")
    private List<Integer> agents;

    @JsonProperty("Price")
    private Double price;

    @JsonProperty("QuoteAgeInMinutes")
    private Integer quoteAgeInMinutes;

    @JsonProperty("DeeplinkUrl")
    private String deeplinkUrl;

    public PricingOption(List<Integer> agents, Double price, String deeplinkUrl) {
        this.agents = agents;
        this.price = price;
        this.deeplinkUrl = deeplinkUrl;
    }

    public PricingOption() {
    }

    public List<Integer> getAgents() {
        return agents;
    }

    public void setAgents(List<Integer> agents) {
        this.agents = agents;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }
}
