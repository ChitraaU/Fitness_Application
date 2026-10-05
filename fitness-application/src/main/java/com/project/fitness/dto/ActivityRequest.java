package com.project.fitness.dto;

import com.project.fitness.Model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class ActivityRequest {

    private String userId;
    private Integer duration;
    private Integer caloriesBurnt;
    private LocalDateTime startTime;
    private ActivityType activityType;
    private Map<String, Object> additionalMatrix;
}
