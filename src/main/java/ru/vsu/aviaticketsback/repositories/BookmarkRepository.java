package ru.vsu.aviaticketsback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.ticketssearch.models.CabinClass;
import ru.vsu.aviaticketsback.ticketssearch.models.FlightType;

import java.util.List;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    List<Bookmark> findAllByUser(User user);

    Bookmark findByUserAndOriginAndDestinationAndAdultCountAndChildCountAndInfantCountAndFlightTypeAndTransfersAndClassType(
            User user, String origin, String destination, Integer adultCount, Integer childrenCount, Integer infantsCount,
            FlightType flightType, boolean transfers, CabinClass cabinClass
    );
}
