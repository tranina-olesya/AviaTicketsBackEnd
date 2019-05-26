package ru.vsu.aviaticketsback.services;

import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.util.List;

public interface BookmarkService {
    void addBookmark(Bookmark bookmark);
    void delete(Long id);
    List<Bookmark> getAllByUser(User user);
    Bookmark findBookmark(User user, String origin, String destination, Integer adultCount, Integer childrenCount, Integer infantsCount,
                          FlightType flightType, boolean transfers, CabinClass cabinClass);
}
