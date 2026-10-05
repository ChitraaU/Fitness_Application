package com.project.fitness.Controller;


import com.project.fitness.Service.RecommendationService;
import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fitness/recommendation")
@SecurityRequirement(name = "bearerAuth")
public class RecommendationsController {
    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@RequestBody RecommendationRequest recommendationRequest)
    {
      return ResponseEntity.ok(recommendationService.trackRecommendation(recommendationRequest));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationResponse>>fetchUserRecommendation(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(recommendationService.fetchUserRecommendations(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RecommendationResponse>> fetchActivityRecommendation(@PathVariable(value = "activityId") String activityId){
        return ResponseEntity.ok(recommendationService.fetchActivityRecommendations(activityId));

    }



}
