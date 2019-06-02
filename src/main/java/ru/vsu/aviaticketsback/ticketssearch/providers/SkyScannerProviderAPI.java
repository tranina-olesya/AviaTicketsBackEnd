package ru.vsu.aviaticketsback.ticketssearch.providers;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vsu.aviaticketsback.ticketssearch.converters.SkyScannerResponseConverter;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;
import ru.vsu.aviaticketsback.ticketssearch.models.SearchData;
import ru.vsu.aviaticketsback.ticketssearch.models.Trip;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerCities;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerCity;
import ru.vsu.aviaticketsback.ticketssearch.models.skyscanner.SkyScannerResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SkyScannerProviderAPI extends ProviderAPI implements TicketProviderApi {
    private static final String CREATE_SESSION_ENDPOINT = "/apiservices/pricing/v1.0";
    private static final String SESSION_RESULTS_ENDPOINT = "/apiservices/pricing/uk2/v1.0/";
    private static final String CITIES_ENDPOINT = "/apiservices/autosuggest/v1.0/RU/RUB/ru-RU/";

    private RestTemplate restTemplate;

    private HttpHeaders headers;

    @Autowired
    public SkyScannerProviderAPI(RestTemplate restTemplate) {
        super("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
        this.restTemplate = restTemplate;
        headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "dde994cc22msh731932b9386ddb4p1237ebjsn88a88c39bb76");
    }

    @Override
    public List<Trip> getTickets(SearchData searchData) {
        String sessionKey = createSession(searchData);
        if (sessionKey != null) {
            SkyScannerResponse sessionResponse = getSessionResponse(searchData, sessionKey);
            List<Trip> trips = SkyScannerResponseConverter.convertResponseToTrip(sessionResponse);
            return trips;
        }
        return null;
    }

    private String getCities(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + CITIES_ENDPOINT)
                .queryParam("query", query);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<SkyScannerCities> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                SkyScannerCities.class);

        SkyScannerCities body = response.getBody();
        if (body != null) {
            List<SkyScannerCity> places = body.getPlaces();
            if (places != null && !places.isEmpty()) {
                SkyScannerCity skyScannerCity = places.get(0);
                return skyScannerCity.getCityId();
            }
        }
        return null;
    }

    private String createSession(SearchData searchData) {
        String origin = getCities(searchData.getOrigin().getName());
        String destination = getCities(searchData.getDestination().getName());
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(baseUrl + CREATE_SESSION_ENDPOINT);

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("outboundDate", convertDateToString(searchData.getOutboundDate())));
            if (searchData.getFlightType() == FlightType.ROUND)
                params.add(new BasicNameValuePair("inboundDate", convertDateToString(searchData.getOutboundDate())));
            params.add(new BasicNameValuePair("cabinClass", searchData.getCabinClass().toString()));
            params.add(new BasicNameValuePair("adults", searchData.getAdultsCount().toString()));
            params.add(new BasicNameValuePair("children", searchData.getChildrenCount().toString()));
            params.add(new BasicNameValuePair("infants", searchData.getInfantsCount().toString()));
            params.add(new BasicNameValuePair("country", "RU"));
            params.add(new BasicNameValuePair("currency", "RUB"));
            params.add(new BasicNameValuePair("locale", "ru-RU"));
            params.add(new BasicNameValuePair("originPlace", origin));
            params.add(new BasicNameValuePair("destinationPlace", destination));
            params.add(new BasicNameValuePair("groupPricing", "true"));

            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httppost.addHeader("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
            httppost.addHeader("X-RapidAPI-Key", "dde994cc22msh731932b9386ddb4p1237ebjsn88a88c39bb76");
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            HttpResponse response = httpclient.execute(httppost);
            if (response != null) {
                Header[] headers = response.getHeaders("location");
                if (headers.length > 0) {
                    String key = headers[0].getValue();

                    if (key != null && !key.isEmpty())
                        return key.substring(key.lastIndexOf("/") + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertDateToString(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private SkyScannerResponse getSessionResponse(SearchData searchData, String sessionKey) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + SESSION_RESULTS_ENDPOINT + sessionKey)
                .queryParam("stops", searchData.getTransfers() ? 1 : 0);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = builder.toUriString();
        HttpEntity<SkyScannerResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                SkyScannerResponse.class);

        SkyScannerResponse body = response.getBody();
        return body;
    }
}
