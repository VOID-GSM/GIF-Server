package com.example.gif.score.controller;

import com.example.gif.score.dto.Request.ScoreRequestDto;
import com.example.gif.score.dto.Response.RankResponseDto;
import com.example.gif.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<String> saveScore(@RequestBody ScoreRequestDto dto) {
        scoreService.saveScore(dto);
        return ResponseEntity.ok("점수가 성공적으로 저장되었습니다.");
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankResponseDto>> getFinalRanking() {
        List<RankResponseDto> ranking = scoreService.getFinalRanking();
        return ResponseEntity.ok(ranking);
    }
}
