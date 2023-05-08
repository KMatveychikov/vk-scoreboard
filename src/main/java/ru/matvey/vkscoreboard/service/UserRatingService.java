package ru.matvey.vkscoreboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.matvey.vkscoreboard.model.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRatingService {
    private final AuthService authService;
    private final TaskService taskService;


    public List<UserRating> getUsersRating() {
        return authService.getAllUsersResponse().stream().map(this::getUserRating).toList();
    }

    public UserRating getUserRating(UserResponse user) {

        List<Task> allTasks = taskService.getAllTasks();

        List<Task> cat1Tasks = allTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY1).toList();
        List<Task> cat2Tasks = allTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY2).toList();
        List<Task> cat3Tasks = allTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY3).toList();

        List<Task> completedTasks = user.getCompletedTasks();

        List<Task> completedCat1Tasks = completedTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY1).toList();
        List<Task> completedCat2Tasks = completedTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY2).toList();
        List<Task> completedCat3Tasks = completedTasks.stream().filter(task -> task.getCategory() == TaskCategory.CATEGORY3).toList();


        return UserRating.builder()
                .userName(user.getName())
                .category1completedTasks(completedCat1Tasks.size())
                .category2completedTasks(completedCat2Tasks.size())
                .category3completedTasks(completedCat3Tasks.size())
                .allCompletedTaskCount(completedTasks.size())
                .category1Rating((float)cat1Tasks.size()/(float)completedCat1Tasks.size())
                .category2Rating((float)cat2Tasks.size()/(float)completedCat2Tasks.size())
                .category3Rating((float)cat3Tasks.size()/(float)completedCat3Tasks.size())
                .overAllRating((float) allTasks.size()/(float) completedTasks.size())
                .build();
    }


}
