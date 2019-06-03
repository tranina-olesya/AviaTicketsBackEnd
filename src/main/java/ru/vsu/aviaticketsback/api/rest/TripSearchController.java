package ru.vsu.aviaticketsback.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.aviaticketsback.services.TripsSearchService;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;
import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.SearchPlace;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;
import ru.vsu.aviaticketsback.utils.DateConvert;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = {"Trips Search"})
public class TripSearchController {

    private TripsSearchService tripsSearchService;

    @Autowired
    public TripSearchController(TripsSearchService tripsSearchService) {
        this.tripsSearchService = tripsSearchService;
    }

    @ApiOperation(value = "Получить все билеты со следующими параметрами (формат ввода даты ДД-ММ-ГГГГ).")
    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public List<Trip> getTrips(String originCode, String originName, String destinationCode, String destinationName,
                               String dateOutbound, String dateInbound,
                               Integer adultsCount, Integer childrenCount, Integer infantsCount,
                               FlightType flightType, CabinClass cabinClass, Boolean transfers) {
        Date outbound = DateConvert.getDateFromRequest(dateOutbound);
        Date inbound = flightType.equals(FlightType.ROUND) ? DateConvert.getDateFromRequest(dateInbound) : null;

        SearchData searchData = new SearchData(new SearchPlace(originName, originCode),
                new SearchPlace(destinationName, destinationCode),
                outbound, inbound,
                adultsCount, childrenCount, infantsCount,
                flightType, transfers, cabinClass);

        return tripsSearchService.getAllTrips(searchData);
    }

    @ApiOperation(value = "Получить IATA-код городов отправления и прибытия.")
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public Route getCityCodes(String origin, String destination) {
        return tripsSearchService.getCityCodes(origin, destination);
    }
}
