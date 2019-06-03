package ru.vsu.aviaticketsback.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.aviaticketsback.api.dto.request.UserRequestDTO;
import ru.vsu.aviaticketsback.api.dto.response.UserResponseDTO;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.api.mappers.UserMapper;
import ru.vsu.aviaticketsback.services.UserService;

import java.util.List;

@RestController
@Api(tags = {"Users"})
public class UserController {
    private UserService userService;

    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Добавить нового пользователя.")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User userByCode = userService.getByCode(userRequestDTO.getCode());
        if (userByCode == null) {
            User user = userMapper.userRequestDtoToUser(userRequestDTO);
            userService.addUser(user);
            return ResponseEntity.ok(user.getId());
        } else
            return ResponseEntity.ok(userByCode.getId());
    }

    @ApiOperation(value = "Получить список всех пользователей (необходимо только для облегчения отладки).")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserResponseDTO> getAllUsers() {
        return userMapper.userListToUserResponseDtoList(userService.getAllUsers());
    }
}
