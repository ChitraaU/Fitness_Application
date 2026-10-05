package com.project.fitness.Service;


import com.project.fitness.Model.Activity;
import com.project.fitness.Model.Recommendation;
import com.project.fitness.Model.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.RecommendationRepository;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RecommendationResponse trackRecommendation(RecommendationRequest recommendationRequest) {
       Activity activity = activityRepository.findById(recommendationRequest.getActivityId())
               .orElseThrow(()->new RuntimeException("Activity recommendation is not fetched : " + recommendationRequest.getActivityId()));

        User user = userRepository.findById(recommendationRequest.getUserId())
                .orElseThrow(()->new RuntimeException("User recommendation is not fetched : " + recommendationRequest.getUserId()));

        Recommendation recommendation = Recommendation.builder().
                type(recommendationRequest.getType()).
                recommendations(recommendationRequest.getRecommendations()).
                improvements(recommendationRequest.getImprovements()).
                suggestions(recommendationRequest.getSuggestions()).
                safety(recommendationRequest.getSafety()).
                user(user).
                activity(activity).build();

        Recommendation savedRecommendations = recommendationRepository.save(recommendation);
        return mapToResponse(savedRecommendations);


    }

    private RecommendationResponse mapToResponse(Recommendation savedRecommendations) {
        RecommendationResponse recommendationResponse = new RecommendationResponse();
        recommendationResponse.setRecommendations(savedRecommendations.getRecommendations());
        recommendationResponse.setId(savedRecommendations.getId());
        recommendationResponse.setImprovements(savedRecommendations.getImprovements());
        recommendationResponse.setCreatedAt(savedRecommendations.getCreatedAt());
        recommendationResponse.setUpdatedAt(savedRecommendations.getUpdatedAt());
        recommendationResponse.setSafety(savedRecommendations.getSafety());
        recommendationResponse.setSuggestions(savedRecommendations.getSuggestions());
        recommendationResponse.setType(savedRecommendations.getType());
        recommendationResponse.setUserId(savedRecommendations.getUser().getId());
        recommendationResponse.setActivityId(savedRecommendations.getActivity().getId());
        return recommendationResponse;

    }

    public List<RecommendationResponse> fetchUserRecommendations(String userId) {
        return recommendationRepository.findByUser_Id(userId).stream().map(this::mapToResponse).toList();
    }

    public List<RecommendationResponse> fetchActivityRecommendations(String activityId) {
        return recommendationRepository.findByActivity_Id(activityId).stream().map(this::mapToResponse).toList();
    }
}
