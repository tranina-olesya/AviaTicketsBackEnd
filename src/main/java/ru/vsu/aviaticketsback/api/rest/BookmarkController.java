package ru.vsu.aviaticketsback.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.aviaticketsback.api.dto.request.BookmarkRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.BookmarkResponseDTO;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.api.mappers.BookmarkMapper;
import ru.vsu.aviaticketsback.services.BookmarkService;
import ru.vsu.aviaticketsback.services.UserService;

import java.util.List;

@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;

    private BookmarkMapper bookmarkMapper;

    private final UserService userService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService, UserService userService, BookmarkMapper bookmarkMapper) {
        this.bookmarkService = bookmarkService;
        this.userService = userService;
        this.bookmarkMapper = bookmarkMapper;
    }

    @RequestMapping(value = "/users/{userCode}/bookmarks", method = RequestMethod.GET)
    public List<BookmarkResponseDTO> getBookmarksForUser(@PathVariable String userCode) {
        User user = userService.getByCode(userCode);
        List<Bookmark> bookmarks = bookmarkService.getAllByUser(user);
        return bookmarkMapper.bookmarkListToBookmarkResponseDtoList(bookmarks);
    }

    @RequestMapping(value = "/bookmarks", method = RequestMethod.POST)
    public ResponseEntity<Long> createBookmark(@RequestBody BookmarkRequestDTO bookmarkRequestDTO) {
        Bookmark bookmark = bookmarkMapper.bookmarkRequestDtoToBookmark(bookmarkRequestDTO);
        bookmarkService.addBookmark(bookmark);
        return ResponseEntity.ok(bookmark.getId());
    }

    @RequestMapping(value = "/bookmarks/{bookmarkId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBookmark(@PathVariable Long bookmarkId) {
        bookmarkService.delete(bookmarkId);
        return ResponseEntity.ok().build();
    }

}
