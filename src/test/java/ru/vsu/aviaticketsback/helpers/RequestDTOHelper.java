package ru.vsu.aviaticketsback.helpers;

import ru.vsu.aviaticketsback.api.dto.request.BookmarkRequestDTO;
import ru.vsu.aviaticketsback.api.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.time.LocalDateTime;

public class RequestDTOHelper {

    public static SearchHistoryEntryRequestDTO createSearchHistoryEntryRequestDTO(String userCode) {
        SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO = new SearchHistoryEntryRequestDTO();
        searchHistoryEntryRequestDTO.setUserCode(userCode);
        searchHistoryEntryRequestDTO.setAdultsCount(1);
        searchHistoryEntryRequestDTO.setChildrenCount(0);
        searchHistoryEntryRequestDTO.setInfantsCount(0);
        searchHistoryEntryRequestDTO.setCabinClass(CabinClass.BUSINESS);
        searchHistoryEntryRequestDTO.setDestination("test1");
        searchHistoryEntryRequestDTO.setOrigin("test0");
        searchHistoryEntryRequestDTO.setFlightType(FlightType.ROUND);
        searchHistoryEntryRequestDTO.setTransfers(false);
        searchHistoryEntryRequestDTO.setInboundDate(LocalDateTime.now());
        searchHistoryEntryRequestDTO.setOutboundDate(LocalDateTime.now());
        return searchHistoryEntryRequestDTO;
    }

    public static BookmarkRequestDTO createBookmarkRequestDTO(String userCode) {
        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO();
        bookmarkRequestDTO.setUserCode(userCode);
        bookmarkRequestDTO.setAdultCount(1);
        bookmarkRequestDTO.setChildCount(0);
        bookmarkRequestDTO.setInfantCount(0);
        bookmarkRequestDTO.setClassType(CabinClass.BUSINESS);
        bookmarkRequestDTO.setDestination("test1");
        bookmarkRequestDTO.setOrigin("test0");
        bookmarkRequestDTO.setFlightType(FlightType.ROUND);
        bookmarkRequestDTO.setTransfers(false);
        return bookmarkRequestDTO;
    }
}
