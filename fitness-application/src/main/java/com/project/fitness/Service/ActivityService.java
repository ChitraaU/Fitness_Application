package com.project.fitness.Service;

import com.project.fitness.Model.Activity;
import com.project.fitness.Model.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor

public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse activity(ActivityRequest activityRequest) {
        User user = userRepository.findById(activityRequest.getUserId())
                .orElseThrow(()->new RuntimeException("Invalid User " + activityRequest.getUserId()));
        Activity activity = Activity.builder().
                user(user).
                duration(activityRequest.getDuration()).
                caloriesBurnt(activityRequest.getCaloriesBurnt()).
                startTime(activityRequest.getStartTime()).
                activityType(activityRequest.getActivityType()).
                additionalMatrix(activityRequest.getAdditionalMatrix()).
                build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToActivityResponse(savedActivity);
    }

    private ActivityResponse mapToActivityResponse(Activity savedActivity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setCreatedAt(savedActivity.getCreatedAt());
        activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());
        activityResponse.setActivityType(savedActivity.getActivityType());
        activityResponse.setCaloriesBurnt(savedActivity.getCaloriesBurnt());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setStartTime(savedActivity.getStartTime());
        activityResponse.setUserId(savedActivity.getUser().getId());
        activityResponse.setId(savedActivity.getId());
        activityResponse.setAdditionalMatrix(savedActivity.getAdditionalMatrix());
        return activityResponse;
    }

    public List<ActivityResponse> getActivites() {
        return activityRepository.findAll().stream().map(this::mapToActivityResponse).toList();
    }

    public List<ActivityResponse> getActivityByUserId(String id) {
        return activityRepository.findByUser_Id(id).stream().map(this::mapToActivityResponse).toList();
    }
}
