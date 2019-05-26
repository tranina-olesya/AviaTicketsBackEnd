package ru.vsu.aviaticketsback.ticketssearch.converters;

import ru.vsu.aviaticketsback.ticketssearch.models.Agent;
import ru.vsu.aviaticketsback.ticketssearch.models.Carrier;
import ru.vsu.aviaticketsback.ticketssearch.models.Flight;
import ru.vsu.aviaticketsback.ticketssearch.models.Place;
import ru.vsu.aviaticketsback.ticketssearch.models.PriceLink;
import ru.vsu.aviaticketsback.ticketssearch.models.Ticket;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.kiwi.Datum;
import ru.vsu.aviaticketsback.ticketssearch.models.kiwi.KiwiResponse;
import ru.vsu.aviaticketsback.ticketssearch.models.kiwi.Route;
import ru.vsu.aviaticketsback.ticketssearch.models.kiwi.SearchParams;
import ru.vsu.aviaticketsback.ticketssearch.utils.TripUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KiwiResponseConverter {

    public static List<Trip> convertResponseToTrip(KiwiResponse response) {
        List<Trip> trips = new ArrayList<>();
        for (Datum data : response.getData()) {
            Trip trip = new Trip();

            List<PriceLink> priceLinks = new ArrayList<>();
            List<Agent> agents = new ArrayList<>();
            agents.add(new Agent("Kiwi.com", "https://s1.apideeplink.com/images/websites/skyp.png"));
            PriceLink priceLink = new PriceLink(agents, data.getPrice(), data.getDeepLink());
            priceLinks.add(priceLink);
            trip.setPriceLinks(priceLinks);
            trip.setOutbound(formFlight(response.getSearchParams(), data, 0));
            trip.setInbound(formFlight(response.getSearchParams(), data, 1));
            trip.setMinPrice(TripUtils.getMinPriceLink(trip.getPriceLinks()).getPrice());

            trips.add(trip);
        }
        return trips;
    }

    private static List<Route> getRoutesByDirection(List<Route> routes, int direction) {
        List<Route> results = new ArrayList<>();
        for (Route route : routes) {
            if (route.getReturn() == direction)
                results.add(route);
        }
        return results;
    }

    private static List<Ticket> formTicketsList(SearchParams searchParams, Datum data, int direction) {
        List<Ticket> tickets = new ArrayList<>();
        for (Route route : getRoutesByDirection(data.getRoute(), direction)) {
            Ticket ticket = new Ticket();
            String operatingCarrier = !route.getOperatingCarrier().isEmpty() ? route.getOperatingCarrier() : route.getAirline();
            ticket.setCarrier(new Carrier(operatingCarrier, route.getAirline(), String.format("https://pics.avs.io/120/60/%s.png", route.getAirline())));
            ticket.setOrigin(new Place(route.getFlyFrom(), searchParams.getFlyFromType(), route.getCityFrom()));
            ticket.setDestination(new Place(route.getFlyTo(), searchParams.getToType(), route.getCityTo()));
            ticket.setOutboundDate(new Date(route.getDTime() * 1000L));
            ticket.setInboundDate(new Date(route.getATime() * 1000L));
            ticket.setDuration((int) ((route.getATime() - route.getDTime()) / (60L)));
            ticket.setFlightNumber(route.getFlightNo().toString());
            tickets.add(ticket);
        }
        return tickets;
    }

    private static Flight formFlight(SearchParams searchParams, Datum data, int direction) {
        Flight flight = new Flight();
        flight.setAdultsCount(searchParams.getSeats().getAdults());
        flight.setChildrenCount(searchParams.getSeats().getChildren());
        flight.setInfantsCount(searchParams.getSeats().getInfants());

        if (direction == 0)
            flight.setDuration((data.getDuration().getDeparture() / 60));
        else
            flight.setDuration((data.getDuration().getReturn() / 60));
        List<Ticket> flightParts = formTicketsList(searchParams, data, direction);
        if (flightParts.isEmpty())
            return null;

        flight.setFlightParts(flightParts);
        Ticket start = flightParts.get(0);
        Ticket end = flightParts.get(flightParts.size() - 1);
        flight.setOutboundDate(start.getOutboundDate());
        flight.setInboundDate(end.getInboundDate());
        flight.setOrigin(start.getOrigin());
        flight.setDestination(end.getDestination());
        return flight;
    }
}
