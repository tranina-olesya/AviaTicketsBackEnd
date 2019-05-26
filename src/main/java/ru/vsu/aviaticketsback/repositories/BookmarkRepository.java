package ru.vsu.aviaticketsback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.aviaticketsback.entities.Bookmark;
import ru.vsu.aviaticketsback.entities.User;

import java.util.List;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    List<Bookmark> findAllByUser(User user);
}
