package com.project.fitness.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {
    private String id;
    private String userId;
    private String activityId;
    private String type;
    private String recommendations;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;

}
