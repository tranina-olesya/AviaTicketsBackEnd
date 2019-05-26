package ru.vsu.aviaticketsback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.aviaticketsback.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByCode(String code);
}
