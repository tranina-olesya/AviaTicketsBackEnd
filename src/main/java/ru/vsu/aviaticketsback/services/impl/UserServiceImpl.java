package ru.vsu.aviaticketsback.services.impl;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.aviaticketsback.entities.User;
import ru.vsu.aviaticketsback.repositories.UserRepository;
import ru.vsu.aviaticketsback.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

//    @Override
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }
//
    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
//
//    @Override
//    public void updateUser(Long userId, User user) {
//        user.setId(userId);
//        userRepository.save(user);
//    }

    @Override
    public List<User> getAllUsers() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    @Override
    public User getByCode(String userCode) {
        return userRepository.findUserByCode(userCode);
    }


}
