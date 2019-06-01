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
import ru.vsu.aviaticketsback.api.dto.request.BookmarkRequestDTO;
import ru.vsu.aviaticketsback.api.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.BookmarkResponseDTO;
import ru.vsu.aviaticketsback.api.dto.response.SearchHistoryEntryResponseDTO;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.helpers.RequestDTOHelper;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AviaTicketsBackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatabaseControllersIntegrationTest {
    private static final String CODE = "123";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void users_shouldGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<User>> response = restTemplate.exchange(getRootUrl() + "/users",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void users_shouldCreateUser() {
        User user = new User(CODE);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/users",
                user, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void bookmarks_shouldGetAllBookmarkForUser() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<BookmarkResponseDTO>> response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/bookmarks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookmarkResponseDTO>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void bookmarks_shouldCreateBookmark() {
        BookmarkRequestDTO bookmarkRequestDTO = RequestDTOHelper.createBookmarkRequestDTO(CODE);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/bookmarks",
                bookmarkRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void bookmarks_shouldFindBookmark() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getRootUrl() + "/bookmarks/find")
                .queryParam("adultCount", 1)
                .queryParam("childCount", 0)
                .queryParam("infantCount", 0)
                .queryParam("classType", CabinClass.BUSINESS)
                .queryParam("destination", "test1")
                .queryParam("origin", "test0")
                .queryParam("userCode", CODE)
                .queryParam("flightType", FlightType.ROUND)
                .queryParam("transfers", "true");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<BookmarkResponseDTO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                BookmarkResponseDTO.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void bookmarks_shouldDeleteBookmark() {
        BookmarkRequestDTO bookmarkRequestDTO = RequestDTOHelper.createBookmarkRequestDTO(CODE);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/bookmarks",
                bookmarkRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<BookmarkResponseDTO>> response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/bookmarks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookmarkResponseDTO>>() {
                });
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        List<BookmarkResponseDTO> body = response.getBody();
        assertTrue(body != null && !body.isEmpty());
        int expected = body.size() - 1;

        restTemplate.delete(getRootUrl() + "/bookmarks/" + body.get(0).getId());

        response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/bookmarks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookmarkResponseDTO>>() {
                });
        body = response.getBody();
        assertNotNull(body);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        int actual = body.size();
        assertEquals(expected, actual);
    }

    @Test
    public void searchHistory_shouldGetAllSearchHistoryForUser() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<SearchHistoryEntryResponseDTO>> response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/search-history",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<SearchHistoryEntryResponseDTO>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void searchHistory_shouldAddSearchHistoryEntry() {
        SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO = RequestDTOHelper.createSearchHistoryEntryRequestDTO(CODE);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/search-history",
                searchHistoryEntryRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void searchHistory_shouldDeleteSearchHistoryEntry() {
        SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO = RequestDTOHelper.createSearchHistoryEntryRequestDTO(CODE);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/search-history",
                searchHistoryEntryRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<SearchHistoryEntryResponseDTO>> response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/search-history",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<SearchHistoryEntryResponseDTO>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response);

        List<SearchHistoryEntryResponseDTO> body = response.getBody();
        assertTrue(body != null && !body.isEmpty());
        int expected = body.size() - 1;

        restTemplate.delete(getRootUrl() + "/search-history/" + CODE + "/" + body.get(0).getId());

        response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/search-history",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<SearchHistoryEntryResponseDTO>>() {
                });
        body = response.getBody();
        assertNotNull(body);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        int actual = body.size();
        assertEquals(expected, actual);
    }

    @Test
    public void searchHistory_shouldDeleteAllSearchHistory() {
        SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO = RequestDTOHelper.createSearchHistoryEntryRequestDTO(CODE);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/search-history",
                searchHistoryEntryRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());

        postResponse = restTemplate.postForEntity(getRootUrl() + "/search-history",
                searchHistoryEntryRequestDTO, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<SearchHistoryEntryResponseDTO>> response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/search-history",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<SearchHistoryEntryResponseDTO>>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response);

        restTemplate.delete(getRootUrl() + "/search-history/" + CODE);

        response = restTemplate.exchange(getRootUrl() + "/users/" + CODE + "/search-history",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<SearchHistoryEntryResponseDTO>>() {
                });
        List<SearchHistoryEntryResponseDTO> body = response.getBody();
        assertNotNull(body);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        int actual = body.size();
        assertEquals(0, actual);
    }
}
