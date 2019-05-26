package ru.vsu.aviaticketsback.services;

import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

public interface BookmarkService {
    void addBookmark(Bookmark bookmark);
    void delete(Long id);
    List<Bookmark> getAllByUser(User user);
}
