package com.example.gif.score.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScoreRequestDto {
    private Long teatId;
    private Long evaluatorId;

    private Integer technicalScore;
    private Integer socialValueScore;
    private Integer aiUtilityScore;
    private Integer presentationScore;
}
