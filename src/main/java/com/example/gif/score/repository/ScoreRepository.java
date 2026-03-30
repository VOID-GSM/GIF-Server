package com.example.gif.score.repository;

import com.example.gif.project.entity.Project;
import org.springframework.data.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByTeamName(Project teamName);
}
