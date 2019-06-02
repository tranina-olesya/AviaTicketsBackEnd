package ru.vsu.aviaticketsback.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.aviaticketsback.services.TripsSearchService;
import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;
import ru.vsu.aviaticketsback.ticketssearch.providers.IATAProviderAPI;
import ru.vsu.aviaticketsback.ticketssearch.providers.KiwiProviderApi;
import ru.vsu.aviaticketsback.ticketssearch.providers.SkyScannerProviderAPI;
import ru.vsu.aviaticketsback.ticketssearch.providers.TicketProviderApi;
import ru.vsu.aviaticketsback.ticketssearch.utils.TripUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripsSearchServiceImpl implements TripsSearchService {
    private List<TicketProviderApi> apiProviders;
    private IATAProviderAPI iataProviderAPI;

    @Autowired
    public TripsSearchServiceImpl(IATAProviderAPI iataProviderAPI, SkyScannerProviderAPI skyScannerProviderAPI, KiwiProviderApi kiwiProviderApi) {
        this.iataProviderAPI = iataProviderAPI;
        apiProviders = new ArrayList<>();
        apiProviders.add(skyScannerProviderAPI);
        apiProviders.add(kiwiProviderApi);
    }

    @Override
    public Route getCityCodes(String origin, String destination) {
        return iataProviderAPI.getCityCodes(origin, destination);
    }

    @Override
    public List<Trip> getAllTrips(SearchData searchData) {
        List<Thread> threads = new ArrayList<>();

        List<List<Trip>> apiResponses = new ArrayList<>();
        for (final TicketProviderApi ticketProviderApi : apiProviders) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    List<Trip> tickets = ticketProviderApi.getTickets(searchData);
                    apiResponses.add(tickets);
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        for (TicketProviderApi ticketProviderApi: apiProviders){
//            apiResponses.add(ticketProviderApi.getTickets(searchData));
//        }
        return mergeTrips(apiResponses);
    }

    private List<Trip> mergeTrips(List<List<Trip>> trips) {
        List<Trip> result = new ArrayList<>();
        for (List<Trip> list : trips) {
            if (list == null)
                continue;
            if (result.isEmpty()) {
                result.addAll(list);
            } else {
                for (Trip trip : list) {
                    int index = findTripIndex(result, trip);
                    if (index >= 0) {
                        Trip resTrip = result.get(index);
                        resTrip.getPriceLinks().addAll(trip.getPriceLinks());
                        resTrip.setMinPrice(TripUtils.getMinPriceLink(trip.getPriceLinks()).getPrice());
                    } else
                        result.add(trip);
                }
            }
        }
        return result;
    }

    private int findTripIndex(List<Trip> trips, Trip p) {
        for (int i = 0; i < trips.size(); i++) {
            if (p.equals(trips.get(i)))
                return i;
        }
        return -1;
    }

}
