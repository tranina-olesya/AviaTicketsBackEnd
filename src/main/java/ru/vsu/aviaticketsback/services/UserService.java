package ru.vsu.aviaticketsback.services;

import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
//    void delete(Long id);
    User getById(Long id);

    List<User> getAllUsers();

    User getByCode(String userCode);
////    void updateUser(Long userId, User user);
//    List<User> getAll();
}
