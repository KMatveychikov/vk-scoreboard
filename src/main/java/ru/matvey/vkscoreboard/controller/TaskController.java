package ru.matvey.vkscoreboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.vkscoreboard.model.Task;
import ru.matvey.vkscoreboard.model.UserRating;
import ru.matvey.vkscoreboard.model.UserResponse;
import ru.matvey.vkscoreboard.service.AuthService;
import ru.matvey.vkscoreboard.service.TaskService;
import ru.matvey.vkscoreboard.service.UserRatingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthService authService;
    private final UserRatingService userRatingService;

    @PostMapping("/add")
    private ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.addTask(task));
    }

    @PostMapping("/complete")
    private ResponseEntity<UserResponse> completeTask(@RequestParam String taskId) {
        return ResponseEntity.ok(taskService.completeTask(taskId));
    }

    @GetMapping("/all")
    private List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/uncompleted")
    private List<Task> getUncompletedTasks() {
        return taskService.getAllUncompletedTask();
    }

    @GetMapping("/get_rating")
    private UserRating getRating() {
        return userRatingService.getUserRating(authService.convertUserToResponse(authService.getCurrentUser()));
    }

    @GetMapping("/get_all_ratings")
    private List<UserRating> getAllRatings() {
      return userRatingService.getUsersRating();
    }

}
