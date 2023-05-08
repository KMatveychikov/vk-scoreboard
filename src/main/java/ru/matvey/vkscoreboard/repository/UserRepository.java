package ru.matvey.vkscoreboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.matvey.vkscoreboard.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
