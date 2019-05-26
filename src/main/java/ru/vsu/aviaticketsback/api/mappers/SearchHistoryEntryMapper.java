package ru.vsu.aviaticketsback.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.aviaticketsback.api.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.SearchHistoryEntryResponseDTO;
import ru.vsu.aviaticketsback.entities.SearchHistoryEntry;
import ru.vsu.aviaticketsback.repositories.UserRepository;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SearchHistoryEntryMapper {
    @Autowired
    protected UserRepository userRepository;

    @Mappings({
            @Mapping(target = "user", expression = "java(userRepository.findUserByCode(searchHistoryEntryRequestDTO.getUserCode()))")
    })
    public abstract SearchHistoryEntry searchHistoryEntryRequestDtoToSearchHistoryEntry(SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO);

    @Mappings({
            @Mapping(target = "userCode", source = "searchHistoryEntry.user.code")
    })
    public abstract SearchHistoryEntryResponseDTO searchHistoryEntryToSearchHistoryEntryResponseDto(SearchHistoryEntry searchHistoryEntry);

    public abstract List<SearchHistoryEntryResponseDTO> searchHistoryEntryListToSearchHistoryEntryResponseDtoList(List<SearchHistoryEntry> searchHistoryEntries);
}
