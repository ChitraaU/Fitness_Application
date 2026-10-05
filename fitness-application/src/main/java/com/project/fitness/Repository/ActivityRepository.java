package com.project.fitness.Repository;

import com.project.fitness.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,String> {
    List<Activity> findByUser_Id(String userId);
}
