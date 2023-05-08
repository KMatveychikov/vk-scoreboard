package ru.matvey.vkscoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String name;
    private String email;
    private Role role;

    private List<Task> completedTasks;

    private int category1Score;
    private int category2Score;
    private int category3Score;
}
