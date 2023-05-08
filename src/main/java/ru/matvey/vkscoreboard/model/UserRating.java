package ru.matvey.vkscoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRating {
    private String userName;
    private int category1completedTasks;
    private int category2completedTasks;
    private int category3completedTasks;
    private int allCompletedTaskCount;

    private float category1Rating;
    private float category2Rating;
    private float category3Rating;

    private float overAllRating;

}
