package ru.vsu.aviaticketsback.api.mappers;

import org.mapstruct.Mapper;
import ru.vsu.aviaticketsback.api.dto.request.UserRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.UserResponseDTO;
import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User userRequestDtoToUser(UserRequestDTO userRequestDTO);

    public abstract UserResponseDTO userToUserResponseDto(User user);

    public abstract List<UserResponseDTO> userListToUserResponseDtoList(List<User> userList);
}
