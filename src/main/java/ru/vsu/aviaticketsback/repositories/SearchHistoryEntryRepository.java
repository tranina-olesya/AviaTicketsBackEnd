package ru.vsu.aviaticketsback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.SearchHistoryEntry;
import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

@Repository
public interface SearchHistoryEntryRepository extends CrudRepository<SearchHistoryEntry, Long> {
    List<SearchHistoryEntry> findAllByUser(User user);
}
