package com.example.gif.score.dto.Response;

import lombok.Getter;

@Getter
public class RankResponseDto {
    private String teamName;
    private Integer totalScore;
    private int rank;
}
