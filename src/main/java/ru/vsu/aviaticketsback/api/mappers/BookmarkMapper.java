package ru.vsu.aviaticketsback.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.aviaticketsback.api.dto.request.BookmarkRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.BookmarkResponseDTO;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.repositories.UserRepository;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookmarkMapper {
    @Autowired
    protected UserRepository userRepository;

    @Mappings({
            @Mapping(target = "user", expression = "java(userRepository.findUserByCode(bookmarkRequestDTO.getUserCode()))")
    })
    public abstract Bookmark bookmarkRequestDtoToBookmark(BookmarkRequestDTO bookmarkRequestDTO);

    @Mappings({
            @Mapping(target = "userCode", source = "bookmark.user.code")
    })
    public abstract BookmarkResponseDTO bookmarkToBookmarkResponseDTO(Bookmark bookmark);

    public abstract List<BookmarkResponseDTO> bookmarkListToBookmarkResponseDtoList(List<Bookmark> bookmarks);
}
