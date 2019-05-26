package ru.vsu.aviaticketsback.ticketssearch.providers;

import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;

import java.util.List;

public interface TicketProviderApi {
    List<Trip> getTickets(SearchData searchData);
}
