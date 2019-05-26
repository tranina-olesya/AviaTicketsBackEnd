package ru.vsu.aviaticketsback.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.repositories.BookmarkRepository;
import ru.vsu.aviaticketsback.services.BookmarkService;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void delete(Long id) {
        bookmarkRepository.deleteById(id);
    }

    @Override
    public List<Bookmark> getAllByUser(User user) {
        return bookmarkRepository.findAllByUser(user);
    }

    @Override
    public Bookmark findBookmark(User user, String origin, String destination, Integer adultCount, Integer childrenCount, Integer infantsCount, FlightType flightType, boolean transfers, CabinClass cabinClass) {
        return bookmarkRepository.findByUserAndOriginAndDestinationAndAdultCountAndChildCountAndInfantCountAndFlightTypeAndTransfersAndClassType(
                user, origin, destination, adultCount, childrenCount, infantsCount, flightType, transfers, cabinClass);
    }
}
