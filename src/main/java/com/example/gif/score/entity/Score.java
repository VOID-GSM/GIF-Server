package com.example.gif.score.entity;

import com.example.gif.auth.domain.entity.User;
import com.example.gif.project.entity.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", unique = true, nullable = false)
    private Project teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluator_id", nullable = false)
    private User evaluator;

    @Column(name = "technical_score", nullable = false)
    private Integer technicalScore;

    @Column(name = "social_value_score", nullable = false)
    private Integer socialValueScore;

    @Column(name = "ai_uility_score", nullable = false)
    private Integer aiUtilityScore;

    @Column(name = "presentation_score", nullable = false)
    private Integer presentationScore;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

    private int rank;

    @Builder
    public Score(Project teamName, User evaluator, Integer technicalScore, Integer socialValueScore, Integer aiUtilityScore, Integer presentationScore) {
        this.teamName = teamName;
        this.evaluator = evaluator;
        this.technicalScore = technicalScore;
        this.socialValueScore = socialValueScore;
        this.aiUtilityScore = aiUtilityScore;
        this.presentationScore = presentationScore;
        this.totalScore = technicalScore + socialValueScore + aiUtilityScore + presentationScore;
    }
}