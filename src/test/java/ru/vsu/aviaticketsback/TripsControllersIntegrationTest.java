package ru.vsu.aviaticketsback;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;
import ru.vsu.aviaticketsback.utils.DateConvert;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AviaTicketsBackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TripsControllersIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void cities_shouldGetCities() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getRootUrl() + "/cities")
                .queryParam("origin", "Москва")
                .queryParam("destination", "Лондон");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Route> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Route.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void trips_shouldGetTrips() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getRootUrl() + "/trips")
                .queryParam("originName", "MOW")
                .queryParam("originCode", "Москва")
                .queryParam("destinationName", "VOZ")
                .queryParam("destinationCode", "Воронеж")
                .queryParam("adultsCount", 1)
                .queryParam("childrenCount", 0)
                .queryParam("infantsCount", 0)
                .queryParam("cabinClass", CabinClass.ECONOMY)
                .queryParam("dateInbound", DateConvert.getStringFromDate(new Date()))
                .queryParam("dateOutbound", DateConvert.getStringFromDate(new Date()))
                .queryParam("transfers", true)
                .queryParam("flightType", FlightType.ROUND);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Trip>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Trip>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }
}
