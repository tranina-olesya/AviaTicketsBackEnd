package ru.vsu.aviaticketsback.ticketssearch.converters;

import ru.vsu.aviaticketsback.ticketssearch.models.Agent;
import ru.vsu.aviaticketsback.ticketssearch.models.Carrier;
import ru.vsu.aviaticketsback.ticketssearch.models.Flight;
import ru.vsu.aviaticketsback.ticketssearch.models.PriceLink;
import ru.vsu.aviaticketsback.ticketssearch.models.Ticket;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.Itinerary;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.Leg;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.PricingOption;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.Segment;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerAgent;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerCarrier;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerPlace;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerResponse;
import ru.vsu.aviaticketsback.ticketssearch.utils.TripUtils;

import java.util.ArrayList;
import java.util.List;

public class SkyScannerResponseConverter {

    public static List<Trip> convertResponseToTrip(SkyScannerResponse response) {
        List<Trip> trips = new ArrayList<>();
        for (final Itinerary itinerary : response.getItineraries()) {
            Trip trip = new Trip();
            Leg outboundLeg = findLegById(response.getLegs(), itinerary.getOutboundLegId());
            Leg inboundLeg = findLegById(response.getLegs(), itinerary.getInboundLegId());
            Flight outbound = formFlight(response, outboundLeg);
            Flight inbound = formFlight(response, inboundLeg);
            trip.setOutbound(outbound);
            trip.setInbound(inbound);
            List<PriceLink> priceLinks = formPriceLinks(response.getSkyScannerAgents(), itinerary);
            trip.setPriceLinks(priceLinks);
            trip.setMinPrice(TripUtils.getMinPriceLink(trip.getPriceLinks()).getPrice());

            trips.add(trip);
        }
        return trips;
    }

    private static SkyScannerPlace findPlaceById(List<SkyScannerPlace> places, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerPlace place : places) {
            if (place.getId().equals(id))
                return place;
        }
        return null;
    }

    private static Leg findLegById(List<Leg> legs, String id) {
        if (id == null)
            return null;
        for (Leg leg : legs) {
            if (leg.getId().equals(id))
                return leg;
        }
        return null;
    }

    private static Carrier findCarrierById(List<SkyScannerCarrier> carriers, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerCarrier carrier : carriers) {
            if (carrier.getId().equals(id))
                return carrier;
        }
        return null;
    }

    private static SkyScannerAgent findAgentById(List<SkyScannerAgent> agents, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerAgent agent : agents) {
            if (agent.getId().equals(id))
                return agent;
        }
        return null;
    }

    private static Segment findSegmentById(List<Segment> segments, Integer id) {
        if (id == null)
            return null;
        for (Segment segment : segments) {
            if (segment.getId().equals(id))
                return segment;
        }
        return null;
    }

    private static Flight formFlight(final SkyScannerResponse response, final Leg leg) {
        if (response == null || leg == null)
            return null;
        Flight flight = new Flight();
        flight.setAdultsCount(response.getQuery().getAdults());
        flight.setChildrenCount(response.getQuery().getChildren());
        flight.setInfantsCount(response.getQuery().getInfants());
        flight.setOutboundDate(leg.getDeparture());
        flight.setInboundDate(leg.getArrival());
        flight.setDuration(leg.getDuration());
        flight.setOrigin(findPlaceById(response.getPlaces(), leg.getOriginStation()));
        flight.setDestination(findPlaceById(response.getPlaces(), leg.getDestinationStation()));
        for (Integer segmentID : leg.getSegmentIds()) {
            Ticket ticket = new Ticket();
            Segment segment = findSegmentById(response.getSegments(), segmentID);
            ticket.setOrigin(findPlaceById(response.getPlaces(), segment.getOriginStation()));
            ticket.setDestination(findPlaceById(response.getPlaces(), segment.getDestinationStation()));
            ticket.setOutboundDate(segment.getDepartureDateTime());
            ticket.setInboundDate(segment.getArrivalDateTime());
            ticket.setDuration(segment.getDuration());
            ticket.setCarrier(findCarrierById(response.getCarriers(), segment.getCarrier()));
            ticket.setFlightNumber(segment.getFlightNumber());
            flight.getFlightParts().add(ticket);

        }
        return flight;
    }

    private static List<PriceLink> formPriceLinks(List<SkyScannerAgent> skyScannerAgents, Itinerary itinerary) {
        List<PriceLink> priceLinks = new ArrayList<>();
        for (PricingOption pricingOption : itinerary.getPricingOptions()) {
            PriceLink priceLink = new PriceLink();
            List<Agent> agents = new ArrayList<>();
            for (Integer agentId : pricingOption.getAgents()) {
                Agent agent = findAgentById(skyScannerAgents, agentId);
                agents.add(new Agent(agent.getName(), agent.getImageUrl()));
            }
            priceLink.setAgents(agents);
            priceLink.setDeepLink(pricingOption.getDeeplinkUrl());
            priceLink.setPrice(pricingOption.getPrice());
            priceLinks.add(priceLink);
        }
        return priceLinks;
    }
}
