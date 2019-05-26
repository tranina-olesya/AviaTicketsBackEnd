package ru.vsu.aviaticketsback.ticketssearch.models.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Itinerary implements Serializable {
    @JsonProperty("OutboundLegId")
    private String outboundLegId;

    @JsonProperty("InboundLegId")
    private String inboundLegId;

    @JsonProperty("PricingOptions")
    private List<PricingOption> pricingOptions;

    public Itinerary(String outboundLegId, String inboundLegId, List<PricingOption> pricingOptions) {
        this.outboundLegId = outboundLegId;
        this.inboundLegId = inboundLegId;
        this.pricingOptions = pricingOptions;
    }

    public Itinerary() {
    }

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return inboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        this.inboundLegId = inboundLegId;
    }

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }
}
