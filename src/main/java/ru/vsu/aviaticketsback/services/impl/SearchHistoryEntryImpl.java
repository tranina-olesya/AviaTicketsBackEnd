package ru.vsu.aviaticketsback.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.SearchHistoryEntry;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.repositories.BookmarkRepository;
import ru.vsu.aviaticketsback.repositories.SearchHistoryEntryRepository;
import ru.vsu.aviaticketsback.services.BookmarkService;
import ru.vsu.aviaticketsback.services.SearchHistoryEntryService;

import java.util.List;

@Service
public class SearchHistoryEntryImpl implements SearchHistoryEntryService {
    private SearchHistoryEntryRepository searchHistoryEntryRepository;

    @Autowired
    public SearchHistoryEntryImpl(SearchHistoryEntryRepository searchHistoryEntryRepository) {
        this.searchHistoryEntryRepository = searchHistoryEntryRepository;
    }

    @Override
    public void addBSearchHistoryEntry(SearchHistoryEntry searchHistoryEntry) {
        searchHistoryEntryRepository.save(searchHistoryEntry);
    }

    @Override
    public void delete(Long id) {
        searchHistoryEntryRepository.deleteById(id);
    }

    @Override
    public List<SearchHistoryEntry> getAllByUser(User user) {
        return searchHistoryEntryRepository.findAllByUser(user);
    }

    @Override
    public void deleteAllByUser(User user) {
        for (SearchHistoryEntry searchHistoryEntry : searchHistoryEntryRepository.findAllByUser(user))
            delete(searchHistoryEntry.getId());
    }
}
