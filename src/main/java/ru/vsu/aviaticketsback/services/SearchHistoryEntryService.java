package ru.vsu.aviaticketsback.services;

import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.SearchHistoryEntry;
import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

public interface SearchHistoryEntryService {
    void addBSearchHistoryEntry(SearchHistoryEntry searchHistoryEntry);
    void delete(Long id);
    List<SearchHistoryEntry> getAllByUser(User user);
}
