package ru.matvey.vkscoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String _id;
    private String title;
    private String text;
    private TaskCategory category;
    private int score;
}
