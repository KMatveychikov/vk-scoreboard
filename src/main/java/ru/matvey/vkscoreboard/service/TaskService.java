package ru.matvey.vkscoreboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.matvey.vkscoreboard.model.Task;
import ru.matvey.vkscoreboard.model.TaskCategory;
import ru.matvey.vkscoreboard.model.UserResponse;
import ru.matvey.vkscoreboard.repository.TaskRepository;
import ru.matvey.vkscoreboard.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public Task addTask(Task task) {
        var newTask = Task.builder()
                .title(task.getTitle())
                .text(task.getText())
                .category(task.getCategory())
                .score(task.getScore())
                .build();
        taskRepository.save(newTask);
        return newTask;
    }

    public UserResponse completeTask(String taskId) {
        var task = taskRepository.findById(taskId).orElseThrow();
        var user = authService.getCurrentUser();
        List<Task> completedTasks = user.getCompletedTasks();
        if (!completedTasks.contains(task)) {
            completedTasks.add(task);
            if (task.getCategory() == TaskCategory.CATEGORY1)
                user.setCategory1Score(user.getCategory1Score() + task.getScore());
            if (task.getCategory() == TaskCategory.CATEGORY2)
                user.setCategory1Score(user.getCategory2Score() + task.getScore());
            if (task.getCategory() == TaskCategory.CATEGORY3)
                user.setCategory1Score(user.getCategory3Score() + task.getScore());
        }
        user.setCompletedTasks(completedTasks);
        userRepository.save(user);
        return authService.convertUserToResponse(user);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllUncompletedTask(String userId){
        var user = authService.getUserById(userId);
        List<Task> completedTasks = user.getCompletedTasks();
        return getAllTasks().stream().filter(task -> !completedTasks.contains(task)).toList();
    }
}
