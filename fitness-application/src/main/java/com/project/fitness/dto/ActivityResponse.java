package com.project.fitness.dto;


import com.project.fitness.Model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private Integer duration;
    private String id;
    private String userId;
    private Integer caloriesBurnt;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ActivityType activityType;
    private Map<String, Object> additionalMatrix;

}
