package ru.vsu.aviaticketsback.services;

import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;

import java.util.List;

public interface TripsSearchService {
    List<Trip> getAllTrips(SearchData searchData);

    Route getCityCodes(String origin, String destination);
}
