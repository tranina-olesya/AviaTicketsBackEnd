package ru.vsu.aviaticketsback.ticketssearch.providers;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vsu.aviaticketsback.ticketssearch.models.iata.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class IATAProviderAPI extends ProviderAPI {

    private static final String QUERY_ENDPOINT = "/widgets_suggest_params";

    private HttpHeaders headers;

    @Autowired
    public IATAProviderAPI(RestTemplate restTemplate) {
        super("https://www.travelpayouts.com");
    }

    public Route getCityCodes(String origin, String destination) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + QUERY_ENDPOINT)
                .queryParam("q", String.format("Из %s в %s", origin, destination));

        String url = builder.toUriString();
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse response = httpclient.execute(httpGet);
            InputStream content = response.getEntity().getContent();

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

            String jsonString = stringBuilder.toString();
            Gson g = new Gson();
            Route route = g.fromJson(jsonString, Route.class);
            return route;

        } catch (IOException e) {

        }

        return null;
    }
}
