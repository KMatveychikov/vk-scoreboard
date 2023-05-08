package ru.matvey.vkscoreboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.matvey.vkscoreboard.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
}
