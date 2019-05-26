package ru.vsu.aviaticketsback.ticketssearch.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vsu.aviaticketsback.ticketssearch.converters.KiwiResponseConverter;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;
import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;
import ru.vsu.aviaticketsback.ticketssearch.models.kiwi.KiwiResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class KiwiProviderApi extends ProviderAPI implements TicketProviderApi {

    private static final String QUERY_ENDPOINT = "/flights?vehicle_type=aircraft&v=3&curr=RUB&partner=picky&locale=ru";

    private RestTemplate restTemplate;

    private IATAProviderAPI iataProviderAPI;

    private HttpHeaders headers;

    @Autowired
    public KiwiProviderApi(RestTemplate restTemplate, IATAProviderAPI iataProviderAPI) {
        super("https://api.skypicker.com");
        this.restTemplate = restTemplate;
        this.iataProviderAPI = iataProviderAPI;
        headers = new HttpHeaders();
    }

    @Override
    public List<Trip> getTickets(SearchData searchData) {
        if (searchData.getOrigin().getCode() == null || searchData.getDestination().getCode() == null) {
            Route cityCodes = iataProviderAPI.getCityCodes(searchData.getOrigin().getName(), searchData.getDestination().getName());
            searchData.getOrigin().setCode(cityCodes.getOrigin().getIata());
            searchData.getDestination().setCode(cityCodes.getDestination().getIata());
        }
        KiwiResponse kiwiResponse = getKiwiResponse(searchData);
        List<Trip> trips = KiwiResponseConverter.convertResponseToTrip(kiwiResponse);
        return trips;
    }

    private KiwiResponse getKiwiResponse(SearchData searchData) {
        if (searchData.getCabinClass() == CabinClass.BUSINESS) {
            return null;
        }
        String outboundDate = convertDateToString(searchData.getOutboundDate());
        String inboundDate = searchData.getFlightType() == FlightType.ROUND ?
                convertDateToString(searchData.getInboundDate()) : null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + QUERY_ENDPOINT)
                .queryParam("fly_from", searchData.getOrigin().getCode())
                .queryParam("fly_to", searchData.getDestination().getCode())
                .queryParam("date_from", outboundDate)
                .queryParam("date_to", outboundDate)
                .queryParam("return_from", inboundDate)
                .queryParam("return_to", inboundDate)
                .queryParam("flight_type", searchData.getFlightType().toString().toLowerCase())
                .queryParam("adults", searchData.getAdultsCount())
                .queryParam("children", searchData.getChildrenCount())
                .queryParam("infants", searchData.getInfantsCount())
                .queryParam("direct_flights", searchData.getTransfers() ? 0 : 1);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<KiwiResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                KiwiResponse.class);

        KiwiResponse body = response.getBody();
        return body;
    }
    private String convertDateToString(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
