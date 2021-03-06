package ru.vsu.aviaticketsback.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.aviaticketsback.api.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.SearchHistoryEntryResponseDTO;
import ru.vsu.aviaticketsback.api.mappers.SearchHistoryEntryMapper;
import ru.vsu.aviaticketsback.entities.SearchHistoryEntry;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.services.SearchHistoryEntryService;
import ru.vsu.aviaticketsback.services.UserService;

import java.util.List;

@RestController
@Api(tags = {"Search History"})
public class SearchHistoryEntryController {
    private final SearchHistoryEntryService searchHistoryEntryService;
    private final UserService userService;
    private SearchHistoryEntryMapper searchHistoryEntryMapper;

    @Autowired
    public SearchHistoryEntryController(SearchHistoryEntryService searchHistoryEntryService, UserService userService, SearchHistoryEntryMapper searchHistoryEntryMapper) {
        this.searchHistoryEntryService = searchHistoryEntryService;
        this.userService = userService;
        this.searchHistoryEntryMapper = searchHistoryEntryMapper;
    }

    @ApiOperation(value = "Получить всю историю поиска по конкретному пользователю.")
    @RequestMapping(value = "/users/{userCode}/search-history", method = RequestMethod.GET)
    public List<SearchHistoryEntryResponseDTO> getSearchHistoryForUser(@PathVariable String userCode) {
        User user = userService.getByCode(userCode);
        List<SearchHistoryEntry> searchHistoryEntries = searchHistoryEntryService.getAllByUser(user);
        return searchHistoryEntryMapper.searchHistoryEntryListToSearchHistoryEntryResponseDtoList(searchHistoryEntries);
    }

    @ApiOperation(value = "Добавить элемент истории поиска.")
    @RequestMapping(value = "/search-history", method = RequestMethod.POST)
    public ResponseEntity<Long> createSearchHistoryEntry(@RequestBody SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO) {
        SearchHistoryEntry searchHistoryEntry = searchHistoryEntryMapper.searchHistoryEntryRequestDtoToSearchHistoryEntry(searchHistoryEntryRequestDTO);
        searchHistoryEntryService.addBSearchHistoryEntry(searchHistoryEntry);
        return ResponseEntity.ok(searchHistoryEntry.getId());
    }

    @ApiOperation(value = "Удалить один элемент истории поиска (по его id) для конкретного пользователя.")
    @RequestMapping(value = "/search-history/{userCode}/{searchHistoryEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSearchHistoryEntry(@PathVariable String userCode, @PathVariable Long searchHistoryEntryId) {
        User user = userService.getByCode(userCode);
        if (user != null) {
            searchHistoryEntryService.delete(searchHistoryEntryId);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Удалить всю историю поиска для конкретного пользователя.")
    @RequestMapping(value = "/search-history/{userCode}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllSearchHistory(@PathVariable String userCode) {
        User user = userService.getByCode(userCode);
        searchHistoryEntryService.deleteAllByUser(user);
        return ResponseEntity.ok().build();
    }

}
