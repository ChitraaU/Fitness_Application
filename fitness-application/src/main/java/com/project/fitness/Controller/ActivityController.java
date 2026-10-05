package com.project.fitness.Controller;

import com.project.fitness.Service.ActivityService;
import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fitness/activities")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    private ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.activity(activityRequest));
    }

    @GetMapping
    private ResponseEntity<List<ActivityResponse>> retrieveActivity(){
        return ResponseEntity.ok(activityService.getActivites());
    }

    @GetMapping(headers = "X-User-ID")
    private ResponseEntity<List<ActivityResponse>> retrieveActivityByUserId(@RequestHeader(value = "X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getActivityByUserId(userId));
    }
}
